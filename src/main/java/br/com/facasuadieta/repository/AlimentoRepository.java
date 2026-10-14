package br.com.facasuadieta.repository;

import br.com.facasuadieta.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    List<Alimento> findByNomeContainingIgnoreCase(String nome);

    Optional<Alimento> findByCodigoTbca(String codigoTbca);
}