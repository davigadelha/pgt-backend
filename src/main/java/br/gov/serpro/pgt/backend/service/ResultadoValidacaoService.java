package br.gov.serpro.pgt.backend.service;

import br.gov.serpro.pgt.backend.domain.ResultadoValidacao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ResultadoValidacao}.
 */
public interface ResultadoValidacaoService {

    /**
     * Save a resultadoValidacao.
     *
     * @param resultadoValidacao the entity to save.
     * @return the persisted entity.
     */
    ResultadoValidacao save(ResultadoValidacao resultadoValidacao);

    /**
     * Get all the resultadoValidacaos.
     *
     * @return the list of entities.
     */
    List<ResultadoValidacao> findAll();


    /**
     * Get the "id" resultadoValidacao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResultadoValidacao> findOne(Long id);

    /**
     * Delete the "id" resultadoValidacao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
