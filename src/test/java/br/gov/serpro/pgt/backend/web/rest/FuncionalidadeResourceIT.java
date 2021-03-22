package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.Funcionalidade;
import br.gov.serpro.pgt.backend.repository.FuncionalidadeRepository;
import br.gov.serpro.pgt.backend.service.FuncionalidadeService;

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
 * Integration tests for the {@link FuncionalidadeResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FuncionalidadeResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private FuncionalidadeRepository funcionalidadeRepository;

    @Autowired
    private FuncionalidadeService funcionalidadeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFuncionalidadeMockMvc;

    private Funcionalidade funcionalidade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funcionalidade createEntity(EntityManager em) {
        Funcionalidade funcionalidade = new Funcionalidade()
            .nome(DEFAULT_NOME);
        return funcionalidade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funcionalidade createUpdatedEntity(EntityManager em) {
        Funcionalidade funcionalidade = new Funcionalidade()
            .nome(UPDATED_NOME);
        return funcionalidade;
    }

    @BeforeEach
    public void initTest() {
        funcionalidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuncionalidade() throws Exception {
        int databaseSizeBeforeCreate = funcionalidadeRepository.findAll().size();
        // Create the Funcionalidade
        restFuncionalidadeMockMvc.perform(post("/api/funcionalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade)))
            .andExpect(status().isCreated());

        // Validate the Funcionalidade in the database
        List<Funcionalidade> funcionalidadeList = funcionalidadeRepository.findAll();
        assertThat(funcionalidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Funcionalidade testFuncionalidade = funcionalidadeList.get(funcionalidadeList.size() - 1);
        assertThat(testFuncionalidade.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createFuncionalidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = funcionalidadeRepository.findAll().size();

        // Create the Funcionalidade with an existing ID
        funcionalidade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuncionalidadeMockMvc.perform(post("/api/funcionalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Funcionalidade in the database
        List<Funcionalidade> funcionalidadeList = funcionalidadeRepository.findAll();
        assertThat(funcionalidadeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFuncionalidades() throws Exception {
        // Initialize the database
        funcionalidadeRepository.saveAndFlush(funcionalidade);

        // Get all the funcionalidadeList
        restFuncionalidadeMockMvc.perform(get("/api/funcionalidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionalidade.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getFuncionalidade() throws Exception {
        // Initialize the database
        funcionalidadeRepository.saveAndFlush(funcionalidade);

        // Get the funcionalidade
        restFuncionalidadeMockMvc.perform(get("/api/funcionalidades/{id}", funcionalidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(funcionalidade.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }
    @Test
    @Transactional
    public void getNonExistingFuncionalidade() throws Exception {
        // Get the funcionalidade
        restFuncionalidadeMockMvc.perform(get("/api/funcionalidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuncionalidade() throws Exception {
        // Initialize the database
        funcionalidadeService.save(funcionalidade);

        int databaseSizeBeforeUpdate = funcionalidadeRepository.findAll().size();

        // Update the funcionalidade
        Funcionalidade updatedFuncionalidade = funcionalidadeRepository.findById(funcionalidade.getId()).get();
        // Disconnect from session so that the updates on updatedFuncionalidade are not directly saved in db
        em.detach(updatedFuncionalidade);
        updatedFuncionalidade
            .nome(UPDATED_NOME);

        restFuncionalidadeMockMvc.perform(put("/api/funcionalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuncionalidade)))
            .andExpect(status().isOk());

        // Validate the Funcionalidade in the database
        List<Funcionalidade> funcionalidadeList = funcionalidadeRepository.findAll();
        assertThat(funcionalidadeList).hasSize(databaseSizeBeforeUpdate);
        Funcionalidade testFuncionalidade = funcionalidadeList.get(funcionalidadeList.size() - 1);
        assertThat(testFuncionalidade.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingFuncionalidade() throws Exception {
        int databaseSizeBeforeUpdate = funcionalidadeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFuncionalidadeMockMvc.perform(put("/api/funcionalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Funcionalidade in the database
        List<Funcionalidade> funcionalidadeList = funcionalidadeRepository.findAll();
        assertThat(funcionalidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFuncionalidade() throws Exception {
        // Initialize the database
        funcionalidadeService.save(funcionalidade);

        int databaseSizeBeforeDelete = funcionalidadeRepository.findAll().size();

        // Delete the funcionalidade
        restFuncionalidadeMockMvc.perform(delete("/api/funcionalidades/{id}", funcionalidade.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Funcionalidade> funcionalidadeList = funcionalidadeRepository.findAll();
        assertThat(funcionalidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
