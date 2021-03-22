package br.gov.serpro.pgt.backend.service;

import br.gov.serpro.pgt.backend.domain.Funcionalidade;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Funcionalidade}.
 */
public interface FuncionalidadeService {

    /**
     * Save a funcionalidade.
     *
     * @param funcionalidade the entity to save.
     * @return the persisted entity.
     */
    Funcionalidade save(Funcionalidade funcionalidade);

    /**
     * Get all the funcionalidades.
     *
     * @return the list of entities.
     */
    List<Funcionalidade> findAll();


    /**
     * Get the "id" funcionalidade.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Funcionalidade> findOne(Long id);

    /**
     * Delete the "id" funcionalidade.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
