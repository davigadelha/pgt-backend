package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.domain.Funcionalidade;
import br.gov.serpro.pgt.backend.service.FuncionalidadeService;
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
 * REST controller for managing {@link br.gov.serpro.pgt.backend.domain.Funcionalidade}.
 */
@RestController
@RequestMapping("/api")
public class FuncionalidadeResource {

    private final Logger log = LoggerFactory.getLogger(FuncionalidadeResource.class);

    private static final String ENTITY_NAME = "pgtBackendFuncionalidade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FuncionalidadeService funcionalidadeService;

    public FuncionalidadeResource(FuncionalidadeService funcionalidadeService) {
        this.funcionalidadeService = funcionalidadeService;
    }

    /**
     * {@code POST  /funcionalidades} : Create a new funcionalidade.
     *
     * @param funcionalidade the funcionalidade to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new funcionalidade, or with status {@code 400 (Bad Request)} if the funcionalidade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/funcionalidades")
    public ResponseEntity<Funcionalidade> createFuncionalidade(@RequestBody Funcionalidade funcionalidade) throws URISyntaxException {
        log.debug("REST request to save Funcionalidade : {}", funcionalidade);
        if (funcionalidade.getId() != null) {
            throw new BadRequestAlertException("A new funcionalidade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Funcionalidade result = funcionalidadeService.save(funcionalidade);
        return ResponseEntity.created(new URI("/api/funcionalidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /funcionalidades} : Updates an existing funcionalidade.
     *
     * @param funcionalidade the funcionalidade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated funcionalidade,
     * or with status {@code 400 (Bad Request)} if the funcionalidade is not valid,
     * or with status {@code 500 (Internal Server Error)} if the funcionalidade couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/funcionalidades")
    public ResponseEntity<Funcionalidade> updateFuncionalidade(@RequestBody Funcionalidade funcionalidade) throws URISyntaxException {
        log.debug("REST request to update Funcionalidade : {}", funcionalidade);
        if (funcionalidade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Funcionalidade result = funcionalidadeService.save(funcionalidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, funcionalidade.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /funcionalidades} : get all the funcionalidades.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of funcionalidades in body.
     */
    @GetMapping("/funcionalidades")
    public List<Funcionalidade> getAllFuncionalidades() {
        log.debug("REST request to get all Funcionalidades");
        return funcionalidadeService.findAll();
    }

    /**
     * {@code GET  /funcionalidades/:id} : get the "id" funcionalidade.
     *
     * @param id the id of the funcionalidade to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the funcionalidade, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/funcionalidades/{id}")
    public ResponseEntity<Funcionalidade> getFuncionalidade(@PathVariable Long id) {
        log.debug("REST request to get Funcionalidade : {}", id);
        Optional<Funcionalidade> funcionalidade = funcionalidadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(funcionalidade);
    }

    /**
     * {@code DELETE  /funcionalidades/:id} : delete the "id" funcionalidade.
     *
     * @param id the id of the funcionalidade to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/funcionalidades/{id}")
    public ResponseEntity<Void> deleteFuncionalidade(@PathVariable Long id) {
        log.debug("REST request to delete Funcionalidade : {}", id);
        funcionalidadeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
