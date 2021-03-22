package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.TipoValidacao;
import br.gov.serpro.pgt.backend.repository.TipoValidacaoRepository;

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
 * Integration tests for the {@link TipoValidacaoResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoValidacaoResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_UF = 1L;
    private static final Long UPDATED_ID_UF = 2L;

    private static final Boolean DEFAULT_IC_IMPEDITIVO = false;
    private static final Boolean UPDATED_IC_IMPEDITIVO = true;

    private static final Boolean DEFAULT_IC_ATIVO = false;
    private static final Boolean UPDATED_IC_ATIVO = true;

    @Autowired
    private TipoValidacaoRepository tipoValidacaoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoValidacaoMockMvc;

    private TipoValidacao tipoValidacao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoValidacao createEntity(EntityManager em) {
        TipoValidacao tipoValidacao = new TipoValidacao()
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO)
            .categoria(DEFAULT_CATEGORIA)
            .idUf(DEFAULT_ID_UF)
            .icImpeditivo(DEFAULT_IC_IMPEDITIVO)
            .icAtivo(DEFAULT_IC_ATIVO);
        return tipoValidacao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoValidacao createUpdatedEntity(EntityManager em) {
        TipoValidacao tipoValidacao = new TipoValidacao()
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO)
            .categoria(UPDATED_CATEGORIA)
            .idUf(UPDATED_ID_UF)
            .icImpeditivo(UPDATED_IC_IMPEDITIVO)
            .icAtivo(UPDATED_IC_ATIVO);
        return tipoValidacao;
    }

    @BeforeEach
    public void initTest() {
        tipoValidacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoValidacao() throws Exception {
        int databaseSizeBeforeCreate = tipoValidacaoRepository.findAll().size();
        // Create the TipoValidacao
        restTipoValidacaoMockMvc.perform(post("/api/tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoValidacao)))
            .andExpect(status().isCreated());

        // Validate the TipoValidacao in the database
        List<TipoValidacao> tipoValidacaoList = tipoValidacaoRepository.findAll();
        assertThat(tipoValidacaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoValidacao testTipoValidacao = tipoValidacaoList.get(tipoValidacaoList.size() - 1);
        assertThat(testTipoValidacao.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testTipoValidacao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testTipoValidacao.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testTipoValidacao.getIdUf()).isEqualTo(DEFAULT_ID_UF);
        assertThat(testTipoValidacao.isIcImpeditivo()).isEqualTo(DEFAULT_IC_IMPEDITIVO);
        assertThat(testTipoValidacao.isIcAtivo()).isEqualTo(DEFAULT_IC_ATIVO);
    }

    @Test
    @Transactional
    public void createTipoValidacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoValidacaoRepository.findAll().size();

        // Create the TipoValidacao with an existing ID
        tipoValidacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoValidacaoMockMvc.perform(post("/api/tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoValidacao in the database
        List<TipoValidacao> tipoValidacaoList = tipoValidacaoRepository.findAll();
        assertThat(tipoValidacaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoValidacaos() throws Exception {
        // Initialize the database
        tipoValidacaoRepository.saveAndFlush(tipoValidacao);

        // Get all the tipoValidacaoList
        restTipoValidacaoMockMvc.perform(get("/api/tipo-validacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoValidacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].idUf").value(hasItem(DEFAULT_ID_UF.intValue())))
            .andExpect(jsonPath("$.[*].icImpeditivo").value(hasItem(DEFAULT_IC_IMPEDITIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].icAtivo").value(hasItem(DEFAULT_IC_ATIVO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTipoValidacao() throws Exception {
        // Initialize the database
        tipoValidacaoRepository.saveAndFlush(tipoValidacao);

        // Get the tipoValidacao
        restTipoValidacaoMockMvc.perform(get("/api/tipo-validacaos/{id}", tipoValidacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoValidacao.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.idUf").value(DEFAULT_ID_UF.intValue()))
            .andExpect(jsonPath("$.icImpeditivo").value(DEFAULT_IC_IMPEDITIVO.booleanValue()))
            .andExpect(jsonPath("$.icAtivo").value(DEFAULT_IC_ATIVO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTipoValidacao() throws Exception {
        // Get the tipoValidacao
        restTipoValidacaoMockMvc.perform(get("/api/tipo-validacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoValidacao() throws Exception {
        // Initialize the database
        tipoValidacaoRepository.saveAndFlush(tipoValidacao);

        int databaseSizeBeforeUpdate = tipoValidacaoRepository.findAll().size();

        // Update the tipoValidacao
        TipoValidacao updatedTipoValidacao = tipoValidacaoRepository.findById(tipoValidacao.getId()).get();
        // Disconnect from session so that the updates on updatedTipoValidacao are not directly saved in db
        em.detach(updatedTipoValidacao);
        updatedTipoValidacao
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO)
            .categoria(UPDATED_CATEGORIA)
            .idUf(UPDATED_ID_UF)
            .icImpeditivo(UPDATED_IC_IMPEDITIVO)
            .icAtivo(UPDATED_IC_ATIVO);

        restTipoValidacaoMockMvc.perform(put("/api/tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoValidacao)))
            .andExpect(status().isOk());

        // Validate the TipoValidacao in the database
        List<TipoValidacao> tipoValidacaoList = tipoValidacaoRepository.findAll();
        assertThat(tipoValidacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoValidacao testTipoValidacao = tipoValidacaoList.get(tipoValidacaoList.size() - 1);
        assertThat(testTipoValidacao.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testTipoValidacao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testTipoValidacao.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testTipoValidacao.getIdUf()).isEqualTo(UPDATED_ID_UF);
        assertThat(testTipoValidacao.isIcImpeditivo()).isEqualTo(UPDATED_IC_IMPEDITIVO);
        assertThat(testTipoValidacao.isIcAtivo()).isEqualTo(UPDATED_IC_ATIVO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoValidacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoValidacaoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoValidacaoMockMvc.perform(put("/api/tipo-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoValidacao in the database
        List<TipoValidacao> tipoValidacaoList = tipoValidacaoRepository.findAll();
        assertThat(tipoValidacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoValidacao() throws Exception {
        // Initialize the database
        tipoValidacaoRepository.saveAndFlush(tipoValidacao);

        int databaseSizeBeforeDelete = tipoValidacaoRepository.findAll().size();

        // Delete the tipoValidacao
        restTipoValidacaoMockMvc.perform(delete("/api/tipo-validacaos/{id}", tipoValidacao.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoValidacao> tipoValidacaoList = tipoValidacaoRepository.findAll();
        assertThat(tipoValidacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
