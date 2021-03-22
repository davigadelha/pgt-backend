package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.CategoriaTipoValidacao;
import br.gov.serpro.pgt.backend.repository.CategoriaTipoValidacaoRepository;
import br.gov.serpro.pgt.backend.service.CategoriaTipoValidacaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CategoriaTipoValidacaoResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoriaTipoValidacaoResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private CategoriaTipoValidacaoRepository categoriaTipoValidacaoRepository;

    @Autowired
    private CategoriaTipoValidacaoService categoriaTipoValidacaoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoriaTipoValidacaoMockMvc;

    private CategoriaTipoValidacao categoriaTipoValidacao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaTipoValidacao createEntity(EntityManager em) {
        CategoriaTipoValidacao categoriaTipoValidacao = new CategoriaTipoValidacao()
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO);
        return categoriaTipoValidacao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaTipoValidacao createUpdatedEntity(EntityManager em) {
        CategoriaTipoValidacao categoriaTipoValidacao = new CategoriaTipoValidacao()
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO);
        return categoriaTipoValidacao;
    }

    @BeforeEach
    public void initTest() {
        categoriaTipoValidacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoriaTipoValidacao() throws Exception {
        int databaseSizeBeforeCreate = categoriaTipoValidacaoRepository.findAll().size();
        // Create the CategoriaTipoValidacao
        restCategoriaTipoValidacaoMockMvc.perform(post("/api/categoria-tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaTipoValidacao)))
            .andExpect(status().isCreated());

        // Validate the CategoriaTipoValidacao in the database
        List<CategoriaTipoValidacao> categoriaTipoValidacaoList = categoriaTipoValidacaoRepository.findAll();
        assertThat(categoriaTipoValidacaoList).hasSize(databaseSizeBeforeCreate + 1);
        CategoriaTipoValidacao testCategoriaTipoValidacao = categoriaTipoValidacaoList.get(categoriaTipoValidacaoList.size() - 1);
        assertThat(testCategoriaTipoValidacao.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testCategoriaTipoValidacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createCategoriaTipoValidacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriaTipoValidacaoRepository.findAll().size();

        // Create the CategoriaTipoValidacao with an existing ID
        categoriaTipoValidacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriaTipoValidacaoMockMvc.perform(post("/api/categoria-tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaTipoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaTipoValidacao in the database
        List<CategoriaTipoValidacao> categoriaTipoValidacaoList = categoriaTipoValidacaoRepository.findAll();
        assertThat(categoriaTipoValidacaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategoriaTipoValidacaos() throws Exception {
        // Initialize the database
        categoriaTipoValidacaoRepository.saveAndFlush(categoriaTipoValidacao);

        // Get all the categoriaTipoValidacaoList
        restCategoriaTipoValidacaoMockMvc.perform(get("/api/categoria-tipo-validacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoriaTipoValidacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getCategoriaTipoValidacao() throws Exception {
        // Initialize the database
        categoriaTipoValidacaoRepository.saveAndFlush(categoriaTipoValidacao);

        // Get the categoriaTipoValidacao
        restCategoriaTipoValidacaoMockMvc.perform(get("/api/categoria-tipo-validacaos/{id}", categoriaTipoValidacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoriaTipoValidacao.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    @Transactional
    public void getNonExistingCategoriaTipoValidacao() throws Exception {
        // Get the categoriaTipoValidacao
        restCategoriaTipoValidacaoMockMvc.perform(get("/api/categoria-tipo-validacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriaTipoValidacao() throws Exception {
        // Initialize the database
        categoriaTipoValidacaoService.save(categoriaTipoValidacao);

        int databaseSizeBeforeUpdate = categoriaTipoValidacaoRepository.findAll().size();

        // Update the categoriaTipoValidacao
        CategoriaTipoValidacao updatedCategoriaTipoValidacao = categoriaTipoValidacaoRepository.findById(categoriaTipoValidacao.getId()).get();
        // Disconnect from session so that the updates on updatedCategoriaTipoValidacao are not directly saved in db
        em.detach(updatedCategoriaTipoValidacao);
        updatedCategoriaTipoValidacao
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO);

        restCategoriaTipoValidacaoMockMvc.perform(put("/api/categoria-tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategoriaTipoValidacao)))
            .andExpect(status().isOk());

        // Validate the CategoriaTipoValidacao in the database
        List<CategoriaTipoValidacao> categoriaTipoValidacaoList = categoriaTipoValidacaoRepository.findAll();
        assertThat(categoriaTipoValidacaoList).hasSize(databaseSizeBeforeUpdate);
        CategoriaTipoValidacao testCategoriaTipoValidacao = categoriaTipoValidacaoList.get(categoriaTipoValidacaoList.size() - 1);
        assertThat(testCategoriaTipoValidacao.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testCategoriaTipoValidacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoriaTipoValidacao() throws Exception {
        int databaseSizeBeforeUpdate = categoriaTipoValidacaoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriaTipoValidacaoMockMvc.perform(put("/api/categoria-tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoriaTipoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaTipoValidacao in the database
        List<CategoriaTipoValidacao> categoriaTipoValidacaoList = categoriaTipoValidacaoRepository.findAll();
        assertThat(categoriaTipoValidacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoriaTipoValidacao() throws Exception {
        // Initialize the database
        categoriaTipoValidacaoService.save(categoriaTipoValidacao);

        int databaseSizeBeforeDelete = categoriaTipoValidacaoRepository.findAll().size();

        // Delete the categoriaTipoValidacao
        restCategoriaTipoValidacaoMockMvc.perform(delete("/api/categoria-tipo-validacaos/{id}", categoriaTipoValidacao.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoriaTipoValidacao> categoriaTipoValidacaoList = categoriaTipoValidacaoRepository.findAll();
        assertThat(categoriaTipoValidacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
