package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.TipoValidacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoValidacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoValidacaoRepository extends JpaRepository<TipoValidacao, Long> {
}
