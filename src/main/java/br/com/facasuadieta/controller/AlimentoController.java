package br.com.facasuadieta.controller;

import br.com.facasuadieta.dto.AlimentoPorcaoResponseDTO;
import br.com.facasuadieta.dto.AlimentoRequestDTO;
import br.com.facasuadieta.dto.AlimentoResponseDTO;
import br.com.facasuadieta.service.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService service;

    @GetMapping
    public ResponseEntity<List<AlimentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/porcao")
    public ResponseEntity<AlimentoPorcaoResponseDTO> buscarPorPorcao(
            @PathVariable Long id,
            @RequestParam(defaultValue = "100") double gramas) {
        return service.buscarPorIdEPorcao(id, gramas)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlimentoResponseDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<AlimentoResponseDTO> criar(@Valid @RequestBody AlimentoRequestDTO dto) {
        AlimentoResponseDTO novoAlimento = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlimentoRequestDTO dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}