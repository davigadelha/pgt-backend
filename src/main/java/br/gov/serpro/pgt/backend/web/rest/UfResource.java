package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.domain.Uf;
import br.gov.serpro.pgt.backend.repository.UfRepository;
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
 * REST controller for managing {@link br.gov.serpro.pgt.backend.domain.Uf}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UfResource {

    private final Logger log = LoggerFactory.getLogger(UfResource.class);

    private static final String ENTITY_NAME = "pgtBackendUf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UfRepository ufRepository;

    public UfResource(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    /**
     * {@code POST  /ufs} : Create a new uf.
     *
     * @param uf the uf to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uf, or with status {@code 400 (Bad Request)} if the uf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ufs")
    public ResponseEntity<Uf> createUf(@RequestBody Uf uf) throws URISyntaxException {
        log.debug("REST request to save Uf : {}", uf);
        if (uf.getId() != null) {
            throw new BadRequestAlertException("A new uf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Uf result = ufRepository.save(uf);
        return ResponseEntity.created(new URI("/api/ufs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ufs} : Updates an existing uf.
     *
     * @param uf the uf to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uf,
     * or with status {@code 400 (Bad Request)} if the uf is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uf couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ufs")
    public ResponseEntity<Uf> updateUf(@RequestBody Uf uf) throws URISyntaxException {
        log.debug("REST request to update Uf : {}", uf);
        if (uf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Uf result = ufRepository.save(uf);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uf.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ufs} : get all the ufs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ufs in body.
     */
    @GetMapping("/ufs")
    public List<Uf> getAllUfs() {
        log.debug("REST request to get all Ufs");
        return ufRepository.findAll();
    }

    /**
     * {@code GET  /ufs/:id} : get the "id" uf.
     *
     * @param id the id of the uf to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uf, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ufs/{id}")
    public ResponseEntity<Uf> getUf(@PathVariable Long id) {
        log.debug("REST request to get Uf : {}", id);
        Optional<Uf> uf = ufRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(uf);
    }

    /**
     * {@code DELETE  /ufs/:id} : delete the "id" uf.
     *
     * @param id the id of the uf to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ufs/{id}")
    public ResponseEntity<Void> deleteUf(@PathVariable Long id) {
        log.debug("REST request to delete Uf : {}", id);
        ufRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
