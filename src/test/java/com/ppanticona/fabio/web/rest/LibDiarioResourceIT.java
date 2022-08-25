package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.LibDiario;
import com.ppanticona.fabio.repository.LibDiarioRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link LibDiarioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LibDiarioResourceIT {

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_CUO = "AAAAAAAAAA";
    private static final String UPDATED_CUO = "BBBBBBBBBB";

    private static final String DEFAULT_ASIENT_CONTAB = "AAAAAAAAAA";
    private static final String UPDATED_ASIENT_CONTAB = "BBBBBBBBBB";

    private static final Integer DEFAULT_COD_CTA_CONTABLE = 1;
    private static final Integer UPDATED_COD_CTA_CONTABLE = 2;

    private static final String DEFAULT_COD_UNID_OPER = "AAAAAAAAAA";
    private static final String UPDATED_COD_UNID_OPER = "BBBBBBBBBB";

    private static final String DEFAULT_COD_CENTRO_CUI = "AAAAAAAAAA";
    private static final String UPDATED_COD_CENTRO_CUI = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_MONEDA_ORI = "AAAAAAAAAA";
    private static final String UPDATED_TIP_MONEDA_ORI = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_DOC_IDEN_EMI = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC_IDEN_EMI = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC_IDEN_EMI = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC_IDEN_EMI = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_COMP_DOC_ASOC = "AAAAAAAAAA";
    private static final String UPDATED_TIP_COMP_DOC_ASOC = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_SER_COMP_DOC_ASOC = "AAAAAAAAAA";
    private static final String UPDATED_NRO_SER_COMP_DOC_ASOC = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_COMP_DOC_ASOC = "AAAAAAAAAA";
    private static final String UPDATED_NRO_COMP_DOC_ASOC = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_CONTABLE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_CONTABLE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_VENC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_VENC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_OPE_EMI = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_OPE_EMI = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESC_OPERAC = "AAAAAAAAAA";
    private static final String UPDATED_DESC_OPERAC = "BBBBBBBBBB";

    private static final String DEFAULT_GLOSA_REF = "AAAAAAAAAA";
    private static final String UPDATED_GLOSA_REF = "BBBBBBBBBB";

    private static final Double DEFAULT_DEBE = 1D;
    private static final Double UPDATED_DEBE = 2D;

    private static final Double DEFAULT_HABER = 1D;
    private static final Double UPDATED_HABER = 2D;

    private static final String DEFAULT_DATO_ESTRUCT = "AAAAAAAAAA";
    private static final String UPDATED_DATO_ESTRUCT = "BBBBBBBBBB";

    private static final Integer DEFAULT_IND_EST_OPE = 1;
    private static final Integer UPDATED_IND_EST_OPE = 2;

    private static final String DEFAULT_CAMPO_LIBRE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_LIBRE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_CREA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_CREA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USU_CREA = "AAAAAAAAAA";
    private static final String UPDATED_USU_CREA = "BBBBBBBBBB";

    private static final String DEFAULT_IP_CREA = "AAAAAAAAAA";
    private static final String UPDATED_IP_CREA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_MODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_MODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USU_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_USU_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_IP_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_IP_MODIF = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/lib-diarios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private LibDiarioRepository libDiarioRepository;

    @Autowired
    private MockMvc restLibDiarioMockMvc;

    private LibDiario libDiario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LibDiario createEntity() {
        LibDiario libDiario = new LibDiario()
            .periodo(DEFAULT_PERIODO)
            .cuo(DEFAULT_CUO)
            .asientContab(DEFAULT_ASIENT_CONTAB)
            .codCtaContable(DEFAULT_COD_CTA_CONTABLE)
            .codUnidOper(DEFAULT_COD_UNID_OPER)
            .codCentroCui(DEFAULT_COD_CENTRO_CUI)
            .tipMonedaOri(DEFAULT_TIP_MONEDA_ORI)
            .tipDocIdenEmi(DEFAULT_TIP_DOC_IDEN_EMI)
            .nroDocIdenEmi(DEFAULT_NRO_DOC_IDEN_EMI)
            .tipCompDocAsoc(DEFAULT_TIP_COMP_DOC_ASOC)
            .nroSerCompDocAsoc(DEFAULT_NRO_SER_COMP_DOC_ASOC)
            .nroCompDocAsoc(DEFAULT_NRO_COMP_DOC_ASOC)
            .fecContable(DEFAULT_FEC_CONTABLE)
            .fecVenc(DEFAULT_FEC_VENC)
            .fecOpeEmi(DEFAULT_FEC_OPE_EMI)
            .descOperac(DEFAULT_DESC_OPERAC)
            .glosaRef(DEFAULT_GLOSA_REF)
            .debe(DEFAULT_DEBE)
            .haber(DEFAULT_HABER)
            .datoEstruct(DEFAULT_DATO_ESTRUCT)
            .indEstOpe(DEFAULT_IND_EST_OPE)
            .campoLibre(DEFAULT_CAMPO_LIBRE)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return libDiario;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LibDiario createUpdatedEntity() {
        LibDiario libDiario = new LibDiario()
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .codCtaContable(UPDATED_COD_CTA_CONTABLE)
            .codUnidOper(UPDATED_COD_UNID_OPER)
            .codCentroCui(UPDATED_COD_CENTRO_CUI)
            .tipMonedaOri(UPDATED_TIP_MONEDA_ORI)
            .tipDocIdenEmi(UPDATED_TIP_DOC_IDEN_EMI)
            .nroDocIdenEmi(UPDATED_NRO_DOC_IDEN_EMI)
            .tipCompDocAsoc(UPDATED_TIP_COMP_DOC_ASOC)
            .nroSerCompDocAsoc(UPDATED_NRO_SER_COMP_DOC_ASOC)
            .nroCompDocAsoc(UPDATED_NRO_COMP_DOC_ASOC)
            .fecContable(UPDATED_FEC_CONTABLE)
            .fecVenc(UPDATED_FEC_VENC)
            .fecOpeEmi(UPDATED_FEC_OPE_EMI)
            .descOperac(UPDATED_DESC_OPERAC)
            .glosaRef(UPDATED_GLOSA_REF)
            .debe(UPDATED_DEBE)
            .haber(UPDATED_HABER)
            .datoEstruct(UPDATED_DATO_ESTRUCT)
            .indEstOpe(UPDATED_IND_EST_OPE)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return libDiario;
    }

    @BeforeEach
    public void initTest() {
        libDiarioRepository.deleteAll();
        libDiario = createEntity();
    }

    @Test
    void createLibDiario() throws Exception {
        int databaseSizeBeforeCreate = libDiarioRepository.findAll().size();
        // Create the LibDiario
        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isCreated());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeCreate + 1);
        LibDiario testLibDiario = libDiarioList.get(libDiarioList.size() - 1);
        assertThat(testLibDiario.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testLibDiario.getCuo()).isEqualTo(DEFAULT_CUO);
        assertThat(testLibDiario.getAsientContab()).isEqualTo(DEFAULT_ASIENT_CONTAB);
        assertThat(testLibDiario.getCodCtaContable()).isEqualTo(DEFAULT_COD_CTA_CONTABLE);
        assertThat(testLibDiario.getCodUnidOper()).isEqualTo(DEFAULT_COD_UNID_OPER);
        assertThat(testLibDiario.getCodCentroCui()).isEqualTo(DEFAULT_COD_CENTRO_CUI);
        assertThat(testLibDiario.getTipMonedaOri()).isEqualTo(DEFAULT_TIP_MONEDA_ORI);
        assertThat(testLibDiario.getTipDocIdenEmi()).isEqualTo(DEFAULT_TIP_DOC_IDEN_EMI);
        assertThat(testLibDiario.getNroDocIdenEmi()).isEqualTo(DEFAULT_NRO_DOC_IDEN_EMI);
        assertThat(testLibDiario.getTipCompDocAsoc()).isEqualTo(DEFAULT_TIP_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroSerCompDocAsoc()).isEqualTo(DEFAULT_NRO_SER_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroCompDocAsoc()).isEqualTo(DEFAULT_NRO_COMP_DOC_ASOC);
        assertThat(testLibDiario.getFecContable()).isEqualTo(DEFAULT_FEC_CONTABLE);
        assertThat(testLibDiario.getFecVenc()).isEqualTo(DEFAULT_FEC_VENC);
        assertThat(testLibDiario.getFecOpeEmi()).isEqualTo(DEFAULT_FEC_OPE_EMI);
        assertThat(testLibDiario.getDescOperac()).isEqualTo(DEFAULT_DESC_OPERAC);
        assertThat(testLibDiario.getGlosaRef()).isEqualTo(DEFAULT_GLOSA_REF);
        assertThat(testLibDiario.getDebe()).isEqualTo(DEFAULT_DEBE);
        assertThat(testLibDiario.getHaber()).isEqualTo(DEFAULT_HABER);
        assertThat(testLibDiario.getDatoEstruct()).isEqualTo(DEFAULT_DATO_ESTRUCT);
        assertThat(testLibDiario.getIndEstOpe()).isEqualTo(DEFAULT_IND_EST_OPE);
        assertThat(testLibDiario.getCampoLibre()).isEqualTo(DEFAULT_CAMPO_LIBRE);
        assertThat(testLibDiario.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testLibDiario.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testLibDiario.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testLibDiario.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testLibDiario.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testLibDiario.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createLibDiarioWithExistingId() throws Exception {
        // Create the LibDiario with an existing ID
        libDiario.setId("existing_id");

        int databaseSizeBeforeCreate = libDiarioRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setPeriodo(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCuoIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setCuo(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodCtaContableIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setCodCtaContable(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTipCompDocAsocIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setTipCompDocAsoc(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroCompDocAsocIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setNroCompDocAsoc(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecOpeEmiIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setFecOpeEmi(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescOperacIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setDescOperac(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDebeIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setDebe(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkHaberIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setHaber(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndEstOpeIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setIndEstOpe(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setFecCrea(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setUsuCrea(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = libDiarioRepository.findAll().size();
        // set the field null
        libDiario.setIpCrea(null);

        // Create the LibDiario, which fails.

        restLibDiarioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isBadRequest());

        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllLibDiarios() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        // Get all the libDiarioList
        restLibDiarioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(libDiario.getId())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO)))
            .andExpect(jsonPath("$.[*].cuo").value(hasItem(DEFAULT_CUO)))
            .andExpect(jsonPath("$.[*].asientContab").value(hasItem(DEFAULT_ASIENT_CONTAB)))
            .andExpect(jsonPath("$.[*].codCtaContable").value(hasItem(DEFAULT_COD_CTA_CONTABLE)))
            .andExpect(jsonPath("$.[*].codUnidOper").value(hasItem(DEFAULT_COD_UNID_OPER)))
            .andExpect(jsonPath("$.[*].codCentroCui").value(hasItem(DEFAULT_COD_CENTRO_CUI)))
            .andExpect(jsonPath("$.[*].tipMonedaOri").value(hasItem(DEFAULT_TIP_MONEDA_ORI)))
            .andExpect(jsonPath("$.[*].tipDocIdenEmi").value(hasItem(DEFAULT_TIP_DOC_IDEN_EMI)))
            .andExpect(jsonPath("$.[*].nroDocIdenEmi").value(hasItem(DEFAULT_NRO_DOC_IDEN_EMI)))
            .andExpect(jsonPath("$.[*].tipCompDocAsoc").value(hasItem(DEFAULT_TIP_COMP_DOC_ASOC)))
            .andExpect(jsonPath("$.[*].nroSerCompDocAsoc").value(hasItem(DEFAULT_NRO_SER_COMP_DOC_ASOC)))
            .andExpect(jsonPath("$.[*].nroCompDocAsoc").value(hasItem(DEFAULT_NRO_COMP_DOC_ASOC)))
            .andExpect(jsonPath("$.[*].fecContable").value(hasItem(sameInstant(DEFAULT_FEC_CONTABLE))))
            .andExpect(jsonPath("$.[*].fecVenc").value(hasItem(sameInstant(DEFAULT_FEC_VENC))))
            .andExpect(jsonPath("$.[*].fecOpeEmi").value(hasItem(sameInstant(DEFAULT_FEC_OPE_EMI))))
            .andExpect(jsonPath("$.[*].descOperac").value(hasItem(DEFAULT_DESC_OPERAC)))
            .andExpect(jsonPath("$.[*].glosaRef").value(hasItem(DEFAULT_GLOSA_REF)))
            .andExpect(jsonPath("$.[*].debe").value(hasItem(DEFAULT_DEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].haber").value(hasItem(DEFAULT_HABER.doubleValue())))
            .andExpect(jsonPath("$.[*].datoEstruct").value(hasItem(DEFAULT_DATO_ESTRUCT)))
            .andExpect(jsonPath("$.[*].indEstOpe").value(hasItem(DEFAULT_IND_EST_OPE)))
            .andExpect(jsonPath("$.[*].campoLibre").value(hasItem(DEFAULT_CAMPO_LIBRE)))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getLibDiario() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        // Get the libDiario
        restLibDiarioMockMvc
            .perform(get(ENTITY_API_URL_ID, libDiario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(libDiario.getId()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO))
            .andExpect(jsonPath("$.cuo").value(DEFAULT_CUO))
            .andExpect(jsonPath("$.asientContab").value(DEFAULT_ASIENT_CONTAB))
            .andExpect(jsonPath("$.codCtaContable").value(DEFAULT_COD_CTA_CONTABLE))
            .andExpect(jsonPath("$.codUnidOper").value(DEFAULT_COD_UNID_OPER))
            .andExpect(jsonPath("$.codCentroCui").value(DEFAULT_COD_CENTRO_CUI))
            .andExpect(jsonPath("$.tipMonedaOri").value(DEFAULT_TIP_MONEDA_ORI))
            .andExpect(jsonPath("$.tipDocIdenEmi").value(DEFAULT_TIP_DOC_IDEN_EMI))
            .andExpect(jsonPath("$.nroDocIdenEmi").value(DEFAULT_NRO_DOC_IDEN_EMI))
            .andExpect(jsonPath("$.tipCompDocAsoc").value(DEFAULT_TIP_COMP_DOC_ASOC))
            .andExpect(jsonPath("$.nroSerCompDocAsoc").value(DEFAULT_NRO_SER_COMP_DOC_ASOC))
            .andExpect(jsonPath("$.nroCompDocAsoc").value(DEFAULT_NRO_COMP_DOC_ASOC))
            .andExpect(jsonPath("$.fecContable").value(sameInstant(DEFAULT_FEC_CONTABLE)))
            .andExpect(jsonPath("$.fecVenc").value(sameInstant(DEFAULT_FEC_VENC)))
            .andExpect(jsonPath("$.fecOpeEmi").value(sameInstant(DEFAULT_FEC_OPE_EMI)))
            .andExpect(jsonPath("$.descOperac").value(DEFAULT_DESC_OPERAC))
            .andExpect(jsonPath("$.glosaRef").value(DEFAULT_GLOSA_REF))
            .andExpect(jsonPath("$.debe").value(DEFAULT_DEBE.doubleValue()))
            .andExpect(jsonPath("$.haber").value(DEFAULT_HABER.doubleValue()))
            .andExpect(jsonPath("$.datoEstruct").value(DEFAULT_DATO_ESTRUCT))
            .andExpect(jsonPath("$.indEstOpe").value(DEFAULT_IND_EST_OPE))
            .andExpect(jsonPath("$.campoLibre").value(DEFAULT_CAMPO_LIBRE))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingLibDiario() throws Exception {
        // Get the libDiario
        restLibDiarioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewLibDiario() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();

        // Update the libDiario
        LibDiario updatedLibDiario = libDiarioRepository.findById(libDiario.getId()).get();
        updatedLibDiario
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .codCtaContable(UPDATED_COD_CTA_CONTABLE)
            .codUnidOper(UPDATED_COD_UNID_OPER)
            .codCentroCui(UPDATED_COD_CENTRO_CUI)
            .tipMonedaOri(UPDATED_TIP_MONEDA_ORI)
            .tipDocIdenEmi(UPDATED_TIP_DOC_IDEN_EMI)
            .nroDocIdenEmi(UPDATED_NRO_DOC_IDEN_EMI)
            .tipCompDocAsoc(UPDATED_TIP_COMP_DOC_ASOC)
            .nroSerCompDocAsoc(UPDATED_NRO_SER_COMP_DOC_ASOC)
            .nroCompDocAsoc(UPDATED_NRO_COMP_DOC_ASOC)
            .fecContable(UPDATED_FEC_CONTABLE)
            .fecVenc(UPDATED_FEC_VENC)
            .fecOpeEmi(UPDATED_FEC_OPE_EMI)
            .descOperac(UPDATED_DESC_OPERAC)
            .glosaRef(UPDATED_GLOSA_REF)
            .debe(UPDATED_DEBE)
            .haber(UPDATED_HABER)
            .datoEstruct(UPDATED_DATO_ESTRUCT)
            .indEstOpe(UPDATED_IND_EST_OPE)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restLibDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLibDiario.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLibDiario))
            )
            .andExpect(status().isOk());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
        LibDiario testLibDiario = libDiarioList.get(libDiarioList.size() - 1);
        assertThat(testLibDiario.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testLibDiario.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testLibDiario.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testLibDiario.getCodCtaContable()).isEqualTo(UPDATED_COD_CTA_CONTABLE);
        assertThat(testLibDiario.getCodUnidOper()).isEqualTo(UPDATED_COD_UNID_OPER);
        assertThat(testLibDiario.getCodCentroCui()).isEqualTo(UPDATED_COD_CENTRO_CUI);
        assertThat(testLibDiario.getTipMonedaOri()).isEqualTo(UPDATED_TIP_MONEDA_ORI);
        assertThat(testLibDiario.getTipDocIdenEmi()).isEqualTo(UPDATED_TIP_DOC_IDEN_EMI);
        assertThat(testLibDiario.getNroDocIdenEmi()).isEqualTo(UPDATED_NRO_DOC_IDEN_EMI);
        assertThat(testLibDiario.getTipCompDocAsoc()).isEqualTo(UPDATED_TIP_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroSerCompDocAsoc()).isEqualTo(UPDATED_NRO_SER_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroCompDocAsoc()).isEqualTo(UPDATED_NRO_COMP_DOC_ASOC);
        assertThat(testLibDiario.getFecContable()).isEqualTo(UPDATED_FEC_CONTABLE);
        assertThat(testLibDiario.getFecVenc()).isEqualTo(UPDATED_FEC_VENC);
        assertThat(testLibDiario.getFecOpeEmi()).isEqualTo(UPDATED_FEC_OPE_EMI);
        assertThat(testLibDiario.getDescOperac()).isEqualTo(UPDATED_DESC_OPERAC);
        assertThat(testLibDiario.getGlosaRef()).isEqualTo(UPDATED_GLOSA_REF);
        assertThat(testLibDiario.getDebe()).isEqualTo(UPDATED_DEBE);
        assertThat(testLibDiario.getHaber()).isEqualTo(UPDATED_HABER);
        assertThat(testLibDiario.getDatoEstruct()).isEqualTo(UPDATED_DATO_ESTRUCT);
        assertThat(testLibDiario.getIndEstOpe()).isEqualTo(UPDATED_IND_EST_OPE);
        assertThat(testLibDiario.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testLibDiario.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testLibDiario.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testLibDiario.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testLibDiario.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testLibDiario.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testLibDiario.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, libDiario.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(libDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(libDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(libDiario)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLibDiarioWithPatch() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();

        // Update the libDiario using partial update
        LibDiario partialUpdatedLibDiario = new LibDiario();
        partialUpdatedLibDiario.setId(libDiario.getId());

        partialUpdatedLibDiario
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .tipDocIdenEmi(UPDATED_TIP_DOC_IDEN_EMI)
            .nroDocIdenEmi(UPDATED_NRO_DOC_IDEN_EMI)
            .tipCompDocAsoc(UPDATED_TIP_COMP_DOC_ASOC)
            .nroSerCompDocAsoc(UPDATED_NRO_SER_COMP_DOC_ASOC)
            .nroCompDocAsoc(UPDATED_NRO_COMP_DOC_ASOC)
            .fecContable(UPDATED_FEC_CONTABLE)
            .fecVenc(UPDATED_FEC_VENC)
            .haber(UPDATED_HABER)
            .indEstOpe(UPDATED_IND_EST_OPE)
            .usuCrea(UPDATED_USU_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restLibDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLibDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLibDiario))
            )
            .andExpect(status().isOk());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
        LibDiario testLibDiario = libDiarioList.get(libDiarioList.size() - 1);
        assertThat(testLibDiario.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testLibDiario.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testLibDiario.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testLibDiario.getCodCtaContable()).isEqualTo(DEFAULT_COD_CTA_CONTABLE);
        assertThat(testLibDiario.getCodUnidOper()).isEqualTo(DEFAULT_COD_UNID_OPER);
        assertThat(testLibDiario.getCodCentroCui()).isEqualTo(DEFAULT_COD_CENTRO_CUI);
        assertThat(testLibDiario.getTipMonedaOri()).isEqualTo(DEFAULT_TIP_MONEDA_ORI);
        assertThat(testLibDiario.getTipDocIdenEmi()).isEqualTo(UPDATED_TIP_DOC_IDEN_EMI);
        assertThat(testLibDiario.getNroDocIdenEmi()).isEqualTo(UPDATED_NRO_DOC_IDEN_EMI);
        assertThat(testLibDiario.getTipCompDocAsoc()).isEqualTo(UPDATED_TIP_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroSerCompDocAsoc()).isEqualTo(UPDATED_NRO_SER_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroCompDocAsoc()).isEqualTo(UPDATED_NRO_COMP_DOC_ASOC);
        assertThat(testLibDiario.getFecContable()).isEqualTo(UPDATED_FEC_CONTABLE);
        assertThat(testLibDiario.getFecVenc()).isEqualTo(UPDATED_FEC_VENC);
        assertThat(testLibDiario.getFecOpeEmi()).isEqualTo(DEFAULT_FEC_OPE_EMI);
        assertThat(testLibDiario.getDescOperac()).isEqualTo(DEFAULT_DESC_OPERAC);
        assertThat(testLibDiario.getGlosaRef()).isEqualTo(DEFAULT_GLOSA_REF);
        assertThat(testLibDiario.getDebe()).isEqualTo(DEFAULT_DEBE);
        assertThat(testLibDiario.getHaber()).isEqualTo(UPDATED_HABER);
        assertThat(testLibDiario.getDatoEstruct()).isEqualTo(DEFAULT_DATO_ESTRUCT);
        assertThat(testLibDiario.getIndEstOpe()).isEqualTo(UPDATED_IND_EST_OPE);
        assertThat(testLibDiario.getCampoLibre()).isEqualTo(DEFAULT_CAMPO_LIBRE);
        assertThat(testLibDiario.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testLibDiario.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testLibDiario.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testLibDiario.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testLibDiario.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testLibDiario.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateLibDiarioWithPatch() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();

        // Update the libDiario using partial update
        LibDiario partialUpdatedLibDiario = new LibDiario();
        partialUpdatedLibDiario.setId(libDiario.getId());

        partialUpdatedLibDiario
            .periodo(UPDATED_PERIODO)
            .cuo(UPDATED_CUO)
            .asientContab(UPDATED_ASIENT_CONTAB)
            .codCtaContable(UPDATED_COD_CTA_CONTABLE)
            .codUnidOper(UPDATED_COD_UNID_OPER)
            .codCentroCui(UPDATED_COD_CENTRO_CUI)
            .tipMonedaOri(UPDATED_TIP_MONEDA_ORI)
            .tipDocIdenEmi(UPDATED_TIP_DOC_IDEN_EMI)
            .nroDocIdenEmi(UPDATED_NRO_DOC_IDEN_EMI)
            .tipCompDocAsoc(UPDATED_TIP_COMP_DOC_ASOC)
            .nroSerCompDocAsoc(UPDATED_NRO_SER_COMP_DOC_ASOC)
            .nroCompDocAsoc(UPDATED_NRO_COMP_DOC_ASOC)
            .fecContable(UPDATED_FEC_CONTABLE)
            .fecVenc(UPDATED_FEC_VENC)
            .fecOpeEmi(UPDATED_FEC_OPE_EMI)
            .descOperac(UPDATED_DESC_OPERAC)
            .glosaRef(UPDATED_GLOSA_REF)
            .debe(UPDATED_DEBE)
            .haber(UPDATED_HABER)
            .datoEstruct(UPDATED_DATO_ESTRUCT)
            .indEstOpe(UPDATED_IND_EST_OPE)
            .campoLibre(UPDATED_CAMPO_LIBRE)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restLibDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLibDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLibDiario))
            )
            .andExpect(status().isOk());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
        LibDiario testLibDiario = libDiarioList.get(libDiarioList.size() - 1);
        assertThat(testLibDiario.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testLibDiario.getCuo()).isEqualTo(UPDATED_CUO);
        assertThat(testLibDiario.getAsientContab()).isEqualTo(UPDATED_ASIENT_CONTAB);
        assertThat(testLibDiario.getCodCtaContable()).isEqualTo(UPDATED_COD_CTA_CONTABLE);
        assertThat(testLibDiario.getCodUnidOper()).isEqualTo(UPDATED_COD_UNID_OPER);
        assertThat(testLibDiario.getCodCentroCui()).isEqualTo(UPDATED_COD_CENTRO_CUI);
        assertThat(testLibDiario.getTipMonedaOri()).isEqualTo(UPDATED_TIP_MONEDA_ORI);
        assertThat(testLibDiario.getTipDocIdenEmi()).isEqualTo(UPDATED_TIP_DOC_IDEN_EMI);
        assertThat(testLibDiario.getNroDocIdenEmi()).isEqualTo(UPDATED_NRO_DOC_IDEN_EMI);
        assertThat(testLibDiario.getTipCompDocAsoc()).isEqualTo(UPDATED_TIP_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroSerCompDocAsoc()).isEqualTo(UPDATED_NRO_SER_COMP_DOC_ASOC);
        assertThat(testLibDiario.getNroCompDocAsoc()).isEqualTo(UPDATED_NRO_COMP_DOC_ASOC);
        assertThat(testLibDiario.getFecContable()).isEqualTo(UPDATED_FEC_CONTABLE);
        assertThat(testLibDiario.getFecVenc()).isEqualTo(UPDATED_FEC_VENC);
        assertThat(testLibDiario.getFecOpeEmi()).isEqualTo(UPDATED_FEC_OPE_EMI);
        assertThat(testLibDiario.getDescOperac()).isEqualTo(UPDATED_DESC_OPERAC);
        assertThat(testLibDiario.getGlosaRef()).isEqualTo(UPDATED_GLOSA_REF);
        assertThat(testLibDiario.getDebe()).isEqualTo(UPDATED_DEBE);
        assertThat(testLibDiario.getHaber()).isEqualTo(UPDATED_HABER);
        assertThat(testLibDiario.getDatoEstruct()).isEqualTo(UPDATED_DATO_ESTRUCT);
        assertThat(testLibDiario.getIndEstOpe()).isEqualTo(UPDATED_IND_EST_OPE);
        assertThat(testLibDiario.getCampoLibre()).isEqualTo(UPDATED_CAMPO_LIBRE);
        assertThat(testLibDiario.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testLibDiario.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testLibDiario.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testLibDiario.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testLibDiario.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testLibDiario.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, libDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(libDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(libDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLibDiario() throws Exception {
        int databaseSizeBeforeUpdate = libDiarioRepository.findAll().size();
        libDiario.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLibDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(libDiario))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LibDiario in the database
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLibDiario() throws Exception {
        // Initialize the database
        libDiarioRepository.save(libDiario);

        int databaseSizeBeforeDelete = libDiarioRepository.findAll().size();

        // Delete the libDiario
        restLibDiarioMockMvc
            .perform(delete(ENTITY_API_URL_ID, libDiario.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LibDiario> libDiarioList = libDiarioRepository.findAll();
        assertThat(libDiarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
