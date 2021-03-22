package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.Uf;
import br.gov.serpro.pgt.backend.repository.UfRepository;

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
 * Integration tests for the {@link UfResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UfResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUfMockMvc;

    private Uf uf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uf createEntity(EntityManager em) {
        Uf uf = new Uf()
            .nome(DEFAULT_NOME)
            .sigla(DEFAULT_SIGLA);
        return uf;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uf createUpdatedEntity(EntityManager em) {
        Uf uf = new Uf()
            .nome(UPDATED_NOME)
            .sigla(UPDATED_SIGLA);
        return uf;
    }

    @BeforeEach
    public void initTest() {
        uf = createEntity(em);
    }

    @Test
    @Transactional
    public void createUf() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();
        // Create the Uf
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isCreated());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate + 1);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testUf.getSigla()).isEqualTo(DEFAULT_SIGLA);
    }

    @Test
    @Transactional
    public void createUfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ufRepository.findAll().size();

        // Create the Uf with an existing ID
        uf.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUfMockMvc.perform(post("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUfs() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        // Get all the ufList
        restUfMockMvc.perform(get("/api/ufs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uf.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)));
    }
    
    @Test
    @Transactional
    public void getUf() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", uf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uf.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA));
    }
    @Test
    @Transactional
    public void getNonExistingUf() throws Exception {
        // Get the uf
        restUfMockMvc.perform(get("/api/ufs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUf() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // Update the uf
        Uf updatedUf = ufRepository.findById(uf.getId()).get();
        // Disconnect from session so that the updates on updatedUf are not directly saved in db
        em.detach(updatedUf);
        updatedUf
            .nome(UPDATED_NOME)
            .sigla(UPDATED_SIGLA);

        restUfMockMvc.perform(put("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUf)))
            .andExpect(status().isOk());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
        Uf testUf = ufList.get(ufList.size() - 1);
        assertThat(testUf.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testUf.getSigla()).isEqualTo(UPDATED_SIGLA);
    }

    @Test
    @Transactional
    public void updateNonExistingUf() throws Exception {
        int databaseSizeBeforeUpdate = ufRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUfMockMvc.perform(put("/api/ufs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uf)))
            .andExpect(status().isBadRequest());

        // Validate the Uf in the database
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUf() throws Exception {
        // Initialize the database
        ufRepository.saveAndFlush(uf);

        int databaseSizeBeforeDelete = ufRepository.findAll().size();

        // Delete the uf
        restUfMockMvc.perform(delete("/api/ufs/{id}", uf.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Uf> ufList = ufRepository.findAll();
        assertThat(ufList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
