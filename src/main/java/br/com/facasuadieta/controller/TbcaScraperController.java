package br.com.facasuadieta.controller;
import br.com.facasuadieta.dto.ScrapingStatusDTO;
import br.com.facasuadieta.service.TbcaScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tbca")
public class TbcaScraperController {

    @Autowired
    private TbcaScraperService scraperService;


    @PostMapping("/sincronizar")
    public ResponseEntity<String> iniciarSincronizacao() {
        if (scraperService.getStatus().isEmAndamento()) {
            return ResponseEntity.badRequest().body("O processo de sincronização com a TBCA já está em execução!");
        }

        scraperService.executarScrapingAssincrono();
        return ResponseEntity.ok("Processo de sincronização iniciado em segundo plano.");
    }

    /**
     * Endpoint para consultar a porcentagem e o status atual do processo.
     * GET /api/tbca/status
     */
    @GetMapping("/status")
    public ResponseEntity<ScrapingStatusDTO> consultarStatus() {
        return ResponseEntity.ok(scraperService.getStatus());
    }
}