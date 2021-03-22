package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategoriaTipoValidacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaTipoValidacaoRepository extends JpaRepository<CategoriaTipoValidacao, Long> {
}
