package br.com.facasuadieta.service;

import br.com.facasuadieta.dto.AlimentoPorcaoResponseDTO;
import br.com.facasuadieta.dto.AlimentoRequestDTO;
import br.com.facasuadieta.dto.AlimentoResponseDTO;
import br.com.facasuadieta.entity.Alimento;
import br.com.facasuadieta.mapper.AlimentoMapper;
import br.com.facasuadieta.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {
    @Autowired
    private AlimentoRepository repository;

    @Autowired
    private AlimentoMapper mapper;

    public List<AlimentoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public Optional<AlimentoResponseDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO);
    }

    public List<AlimentoResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public Optional<AlimentoPorcaoResponseDTO> buscarPorIdEPorcao(Long id, double gramasPorcao) {
        return repository.findById(id)
                .map(alimento -> mapper.toPorcaoResponseDTO(alimento, gramasPorcao));
    }

    public AlimentoResponseDTO salvar(AlimentoRequestDTO dto) {
        Alimento alimento = mapper.toEntity(dto);
        Alimento salvo = repository.save(alimento);
        return mapper.toResponseDTO(salvo);
    }

    public Optional<AlimentoResponseDTO> atualizar(Long id, AlimentoRequestDTO dto) {
        return repository.findById(id)
                .map(existente -> {
                    Alimento alimentoParaAtualizar = mapper.toEntity(dto);
                    alimentoParaAtualizar.setId(existente.getId());
                    Alimento atualizado = repository.save(alimentoParaAtualizar);
                    return mapper.toResponseDTO(atualizado);
                });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}