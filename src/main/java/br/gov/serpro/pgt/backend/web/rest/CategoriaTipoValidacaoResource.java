package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao;
import br.gov.serpro.pgt.backend.service.CategoriaTipoValidacaoService;
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
 * REST controller for managing {@link br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao}.
 */
@RestController
@RequestMapping("/api")
public class CategoriaTipoValidacaoResource {

    private final Logger log = LoggerFactory.getLogger(CategoriaTipoValidacaoResource.class);

    private static final String ENTITY_NAME = "pgtBackendCategoriaTipoValidacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoriaTipoValidacaoService categoriaTipoValidacaoService;

    public CategoriaTipoValidacaoResource(CategoriaTipoValidacaoService categoriaTipoValidacaoService) {
        this.categoriaTipoValidacaoService = categoriaTipoValidacaoService;
    }

    /**
     * {@code POST  /categoria-tipo-validacaos} : Create a new categoriaTipoValidacao.
     *
     * @param categoriaTipoValidacao the categoriaTipoValidacao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoriaTipoValidacao, or with status {@code 400 (Bad Request)} if the categoriaTipoValidacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categoria-tipo-validacaos")
    public ResponseEntity<CategoriaTipoValidacao> createCategoriaTipoValidacao(@RequestBody CategoriaTipoValidacao categoriaTipoValidacao) throws URISyntaxException {
        log.debug("REST request to save CategoriaTipoValidacao : {}", categoriaTipoValidacao);
        if (categoriaTipoValidacao.getId() != null) {
            throw new BadRequestAlertException("A new categoriaTipoValidacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoriaTipoValidacao result = categoriaTipoValidacaoService.save(categoriaTipoValidacao);
        return ResponseEntity.created(new URI("/api/categoria-tipo-validacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categoria-tipo-validacaos} : Updates an existing categoriaTipoValidacao.
     *
     * @param categoriaTipoValidacao the categoriaTipoValidacao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaTipoValidacao,
     * or with status {@code 400 (Bad Request)} if the categoriaTipoValidacao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoriaTipoValidacao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categoria-tipo-validacaos")
    public ResponseEntity<CategoriaTipoValidacao> updateCategoriaTipoValidacao(@RequestBody CategoriaTipoValidacao categoriaTipoValidacao) throws URISyntaxException {
        log.debug("REST request to update CategoriaTipoValidacao : {}", categoriaTipoValidacao);
        if (categoriaTipoValidacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoriaTipoValidacao result = categoriaTipoValidacaoService.save(categoriaTipoValidacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaTipoValidacao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categoria-tipo-validacaos} : get all the categoriaTipoValidacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoriaTipoValidacaos in body.
     */
    @GetMapping("/categoria-tipo-validacaos")
    public List<CategoriaTipoValidacao> getAllCategoriaTipoValidacaos() {
        log.debug("REST request to get all CategoriaTipoValidacaos");
        return categoriaTipoValidacaoService.findAll();
    }

    /**
     * {@code GET  /categoria-tipo-validacaos/:id} : get the "id" categoriaTipoValidacao.
     *
     * @param id the id of the categoriaTipoValidacao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoriaTipoValidacao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categoria-tipo-validacaos/{id}")
    public ResponseEntity<CategoriaTipoValidacao> getCategoriaTipoValidacao(@PathVariable Long id) {
        log.debug("REST request to get CategoriaTipoValidacao : {}", id);
        Optional<CategoriaTipoValidacao> categoriaTipoValidacao = categoriaTipoValidacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoriaTipoValidacao);
    }

    /**
     * {@code DELETE  /categoria-tipo-validacaos/:id} : delete the "id" categoriaTipoValidacao.
     *
     * @param id the id of the categoriaTipoValidacao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categoria-tipo-validacaos/{id}")
    public ResponseEntity<Void> deleteCategoriaTipoValidacao(@PathVariable Long id) {
        log.debug("REST request to delete CategoriaTipoValidacao : {}", id);
        categoriaTipoValidacaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
