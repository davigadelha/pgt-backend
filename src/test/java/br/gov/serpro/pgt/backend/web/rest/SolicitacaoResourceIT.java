package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.Solicitacao;
import br.gov.serpro.pgt.backend.repository.SolicitacaoRepository;
import br.gov.serpro.pgt.backend.service.SolicitacaoService;

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
 * Integration tests for the {@link SolicitacaoResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SolicitacaoResourceIT {

    private static final Long DEFAULT_ID_USUARIO_INCRA = 1L;
    private static final Long UPDATED_ID_USUARIO_INCRA = 2L;

    private static final String DEFAULT_CPF_CNPJ_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_CPF_CNPJ_SOLICITANTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_SOLICITACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_SOLICITACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTEUDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTEUDO = "BBBBBBBBBB";

    private static final String DEFAULT_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_PROTOCOLO = "BBBBBBBBBB";

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSolicitacaoMockMvc;

    private Solicitacao solicitacao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitacao createEntity(EntityManager em) {
        Solicitacao solicitacao = new Solicitacao()
            .idUsuarioIncra(DEFAULT_ID_USUARIO_INCRA)
            .cpfCnpjSolicitante(DEFAULT_CPF_CNPJ_SOLICITANTE)
            .dataSolicitacao(DEFAULT_DATA_SOLICITACAO)
            .situacao(DEFAULT_SITUACAO)
            .conteudo(DEFAULT_CONTEUDO)
            .protocolo(DEFAULT_PROTOCOLO);
        return solicitacao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitacao createUpdatedEntity(EntityManager em) {
        Solicitacao solicitacao = new Solicitacao()
            .idUsuarioIncra(UPDATED_ID_USUARIO_INCRA)
            .cpfCnpjSolicitante(UPDATED_CPF_CNPJ_SOLICITANTE)
            .dataSolicitacao(UPDATED_DATA_SOLICITACAO)
            .situacao(UPDATED_SITUACAO)
            .conteudo(UPDATED_CONTEUDO)
            .protocolo(UPDATED_PROTOCOLO);
        return solicitacao;
    }

    @BeforeEach
    public void initTest() {
        solicitacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createSolicitacao() throws Exception {
        int databaseSizeBeforeCreate = solicitacaoRepository.findAll().size();
        // Create the Solicitacao
        restSolicitacaoMockMvc.perform(post("/api/solicitacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitacao)))
            .andExpect(status().isCreated());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Solicitacao testSolicitacao = solicitacaoList.get(solicitacaoList.size() - 1);
        assertThat(testSolicitacao.getIdUsuarioIncra()).isEqualTo(DEFAULT_ID_USUARIO_INCRA);
        assertThat(testSolicitacao.getCpfCnpjSolicitante()).isEqualTo(DEFAULT_CPF_CNPJ_SOLICITANTE);
        assertThat(testSolicitacao.getDataSolicitacao()).isEqualTo(DEFAULT_DATA_SOLICITACAO);
        assertThat(testSolicitacao.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testSolicitacao.getConteudo()).isEqualTo(DEFAULT_CONTEUDO);
        assertThat(testSolicitacao.getProtocolo()).isEqualTo(DEFAULT_PROTOCOLO);
    }

    @Test
    @Transactional
    public void createSolicitacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = solicitacaoRepository.findAll().size();

        // Create the Solicitacao with an existing ID
        solicitacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSolicitacaoMockMvc.perform(post("/api/solicitacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitacao)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSolicitacaos() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);

        // Get all the solicitacaoList
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUsuarioIncra").value(hasItem(DEFAULT_ID_USUARIO_INCRA.intValue())))
            .andExpect(jsonPath("$.[*].cpfCnpjSolicitante").value(hasItem(DEFAULT_CPF_CNPJ_SOLICITANTE)))
            .andExpect(jsonPath("$.[*].dataSolicitacao").value(hasItem(DEFAULT_DATA_SOLICITACAO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO)))
            .andExpect(jsonPath("$.[*].conteudo").value(hasItem(DEFAULT_CONTEUDO)))
            .andExpect(jsonPath("$.[*].protocolo").value(hasItem(DEFAULT_PROTOCOLO)));
    }
    
    @Test
    @Transactional
    public void getSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoRepository.saveAndFlush(solicitacao);

        // Get the solicitacao
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos/{id}", solicitacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(solicitacao.getId().intValue()))
            .andExpect(jsonPath("$.idUsuarioIncra").value(DEFAULT_ID_USUARIO_INCRA.intValue()))
            .andExpect(jsonPath("$.cpfCnpjSolicitante").value(DEFAULT_CPF_CNPJ_SOLICITANTE))
            .andExpect(jsonPath("$.dataSolicitacao").value(DEFAULT_DATA_SOLICITACAO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO))
            .andExpect(jsonPath("$.conteudo").value(DEFAULT_CONTEUDO))
            .andExpect(jsonPath("$.protocolo").value(DEFAULT_PROTOCOLO));
    }
    @Test
    @Transactional
    public void getNonExistingSolicitacao() throws Exception {
        // Get the solicitacao
        restSolicitacaoMockMvc.perform(get("/api/solicitacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoService.save(solicitacao);

        int databaseSizeBeforeUpdate = solicitacaoRepository.findAll().size();

        // Update the solicitacao
        Solicitacao updatedSolicitacao = solicitacaoRepository.findById(solicitacao.getId()).get();
        // Disconnect from session so that the updates on updatedSolicitacao are not directly saved in db
        em.detach(updatedSolicitacao);
        updatedSolicitacao
            .idUsuarioIncra(UPDATED_ID_USUARIO_INCRA)
            .cpfCnpjSolicitante(UPDATED_CPF_CNPJ_SOLICITANTE)
            .dataSolicitacao(UPDATED_DATA_SOLICITACAO)
            .situacao(UPDATED_SITUACAO)
            .conteudo(UPDATED_CONTEUDO)
            .protocolo(UPDATED_PROTOCOLO);

        restSolicitacaoMockMvc.perform(put("/api/solicitacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSolicitacao)))
            .andExpect(status().isOk());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeUpdate);
        Solicitacao testSolicitacao = solicitacaoList.get(solicitacaoList.size() - 1);
        assertThat(testSolicitacao.getIdUsuarioIncra()).isEqualTo(UPDATED_ID_USUARIO_INCRA);
        assertThat(testSolicitacao.getCpfCnpjSolicitante()).isEqualTo(UPDATED_CPF_CNPJ_SOLICITANTE);
        assertThat(testSolicitacao.getDataSolicitacao()).isEqualTo(UPDATED_DATA_SOLICITACAO);
        assertThat(testSolicitacao.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testSolicitacao.getConteudo()).isEqualTo(UPDATED_CONTEUDO);
        assertThat(testSolicitacao.getProtocolo()).isEqualTo(UPDATED_PROTOCOLO);
    }

    @Test
    @Transactional
    public void updateNonExistingSolicitacao() throws Exception {
        int databaseSizeBeforeUpdate = solicitacaoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSolicitacaoMockMvc.perform(put("/api/solicitacaos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitacao)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitacao in the database
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSolicitacao() throws Exception {
        // Initialize the database
        solicitacaoService.save(solicitacao);

        int databaseSizeBeforeDelete = solicitacaoRepository.findAll().size();

        // Delete the solicitacao
        restSolicitacaoMockMvc.perform(delete("/api/solicitacaos/{id}", solicitacao.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
        assertThat(solicitacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
