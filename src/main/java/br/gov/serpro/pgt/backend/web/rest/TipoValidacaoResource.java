package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.domain.TipoValidacao;
import br.gov.serpro.pgt.backend.repository.TipoValidacaoRepository;
import br.gov.serpro.pgt.backend.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.gov.serpro.pgt.backend.domain.TipoValidacao}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TipoValidacaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoValidacaoResource.class);

    private static final String ENTITY_NAME = "pgtBackendTipoValidacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoValidacaoRepository tipoValidacaoRepository;

    public TipoValidacaoResource(TipoValidacaoRepository tipoValidacaoRepository) {
        this.tipoValidacaoRepository = tipoValidacaoRepository;
    }

    /**
     * {@code POST  /tipo-validacaos} : Create a new tipoValidacao.
     *
     * @param tipoValidacao the tipoValidacao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoValidacao, or with status {@code 400 (Bad Request)} if the tipoValidacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-validacaos")
    public ResponseEntity<TipoValidacao> createTipoValidacao(@RequestBody TipoValidacao tipoValidacao) throws URISyntaxException {
        log.debug("REST request to save TipoValidacao : {}", tipoValidacao);
        if (tipoValidacao.getId() != null) {
            throw new BadRequestAlertException("A new tipoValidacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoValidacao result = tipoValidacaoRepository.save(tipoValidacao);
        return ResponseEntity.created(new URI("/api/tipo-validacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-validacaos} : Updates an existing tipoValidacao.
     *
     * @param tipoValidacao the tipoValidacao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoValidacao,
     * or with status {@code 400 (Bad Request)} if the tipoValidacao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoValidacao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-validacaos")
    public ResponseEntity<TipoValidacao> updateTipoValidacao(@RequestBody TipoValidacao tipoValidacao) throws URISyntaxException {
        log.debug("REST request to update TipoValidacao : {}", tipoValidacao);
        if (tipoValidacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoValidacao result = tipoValidacaoRepository.save(tipoValidacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoValidacao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-validacaos} : get all the tipoValidacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoValidacaos in body.
     */
    @GetMapping("/tipo-validacaos")
    public List<TipoValidacao> getAllTipoValidacaos() {
        log.debug("REST request to get all TipoValidacaos");
        return tipoValidacaoRepository.findAll();
    }

    /**
     * {@code GET  /tipo-validacaos/:id} : get the "id" tipoValidacao.
     *
     * @param id the id of the tipoValidacao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoValidacao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-validacaos/{id}")
    public ResponseEntity<TipoValidacao> getTipoValidacao(@PathVariable Long id) {
        log.debug("REST request to get TipoValidacao : {}", id);
        Optional<TipoValidacao> tipoValidacao = tipoValidacaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoValidacao);
    }

    /**
     * {@code DELETE  /tipo-validacaos/:id} : delete the "id" tipoValidacao.
     *
     * @param id the id of the tipoValidacao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-validacaos/{id}")
    public ResponseEntity<Void> deleteTipoValidacao(@PathVariable Long id) {
        log.debug("REST request to delete TipoValidacao : {}", id);
        tipoValidacaoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
