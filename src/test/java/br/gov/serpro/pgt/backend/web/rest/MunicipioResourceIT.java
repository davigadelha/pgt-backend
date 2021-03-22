package br.gov.serpro.pgt.backend.web.rest;

import br.gov.serpro.pgt.backend.PgtBackendApp;
import br.gov.serpro.pgt.backend.domain.Municipio;
import br.gov.serpro.pgt.backend.repository.MunicipioRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MunicipioResource} REST controller.
 */
@SpringBootTest(classes = PgtBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MunicipioResourceIT {

    private static final Long DEFAULT_CODIGO_IBGE = 1L;
    private static final Long UPDATED_CODIGO_IBGE = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_UF = 1L;
    private static final Long UPDATED_ID_UF = 2L;

    private static final Long DEFAULT_CODIGO_TOM = 1L;
    private static final Long UPDATED_CODIGO_TOM = 2L;

    private static final BigDecimal DEFAULT_AREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_AREA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AREA_MODULO_FISCAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_AREA_MODULO_FISCAL = new BigDecimal(2);

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMunicipioMockMvc;

    private Municipio municipio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipio createEntity(EntityManager em) {
        Municipio municipio = new Municipio()
            .codigoIbge(DEFAULT_CODIGO_IBGE)
            .nome(DEFAULT_NOME)
            .sigla(DEFAULT_SIGLA)
            .idUf(DEFAULT_ID_UF)
            .codigoTom(DEFAULT_CODIGO_TOM)
            .area(DEFAULT_AREA)
            .areaModuloFiscal(DEFAULT_AREA_MODULO_FISCAL);
        return municipio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipio createUpdatedEntity(EntityManager em) {
        Municipio municipio = new Municipio()
            .codigoIbge(UPDATED_CODIGO_IBGE)
            .nome(UPDATED_NOME)
            .sigla(UPDATED_SIGLA)
            .idUf(UPDATED_ID_UF)
            .codigoTom(UPDATED_CODIGO_TOM)
            .area(UPDATED_AREA)
            .areaModuloFiscal(UPDATED_AREA_MODULO_FISCAL);
        return municipio;
    }

    @BeforeEach
    public void initTest() {
        municipio = createEntity(em);
    }

    @Test
    @Transactional
    public void createMunicipio() throws Exception {
        int databaseSizeBeforeCreate = municipioRepository.findAll().size();
        // Create the Municipio
        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipio)))
            .andExpect(status().isCreated());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeCreate + 1);
        Municipio testMunicipio = municipioList.get(municipioList.size() - 1);
        assertThat(testMunicipio.getCodigoIbge()).isEqualTo(DEFAULT_CODIGO_IBGE);
        assertThat(testMunicipio.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testMunicipio.getSigla()).isEqualTo(DEFAULT_SIGLA);
        assertThat(testMunicipio.getIdUf()).isEqualTo(DEFAULT_ID_UF);
        assertThat(testMunicipio.getCodigoTom()).isEqualTo(DEFAULT_CODIGO_TOM);
        assertThat(testMunicipio.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testMunicipio.getAreaModuloFiscal()).isEqualTo(DEFAULT_AREA_MODULO_FISCAL);
    }

    @Test
    @Transactional
    public void createMunicipioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = municipioRepository.findAll().size();

        // Create the Municipio with an existing ID
        municipio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMunicipioMockMvc.perform(post("/api/municipios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipio)))
            .andExpect(status().isBadRequest());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMunicipios() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList
        restMunicipioMockMvc.perform(get("/api/municipios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipio.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoIbge").value(hasItem(DEFAULT_CODIGO_IBGE.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)))
            .andExpect(jsonPath("$.[*].idUf").value(hasItem(DEFAULT_ID_UF.intValue())))
            .andExpect(jsonPath("$.[*].codigoTom").value(hasItem(DEFAULT_CODIGO_TOM.intValue())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.intValue())))
            .andExpect(jsonPath("$.[*].areaModuloFiscal").value(hasItem(DEFAULT_AREA_MODULO_FISCAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", municipio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(municipio.getId().intValue()))
            .andExpect(jsonPath("$.codigoIbge").value(DEFAULT_CODIGO_IBGE.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA))
            .andExpect(jsonPath("$.idUf").value(DEFAULT_ID_UF.intValue()))
            .andExpect(jsonPath("$.codigoTom").value(DEFAULT_CODIGO_TOM.intValue()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.intValue()))
            .andExpect(jsonPath("$.areaModuloFiscal").value(DEFAULT_AREA_MODULO_FISCAL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMunicipio() throws Exception {
        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        int databaseSizeBeforeUpdate = municipioRepository.findAll().size();

        // Update the municipio
        Municipio updatedMunicipio = municipioRepository.findById(municipio.getId()).get();
        // Disconnect from session so that the updates on updatedMunicipio are not directly saved in db
        em.detach(updatedMunicipio);
        updatedMunicipio
            .codigoIbge(UPDATED_CODIGO_IBGE)
            .nome(UPDATED_NOME)
            .sigla(UPDATED_SIGLA)
            .idUf(UPDATED_ID_UF)
            .codigoTom(UPDATED_CODIGO_TOM)
            .area(UPDATED_AREA)
            .areaModuloFiscal(UPDATED_AREA_MODULO_FISCAL);

        restMunicipioMockMvc.perform(put("/api/municipios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMunicipio)))
            .andExpect(status().isOk());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeUpdate);
        Municipio testMunicipio = municipioList.get(municipioList.size() - 1);
        assertThat(testMunicipio.getCodigoIbge()).isEqualTo(UPDATED_CODIGO_IBGE);
        assertThat(testMunicipio.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testMunicipio.getSigla()).isEqualTo(UPDATED_SIGLA);
        assertThat(testMunicipio.getIdUf()).isEqualTo(UPDATED_ID_UF);
        assertThat(testMunicipio.getCodigoTom()).isEqualTo(UPDATED_CODIGO_TOM);
        assertThat(testMunicipio.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testMunicipio.getAreaModuloFiscal()).isEqualTo(UPDATED_AREA_MODULO_FISCAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMunicipio() throws Exception {
        int databaseSizeBeforeUpdate = municipioRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMunicipioMockMvc.perform(put("/api/municipios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipio)))
            .andExpect(status().isBadRequest());

        // Validate the Municipio in the database
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        int databaseSizeBeforeDelete = municipioRepository.findAll().size();

        // Delete the municipio
        restMunicipioMockMvc.perform(delete("/api/municipios/{id}", municipio.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Municipio> municipioList = municipioRepository.findAll();
        assertThat(municipioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
