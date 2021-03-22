package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.ResultadoValidacao;
import br.gov.serpro.pgt.backend.repository.ResultadoValidacaoRepository;
import br.gov.serpro.pgt.backend.service.ResultadoValidacaoService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ResultadoValidacaoResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ResultadoValidacaoResourceIT {

    private static final Long DEFAULT_ID_TIPO_VALIDACAO = 1L;
    private static final Long UPDATED_ID_TIPO_VALIDACAO = 2L;

    private static final Long DEFAULT_ID_SOLICITACAO = 1L;
    private static final Long UPDATED_ID_SOLICITACAO = 2L;

    private static final Instant DEFAULT_DATA_VALIDACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_VALIDACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RESULTADO = "AAAAAAAAAA";
    private static final String UPDATED_RESULTADO = "BBBBBBBBBB";

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final String DEFAULT_RESULTADO_PARECER = "AAAAAAAAAA";
    private static final String UPDATED_RESULTADO_PARECER = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO_INCRA = 1L;
    private static final Long UPDATED_ID_USUARIO_INCRA = 2L;

    private static final Instant DEFAULT_DATA_PARECER = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_PARECER = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ResultadoValidacaoRepository resultadoValidacaoRepository;

    @Autowired
    private ResultadoValidacaoService resultadoValidacaoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResultadoValidacaoMockMvc;

    private ResultadoValidacao resultadoValidacao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResultadoValidacao createEntity(EntityManager em) {
        ResultadoValidacao resultadoValidacao = new ResultadoValidacao()
            .idTipoValidacao(DEFAULT_ID_TIPO_VALIDACAO)
            .idSolicitacao(DEFAULT_ID_SOLICITACAO)
            .dataValidacao(DEFAULT_DATA_VALIDACAO)
            .resultado(DEFAULT_RESULTADO)
            .situacao(DEFAULT_SITUACAO)
            .resultadoParecer(DEFAULT_RESULTADO_PARECER)
            .idUsuarioIncra(DEFAULT_ID_USUARIO_INCRA)
            .dataParecer(DEFAULT_DATA_PARECER);
        return resultadoValidacao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResultadoValidacao createUpdatedEntity(EntityManager em) {
        ResultadoValidacao resultadoValidacao = new ResultadoValidacao()
            .idTipoValidacao(UPDATED_ID_TIPO_VALIDACAO)
            .idSolicitacao(UPDATED_ID_SOLICITACAO)
            .dataValidacao(UPDATED_DATA_VALIDACAO)
            .resultado(UPDATED_RESULTADO)
            .situacao(UPDATED_SITUACAO)
            .resultadoParecer(UPDATED_RESULTADO_PARECER)
            .idUsuarioIncra(UPDATED_ID_USUARIO_INCRA)
            .dataParecer(UPDATED_DATA_PARECER);
        return resultadoValidacao;
    }

    @BeforeEach
    public void initTest() {
        resultadoValidacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultadoValidacao() throws Exception {
        int databaseSizeBeforeCreate = resultadoValidacaoRepository.findAll().size();
        // Create the ResultadoValidacao
        restResultadoValidacaoMockMvc.perform(post("/api/resultado-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultadoValidacao)))
            .andExpect(status().isCreated());

        // Validate the ResultadoValidacao in the database
        List<ResultadoValidacao> resultadoValidacaoList = resultadoValidacaoRepository.findAll();
        assertThat(resultadoValidacaoList).hasSize(databaseSizeBeforeCreate + 1);
        ResultadoValidacao testResultadoValidacao = resultadoValidacaoList.get(resultadoValidacaoList.size() - 1);
        assertThat(testResultadoValidacao.getIdTipoValidacao()).isEqualTo(DEFAULT_ID_TIPO_VALIDACAO);
        assertThat(testResultadoValidacao.getIdSolicitacao()).isEqualTo(DEFAULT_ID_SOLICITACAO);
        assertThat(testResultadoValidacao.getDataValidacao()).isEqualTo(DEFAULT_DATA_VALIDACAO);
        assertThat(testResultadoValidacao.getResultado()).isEqualTo(DEFAULT_RESULTADO);
        assertThat(testResultadoValidacao.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testResultadoValidacao.getResultadoParecer()).isEqualTo(DEFAULT_RESULTADO_PARECER);
        assertThat(testResultadoValidacao.getIdUsuarioIncra()).isEqualTo(DEFAULT_ID_USUARIO_INCRA);
        assertThat(testResultadoValidacao.getDataParecer()).isEqualTo(DEFAULT_DATA_PARECER);
    }

    @Test
    @Transactional
    public void createResultadoValidacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultadoValidacaoRepository.findAll().size();

        // Create the ResultadoValidacao with an existing ID
        resultadoValidacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultadoValidacaoMockMvc.perform(post("/api/resultado-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultadoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the ResultadoValidacao in the database
        List<ResultadoValidacao> resultadoValidacaoList = resultadoValidacaoRepository.findAll();
        assertThat(resultadoValidacaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllResultadoValidacaos() throws Exception {
        // Initialize the database
        resultadoValidacaoRepository.saveAndFlush(resultadoValidacao);

        // Get all the resultadoValidacaoList
        restResultadoValidacaoMockMvc.perform(get("/api/resultado-validacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultadoValidacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTipoValidacao").value(hasItem(DEFAULT_ID_TIPO_VALIDACAO.intValue())))
            .andExpect(jsonPath("$.[*].idSolicitacao").value(hasItem(DEFAULT_ID_SOLICITACAO.intValue())))
            .andExpect(jsonPath("$.[*].dataValidacao").value(hasItem(DEFAULT_DATA_VALIDACAO.toString())))
            .andExpect(jsonPath("$.[*].resultado").value(hasItem(DEFAULT_RESULTADO)))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO)))
            .andExpect(jsonPath("$.[*].resultadoParecer").value(hasItem(DEFAULT_RESULTADO_PARECER)))
            .andExpect(jsonPath("$.[*].idUsuarioIncra").value(hasItem(DEFAULT_ID_USUARIO_INCRA.intValue())))
            .andExpect(jsonPath("$.[*].dataParecer").value(hasItem(DEFAULT_DATA_PARECER.toString())));
    }
    
    @Test
    @Transactional
    public void getResultadoValidacao() throws Exception {
        // Initialize the database
        resultadoValidacaoRepository.saveAndFlush(resultadoValidacao);

        // Get the resultadoValidacao
        restResultadoValidacaoMockMvc.perform(get("/api/resultado-validacaos/{id}", resultadoValidacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resultadoValidacao.getId().intValue()))
            .andExpect(jsonPath("$.idTipoValidacao").value(DEFAULT_ID_TIPO_VALIDACAO.intValue()))
            .andExpect(jsonPath("$.idSolicitacao").value(DEFAULT_ID_SOLICITACAO.intValue()))
            .andExpect(jsonPath("$.dataValidacao").value(DEFAULT_DATA_VALIDACAO.toString()))
            .andExpect(jsonPath("$.resultado").value(DEFAULT_RESULTADO))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO))
            .andExpect(jsonPath("$.resultadoParecer").value(DEFAULT_RESULTADO_PARECER))
            .andExpect(jsonPath("$.idUsuarioIncra").value(DEFAULT_ID_USUARIO_INCRA.intValue()))
            .andExpect(jsonPath("$.dataParecer").value(DEFAULT_DATA_PARECER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingResultadoValidacao() throws Exception {
        // Get the resultadoValidacao
        restResultadoValidacaoMockMvc.perform(get("/api/resultado-validacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultadoValidacao() throws Exception {
        // Initialize the database
        resultadoValidacaoService.save(resultadoValidacao);

        int databaseSizeBeforeUpdate = resultadoValidacaoRepository.findAll().size();

        // Update the resultadoValidacao
        ResultadoValidacao updatedResultadoValidacao = resultadoValidacaoRepository.findById(resultadoValidacao.getId()).get();
        // Disconnect from session so that the updates on updatedResultadoValidacao are not directly saved in db
        em.detach(updatedResultadoValidacao);
        updatedResultadoValidacao
            .idTipoValidacao(UPDATED_ID_TIPO_VALIDACAO)
            .idSolicitacao(UPDATED_ID_SOLICITACAO)
            .dataValidacao(UPDATED_DATA_VALIDACAO)
            .resultado(UPDATED_RESULTADO)
            .situacao(UPDATED_SITUACAO)
            .resultadoParecer(UPDATED_RESULTADO_PARECER)
            .idUsuarioIncra(UPDATED_ID_USUARIO_INCRA)
            .dataParecer(UPDATED_DATA_PARECER);

        restResultadoValidacaoMockMvc.perform(put("/api/resultado-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedResultadoValidacao)))
            .andExpect(status().isOk());

        // Validate the ResultadoValidacao in the database
        List<ResultadoValidacao> resultadoValidacaoList = resultadoValidacaoRepository.findAll();
        assertThat(resultadoValidacaoList).hasSize(databaseSizeBeforeUpdate);
        ResultadoValidacao testResultadoValidacao = resultadoValidacaoList.get(resultadoValidacaoList.size() - 1);
        assertThat(testResultadoValidacao.getIdTipoValidacao()).isEqualTo(UPDATED_ID_TIPO_VALIDACAO);
        assertThat(testResultadoValidacao.getIdSolicitacao()).isEqualTo(UPDATED_ID_SOLICITACAO);
        assertThat(testResultadoValidacao.getDataValidacao()).isEqualTo(UPDATED_DATA_VALIDACAO);
        assertThat(testResultadoValidacao.getResultado()).isEqualTo(UPDATED_RESULTADO);
        assertThat(testResultadoValidacao.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testResultadoValidacao.getResultadoParecer()).isEqualTo(UPDATED_RESULTADO_PARECER);
        assertThat(testResultadoValidacao.getIdUsuarioIncra()).isEqualTo(UPDATED_ID_USUARIO_INCRA);
        assertThat(testResultadoValidacao.getDataParecer()).isEqualTo(UPDATED_DATA_PARECER);
    }

    @Test
    @Transactional
    public void updateNonExistingResultadoValidacao() throws Exception {
        int databaseSizeBeforeUpdate = resultadoValidacaoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResultadoValidacaoMockMvc.perform(put("/api/resultado-validacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultadoValidacao)))
            .andExpect(status().isBadRequest());

        // Validate the ResultadoValidacao in the database
        List<ResultadoValidacao> resultadoValidacaoList = resultadoValidacaoRepository.findAll();
        assertThat(resultadoValidacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResultadoValidacao() throws Exception {
        // Initialize the database
        resultadoValidacaoService.save(resultadoValidacao);

        int databaseSizeBeforeDelete = resultadoValidacaoRepository.findAll().size();

        // Delete the resultadoValidacao
        restResultadoValidacaoMockMvc.perform(delete("/api/resultado-validacaos/{id}", resultadoValidacao.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResultadoValidacao> resultadoValidacaoList = resultadoValidacaoRepository.findAll();
        assertThat(resultadoValidacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
