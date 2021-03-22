package br.gov.serpro.pgt.backend.repository;

import br.gov.serpro.pgt.backend.domain.Solicitacao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Solicitacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
}
