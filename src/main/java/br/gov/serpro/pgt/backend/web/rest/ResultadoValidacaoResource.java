package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.domain.ResultadoValidacao;
import br.gov.serpro.pgt.backend.service.ResultadoValidacaoService;
import br.gov.serpro.pgt.backend.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.gov.serpro.pgt.backend.domain.ResultadoValidacao}.
 */
@RestController
@RequestMapping("/api")
public class ResultadoValidacaoResource {

    private final Logger log = LoggerFactory.getLogger(ResultadoValidacaoResource.class);

    private static final String ENTITY_NAME = "pgtBackendResultadoValidacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultadoValidacaoService resultadoValidacaoService;

    public ResultadoValidacaoResource(ResultadoValidacaoService resultadoValidacaoService) {
        this.resultadoValidacaoService = resultadoValidacaoService;
    }

    /**
     * {@code POST  /resultado-validacaos} : Create a new resultadoValidacao.
     *
     * @param resultadoValidacao the resultadoValidacao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resultadoValidacao, or with status {@code 400 (Bad Request)} if the resultadoValidacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resultado-validacaos")
    public ResponseEntity<ResultadoValidacao> createResultadoValidacao(@RequestBody ResultadoValidacao resultadoValidacao) throws URISyntaxException {
        log.debug("REST request to save ResultadoValidacao : {}", resultadoValidacao);
        if (resultadoValidacao.getId() != null) {
            throw new BadRequestAlertException("A new resultadoValidacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResultadoValidacao result = resultadoValidacaoService.save(resultadoValidacao);
        return ResponseEntity.created(new URI("/api/resultado-validacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resultado-validacaos} : Updates an existing resultadoValidacao.
     *
     * @param resultadoValidacao the resultadoValidacao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resultadoValidacao,
     * or with status {@code 400 (Bad Request)} if the resultadoValidacao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resultadoValidacao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resultado-validacaos")
    public ResponseEntity<ResultadoValidacao> updateResultadoValidacao(@RequestBody ResultadoValidacao resultadoValidacao) throws URISyntaxException {
        log.debug("REST request to update ResultadoValidacao : {}", resultadoValidacao);
        if (resultadoValidacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResultadoValidacao result = resultadoValidacaoService.save(resultadoValidacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resultadoValidacao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /resultado-validacaos} : get all the resultadoValidacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resultadoValidacaos in body.
     */
    @GetMapping("/resultado-validacaos")
    public List<ResultadoValidacao> getAllResultadoValidacaos() {
        log.debug("REST request to get all ResultadoValidacaos");
        return resultadoValidacaoService.findAll();
    }

    /**
     * {@code GET  /resultado-validacaos/:id} : get the "id" resultadoValidacao.
     *
     * @param id the id of the resultadoValidacao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resultadoValidacao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resultado-validacaos/{id}")
    public ResponseEntity<ResultadoValidacao> getResultadoValidacao(@PathVariable Long id) {
        log.debug("REST request to get ResultadoValidacao : {}", id);
        Optional<ResultadoValidacao> resultadoValidacao = resultadoValidacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultadoValidacao);
    }

    /**
     * {@code DELETE  /resultado-validacaos/:id} : delete the "id" resultadoValidacao.
     *
     * @param id the id of the resultadoValidacao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resultado-validacaos/{id}")
    public ResponseEntity<Void> deleteResultadoValidacao(@PathVariable Long id) {
        log.debug("REST request to delete ResultadoValidacao : {}", id);
        resultadoValidacaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
