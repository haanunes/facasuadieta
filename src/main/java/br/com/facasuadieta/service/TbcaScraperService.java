package br.com.facasuadieta.service;

import br.com.facasuadieta.dto.ScrapingStatusDTO;
import br.com.facasuadieta.entity.Alimento;
import br.com.facasuadieta.repository.AlimentoRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TbcaScraperService {

    private static final Logger log = LoggerFactory.getLogger(TbcaScraperService.class);

    private static final String URL_BASE_LISTA = "https://www.tbca.net.br/base-dados/composicao_alimentos.php";
    private static final String URL_HOST = "https://www.tbca.net.br/base-dados/";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    @Autowired
    private AlimentoRepository alimentoRepository;

    private final ScrapingStatusDTO status = new ScrapingStatusDTO();

    private static class ItemScraping {
        String codigo;
        String urlDetalhe;

        ItemScraping(String codigo, String urlDetalhe) {
            this.codigo = codigo;
            this.urlDetalhe = urlDetalhe;
        }
    }

    public ScrapingStatusDTO getStatus() {
        return status;
    }

    @Async
    public void executarScrapingAssincrono() {
        if (status.isEmAndamento()) {
            return;
        }

        resetStatus();
        status.setEmAndamento(true);
        log.info("Iniciando sincronização com a TBCA...");

        try {
            Connection session = Jsoup.newSession()
                    .userAgent(USER_AGENT)
                    .timeout(20000)
                    .followRedirects(true);

            Document primeiraPagina = session.newRequest()
                    .url(URL_BASE_LISTA + "?pagina=1")
                    .get();

            int ultimaPagina = obterUltimaPagina(primeiraPagina);
            status.setTotalPaginas(ultimaPagina);

            List<ItemScraping> itensParaProcessar = new ArrayList<>();

            // 1. MAPEAMENTO DAS PÁGINAS
            for (int p = 1; p <= ultimaPagina; p++) {
                status.setPaginaAtual(p);

                Document doc = (p == 1) ? primeiraPagina : session.newRequest()
                        .url(URL_BASE_LISTA + "?pagina=" + p)
                        .get();

                Elements rows = doc.select("tbody tr");

                for (Element row : rows) {
                    Elements tds = row.select("td");
                    if (!tds.isEmpty()) {
                        Element linkCodigo = tds.get(0).selectFirst("a");
                        if (linkCodigo != null) {
                            String codigo = linkCodigo.text().trim();
                            String hrefRelativo = linkCodigo.attr("href");

                            if (!codigo.isEmpty() && !hrefRelativo.isEmpty()) {
                                String urlCompleta = URL_HOST + hrefRelativo;
                                itensParaProcessar.add(new ItemScraping(codigo, urlCompleta));
                            }
                        }
                    }
                }

                // Log limpo apenas com a porcentagem do mapeamento das páginas
                double pctMapeamento = ((double) p / ultimaPagina) * 100;
                log.info(String.format("Mapeando páginas: %.2f%%", pctMapeamento));
            }

            status.setTotalAlimentosEncontrados(itensParaProcessar.size());
            int totalEncontrados = itensParaProcessar.size();
            int processados = 0;

            // 2. PROCESSAMENTO DOS ALIMENTOS
            for (ItemScraping item : itensParaProcessar) {
                processados++;
                status.setTotalAlimentosProcessados(processados);

                double percentual = ((double) processados / totalEncontrados) * 100;
                double percentualFormatado = Math.round(percentual * 100.0) / 100.0;
                status.setPercentualConcluido(percentualFormatado);

                try {
                    processarEAdicionarOuAtualizarAlimento(session, item);
                } catch (Exception e) {
                    log.error("Erro ao processar alimento {}: {}", item.codigo, e.getMessage());
                }

                // Log limpo exibindo apenas a porcentagem do progresso dos alimentos
                log.info(String.format("Progresso do salvamento: %.2f%%", percentualFormatado));
            }

            log.info("Sincronização concluída com sucesso!");
            status.setMensagem("Scraping concluído com sucesso!");

        } catch (Exception e) {
            log.error("Erro no scraping: {}", e.getMessage());
            status.setMensagem("Erro durante execução do scraping: " + e.getMessage());
        } finally {
            status.setEmAndamento(false);
        }
    }

    private void processarEAdicionarOuAtualizarAlimento(Connection session, ItemScraping item) throws IOException {
        Document doc = session.newRequest()
                .url(item.urlDetalhe)
                .get();

        String nome = "Sem Descrição";
        Element overview = doc.selectFirst("#overview, h5, h6, .card-title");
        if (overview != null) {
            String texto = overview.text().trim();
            if (texto.contains("Descrição:")) {
                nome = texto.split("Descrição:")[1].split("<<")[0].trim();
            } else if (!texto.isEmpty() && !texto.contains("Composição de Alimentos") && !texto.contains("Código")) {
                nome = texto;
            }
        }

        Alimento alimento = alimentoRepository.findByCodigoTbca(item.codigo)
                .orElse(new Alimento());

        alimento.setCodigoTbca(item.codigo);
        alimento.setNome(nome);

        Elements rows = doc.select("#tabela1 tbody tr, table.display tbody tr");

        for (Element row : rows) {
            Elements tds = row.select("td");
            if (tds.size() >= 3) {
                String componente = tds.get(0).text().trim();
                String valorStr = tds.get(2).text().trim();

                BigDecimal valor = parseValue(valorStr);
                mapeiaComponenteParaEntidade(alimento, componente, valor);
            }
        }

        alimentoRepository.save(alimento);
    }

    private int obterUltimaPagina(Document doc) {
        int maxPage = 1;
        Elements pageLinks = doc.select("a.page-link, .pagination a, nav a");

        for (Element link : pageLinks) {
            String href = link.attr("href");
            String text = link.text().trim();

            if (href.contains("pagina=")) {
                try {
                    String pageStr = href.substring(href.indexOf("pagina=") + 7).split("&")[0];
                    int pageNum = Integer.parseInt(pageStr);
                    if (pageNum > maxPage) maxPage = pageNum;
                } catch (Exception ignored) {}
            }

            try {
                int pageNum = Integer.parseInt(text);
                if (pageNum > maxPage) maxPage = pageNum;
            } catch (NumberFormatException ignored) {}
        }
        return maxPage;
    }

    private BigDecimal parseValue(String val) {
        if (val == null || val.equalsIgnoreCase("tr") || val.equalsIgnoreCase("NA") || val.isEmpty() || val.equals("-")) {
            return BigDecimal.ZERO;
        }
        try {
            String limpo = val.replace(",", ".").replaceAll("[^0-9.]", "");
            return limpo.isEmpty() ? BigDecimal.ZERO : new BigDecimal(limpo);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private void mapeiaComponenteParaEntidade(Alimento a, String comp, BigDecimal val) {
        String lower = comp.toLowerCase();
        if (lower.contains("energia") && lower.contains("kj")) a.setEnergiaKj(val);
        else if (lower.contains("energia") && lower.contains("kcal")) a.setEnergiaKcal(val);
        else if (lower.contains("umidade")) a.setUmidadeG(val);
        else if (lower.contains("carboidrato total")) a.setCarboidratoTotalG(val);
        else if (lower.contains("carboidrato disponível")) a.setCarboidratoDisponivelG(val);
        else if (lower.contains("proteína vegetal")) a.setProteinaVegetalG(val);
        else if (lower.contains("proteína animal")) a.setProteinaAnimalG(val);
        else if (lower.contains("proteína")) a.setProteinaG(val);
        else if (lower.contains("lipídios")) a.setLipidiosG(val);
        else if (lower.contains("fibra alimentar")) a.setFibraAlimentarG(val);
        else if (lower.contains("álcool")) a.setAlcoolG(val);
        else if (lower.contains("cinzas")) a.setCinzasG(val);
        else if (lower.contains("colesterol")) a.setColesterolMg(val);
        else if (lower.contains("saturados")) a.setAcidosGraxosSaturadosG(val);
        else if (lower.contains("monoinsaturados")) a.setAcidosGraxosMonoinsaturadosG(val);
        else if (lower.contains("poliinsaturados")) a.setAcidosGraxosPoliinsaturadosG(val);
        else if (lower.contains("trans")) a.setAcidosGraxosTransG(val);
        else if (lower.contains("cálcio")) a.setCalcioMg(val);
        else if (lower.contains("ferro")) a.setFerroMg(val);
        else if (lower.contains("sódio")) a.setSodioMg(val);
        else if (lower.contains("magnésio")) a.setMagnesioMg(val);
        else if (lower.contains("fósforo")) a.setFosforoMg(val);
        else if (lower.contains("potássio")) a.setPotassioMg(val);
        else if (lower.contains("manganês")) a.setManganesMg(val);
        else if (lower.contains("zinco")) a.setZincoMg(val);
        else if (lower.contains("cobre")) a.setCobreMg(val);
        else if (lower.contains("selênio")) a.setSelenioMcg(val);
        else if (lower.contains("vitamina a (re)")) a.setVitaminaAReMcg(val);
        else if (lower.contains("vitamina a (rae)")) a.setVitaminaARaeMcg(val);
        else if (lower.contains("vitamina d")) a.setVitaminaDMcg(val);
        else if (lower.contains("alfa-tocoferol")) a.setVitaminaEMg(val);
        else if (lower.contains("tiamina")) a.setTiaminaMg(val);
        else if (lower.contains("riboflavina")) a.setRiboflavinaMg(val);
        else if (lower.contains("niacina")) a.setNiacinaMg(val);
        else if (lower.contains("vitamina b6")) a.setVitaminaB6Mg(val);
        else if (lower.contains("vitamina b12")) a.setVitaminaB12Mcg(val);
        else if (lower.contains("vitamina c")) a.setVitaminaCMg(val);
        else if (lower.contains("folato")) a.setEquivalenteFolatoMcg(val);
        else if (lower.contains("sal de adição")) a.setSalAdicaoG(val);
        else if (lower.contains("açúcar de adição")) a.setAcucarAdicaoG(val);
        else if (lower.contains("gordura de adição")) a.setGorduraAdicaoG(val);
    }

    private void resetStatus() {
        status.setEmAndamento(false);
        status.setPaginaAtual(0);
        status.setTotalPaginas(0);
        status.setTotalAlimentosProcessados(0);
        status.setTotalAlimentosEncontrados(0);
        status.setPercentualConcluido(0.0);
        status.setMensagem("Iniciando...");
    }
}