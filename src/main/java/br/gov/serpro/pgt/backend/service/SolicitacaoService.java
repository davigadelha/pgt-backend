package br.gov.serpro.pgt.backend.service;

import br.gov.serpro.pgt.backend.domain.Solicitacao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Solicitacao}.
 */
public interface SolicitacaoService {

    /**
     * Save a solicitacao.
     *
     * @param solicitacao the entity to save.
     * @return the persisted entity.
     */
    Solicitacao save(Solicitacao solicitacao);

    /**
     * Get all the solicitacaos.
     *
     * @return the list of entities.
     */
    List<Solicitacao> findAll();


    /**
     * Get the "id" solicitacao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Solicitacao> findOne(Long id);

    /**
     * Delete the "id" solicitacao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
