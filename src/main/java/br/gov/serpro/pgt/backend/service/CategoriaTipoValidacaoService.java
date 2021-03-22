package br.gov.serpro.pgt.backend.service;

import br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CategoriaTipoValidacao}.
 */
public interface CategoriaTipoValidacaoService {

    /**
     * Save a categoriaTipoValidacao.
     *
     * @param categoriaTipoValidacao the entity to save.
     * @return the persisted entity.
     */
    CategoriaTipoValidacao save(CategoriaTipoValidacao categoriaTipoValidacao);

    /**
     * Get all the categoriaTipoValidacaos.
     *
     * @return the list of entities.
     */
    List<CategoriaTipoValidacao> findAll();


    /**
     * Get the "id" categoriaTipoValidacao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoriaTipoValidacao> findOne(Long id);

    /**
     * Delete the "id" categoriaTipoValidacao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
