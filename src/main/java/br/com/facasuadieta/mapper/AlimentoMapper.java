package br.com.facasuadieta.mapper;


import br.com.facasuadieta.dto.AlimentoPorcaoResponseDTO;
import br.com.facasuadieta.dto.AlimentoRequestDTO;
import br.com.facasuadieta.dto.AlimentoResponseDTO;
import br.com.facasuadieta.entity.Alimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AlimentoMapper {

    public Alimento toEntity(AlimentoRequestDTO dto) {
        Alimento entity = new Alimento();
        entity.setNome(dto.getNome());
        entity.setEnergiaKj(dto.getEnergiaKj());
        entity.setEnergiaKcal(dto.getEnergiaKcal());
        entity.setUmidadeG(dto.getUmidadeG());
        entity.setCarboidratoTotalG(dto.getCarboidratoTotalG());
        entity.setCarboidratoDisponivelG(dto.getCarboidratoDisponivelG());
        entity.setProteinaG(dto.getProteinaG());
        entity.setProteinaVegetalG(dto.getProteinaVegetalG());
        entity.setProteinaAnimalG(dto.getProteinaAnimalG());
        entity.setLipidiosG(dto.getLipidiosG());
        entity.setFibraAlimentarG(dto.getFibraAlimentarG());
        entity.setAlcoolG(dto.getAlcoolG());
        entity.setCinzasG(dto.getCinzasG());
        entity.setColesterolMg(dto.getColesterolMg());
        entity.setAcidosGraxosSaturadosG(dto.getAcidosGraxosSaturadosG());
        entity.setAcidosGraxosMonoinsaturadosG(dto.getAcidosGraxosMonoinsaturadosG());
        entity.setAcidosGraxosPoliinsaturadosG(dto.getAcidosGraxosPoliinsaturadosG());
        entity.setAcidosGraxosTransG(dto.getAcidosGraxosTransG());
        entity.setCalcioMg(dto.getCalcioMg());
        entity.setFerroMg(dto.getFerroMg());
        entity.setSodioMg(dto.getSodioMg());
        entity.setMagnesioMg(dto.getMagnesioMg());
        entity.setFosforoMg(dto.getFosforoMg());
        entity.setPotassioMg(dto.getPotassioMg());
        entity.setManganesMg(dto.getManganesMg());
        entity.setZincoMg(dto.getZincoMg());
        entity.setCobreMg(dto.getCobreMg());
        entity.setSelenioMcg(dto.getSelenioMcg());
        entity.setVitaminaAReMcg(dto.getVitaminaAReMcg());
        entity.setVitaminaARaeMcg(dto.getVitaminaARaeMcg());
        entity.setVitaminaDMcg(dto.getVitaminaDMcg());
        entity.setVitaminaEMg(dto.getVitaminaEMg());
        entity.setTiaminaMg(dto.getTiaminaMg());
        entity.setRiboflavinaMg(dto.getRiboflavinaMg());
        entity.setNiacinaMg(dto.getNiacinaMg());
        entity.setVitaminaB6Mg(dto.getVitaminaB6Mg());
        entity.setVitaminaB12Mcg(dto.getVitaminaB12Mcg());
        entity.setVitaminaCMg(dto.getVitaminaCMg());
        entity.setEquivalenteFolatoMcg(dto.getEquivalenteFolatoMcg());
        entity.setSalAdicaoG(dto.getSalAdicaoG());
        entity.setAcucarAdicaoG(dto.getAcucarAdicaoG());
        entity.setGorduraAdicaoG(dto.getGorduraAdicaoG());
        return entity;
    }

    public AlimentoResponseDTO toResponseDTO(Alimento entity) {
        AlimentoResponseDTO dto = new AlimentoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEnergiaKj(entity.getEnergiaKj());
        dto.setEnergiaKcal(entity.getEnergiaKcal());
        dto.setUmidadeG(entity.getUmidadeG());
        dto.setCarboidratoTotalG(entity.getCarboidratoTotalG());
        dto.setCarboidratoDisponivelG(entity.getCarboidratoDisponivelG());
        dto.setProteinaG(entity.getProteinaG());
        dto.setProteinaVegetalG(entity.getProteinaVegetalG());
        dto.setProteinaAnimalG(entity.getProteinaAnimalG());
        dto.setLipidiosG(entity.getLipidiosG());
        dto.setFibraAlimentarG(entity.getFibraAlimentarG());
        dto.setAlcoolG(entity.getAlcoolG());
        dto.setCinzasG(entity.getCinzasG());
        dto.setColesterolMg(entity.getColesterolMg());
        dto.setAcidosGraxosSaturadosG(entity.getAcidosGraxosSaturadosG());
        dto.setAcidosGraxosMonoinsaturadosG(entity.getAcidosGraxosMonoinsaturadosG());
        dto.setAcidosGraxosPoliinsaturadosG(entity.getAcidosGraxosPoliinsaturadosG());
        dto.setAcidosGraxosTransG(entity.getAcidosGraxosTransG());
        dto.setCalcioMg(entity.getCalcioMg());
        dto.setFerroMg(entity.getFerroMg());
        dto.setSodioMg(entity.getSodioMg());
        dto.setMagnesioMg(entity.getMagnesioMg());
        dto.setFosforoMg(entity.getFosforoMg());
        dto.setPotassioMg(entity.getPotassioMg());
        dto.setManganesMg(entity.getManganesMg());
        dto.setZincoMg(entity.getZincoMg());
        dto.setCobreMg(entity.getCobreMg());
        dto.setSelenioMcg(entity.getSelenioMcg());
        dto.setVitaminaAReMcg(entity.getVitaminaAReMcg());
        dto.setVitaminaARaeMcg(entity.getVitaminaARaeMcg());
        dto.setVitaminaDMcg(entity.getVitaminaDMcg());
        dto.setVitaminaEMg(entity.getVitaminaEMg());
        dto.setTiaminaMg(entity.getTiaminaMg());
        dto.setRiboflavinaMg(entity.getRiboflavinaMg());
        dto.setNiacinaMg(entity.getNiacinaMg());
        dto.setVitaminaB6Mg(entity.getVitaminaB6Mg());
        dto.setVitaminaB12Mcg(entity.getVitaminaB12Mcg());
        dto.setVitaminaCMg(entity.getVitaminaCMg());
        dto.setEquivalenteFolatoMcg(entity.getEquivalenteFolatoMcg());
        dto.setSalAdicaoG(entity.getSalAdicaoG());
        dto.setAcucarAdicaoG(entity.getAcucarAdicaoG());
        dto.setGorduraAdicaoG(entity.getGorduraAdicaoG());
        return dto;
    }

    public AlimentoPorcaoResponseDTO toPorcaoResponseDTO(Alimento entity, double gramasPorcao) {
        AlimentoPorcaoResponseDTO dto = new AlimentoPorcaoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setGramasPorcao(gramasPorcao);

        dto.setEnergiaKj(calc(entity.getEnergiaKj(), gramasPorcao));
        dto.setEnergiaKcal(calc(entity.getEnergiaKcal(), gramasPorcao));
        dto.setUmidadeG(calc(entity.getUmidadeG(), gramasPorcao));
        dto.setCarboidratoTotalG(calc(entity.getCarboidratoTotalG(), gramasPorcao));
        dto.setCarboidratoDisponivelG(calc(entity.getCarboidratoDisponivelG(), gramasPorcao));
        dto.setProteinaG(calc(entity.getProteinaG(), gramasPorcao));
        dto.setProteinaVegetalG(calc(entity.getProteinaVegetalG(), gramasPorcao));
        dto.setProteinaAnimalG(calc(entity.getProteinaAnimalG(), gramasPorcao));
        dto.setLipidiosG(calc(entity.getLipidiosG(), gramasPorcao));
        dto.setFibraAlimentarG(calc(entity.getFibraAlimentarG(), gramasPorcao));
        dto.setAlcoolG(calc(entity.getAlcoolG(), gramasPorcao));
        dto.setCinzasG(calc(entity.getCinzasG(), gramasPorcao));
        dto.setColesterolMg(calc(entity.getColesterolMg(), gramasPorcao));
        dto.setAcidosGraxosSaturadosG(calc(entity.getAcidosGraxosSaturadosG(), gramasPorcao));
        dto.setAcidosGraxosMonoinsaturadosG(calc(entity.getAcidosGraxosMonoinsaturadosG(), gramasPorcao));
        dto.setAcidosGraxosPoliinsaturadosG(calc(entity.getAcidosGraxosPoliinsaturadosG(), gramasPorcao));
        dto.setAcidosGraxosTransG(calc(entity.getAcidosGraxosTransG(), gramasPorcao));
        dto.setCalcioMg(calc(entity.getCalcioMg(), gramasPorcao));
        dto.setFerroMg(calc(entity.getFerroMg(), gramasPorcao));
        dto.setSodioMg(calc(entity.getSodioMg(), gramasPorcao));
        dto.setMagnesioMg(calc(entity.getMagnesioMg(), gramasPorcao));
        dto.setFosforoMg(calc(entity.getFosforoMg(), gramasPorcao));
        dto.setPotassioMg(calc(entity.getPotassioMg(), gramasPorcao));
        dto.setManganesMg(calc(entity.getManganesMg(), gramasPorcao));
        dto.setZincoMg(calc(entity.getZincoMg(), gramasPorcao));
        dto.setCobreMg(calc(entity.getCobreMg(), gramasPorcao));
        dto.setSelenioMcg(calc(entity.getSelenioMcg(), gramasPorcao));
        dto.setVitaminaAReMcg(calc(entity.getVitaminaAReMcg(), gramasPorcao));
        dto.setVitaminaARaeMcg(calc(entity.getVitaminaARaeMcg(), gramasPorcao));
        dto.setVitaminaDMcg(calc(entity.getVitaminaDMcg(), gramasPorcao));
        dto.setVitaminaEMg(calc(entity.getVitaminaEMg(), gramasPorcao));
        dto.setTiaminaMg(calc(entity.getTiaminaMg(), gramasPorcao));
        dto.setRiboflavinaMg(calc(entity.getRiboflavinaMg(), gramasPorcao));
        dto.setNiacinaMg(calc(entity.getNiacinaMg(), gramasPorcao));
        dto.setVitaminaB6Mg(calc(entity.getVitaminaB6Mg(), gramasPorcao));
        dto.setVitaminaB12Mcg(calc(entity.getVitaminaB12Mcg(), gramasPorcao));
        dto.setVitaminaCMg(calc(entity.getVitaminaCMg(), gramasPorcao));
        dto.setEquivalenteFolatoMcg(calc(entity.getEquivalenteFolatoMcg(), gramasPorcao));
        dto.setSalAdicaoG(calc(entity.getSalAdicaoG(), gramasPorcao));
        dto.setAcucarAdicaoG(calc(entity.getAcucarAdicaoG(), gramasPorcao));
        dto.setGorduraAdicaoG(calc(entity.getGorduraAdicaoG(), gramasPorcao));
        return dto;
    }

    private BigDecimal calc(BigDecimal val, double gramas) {
        if (val == null) return BigDecimal.ZERO;
        return val.multiply(BigDecimal.valueOf(gramas))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}