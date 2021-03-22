package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.ResultadoValidacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ResultadoValidacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultadoValidacaoRepository extends JpaRepository<ResultadoValidacao, Long> {
}
