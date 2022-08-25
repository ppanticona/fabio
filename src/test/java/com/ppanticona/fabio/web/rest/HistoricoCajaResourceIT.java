package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.HistoricoCaja;
import com.ppanticona.fabio.repository.HistoricoCajaRepository;
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
 * Integration tests for the {@link HistoricoCajaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HistoricoCajaResourceIT {

    private static final ZonedDateTime DEFAULT_FEC_INI_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INI_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_FIN_VIG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_FIN_VIG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTO_INICIAL_SOLES = 1D;
    private static final Double UPDATED_MONTO_INICIAL_SOLES = 2D;

    private static final Double DEFAULT_MONTO_MAXIMO_SOLES = 1D;
    private static final Double UPDATED_MONTO_MAXIMO_SOLES = 2D;

    private static final Double DEFAULT_MONTO_REAL_SOLES = 1D;
    private static final Double UPDATED_MONTO_REAL_SOLES = 2D;

    private static final Double DEFAULT_MONTO_INICIAL_DOLARES = 1D;
    private static final Double UPDATED_MONTO_INICIAL_DOLARES = 2D;

    private static final Double DEFAULT_MONTO_MAXIMO_DOLARES = 1D;
    private static final Double UPDATED_MONTO_MAXIMO_DOLARES = 2D;

    private static final Double DEFAULT_MONTO_REAL_DOLARES = 1D;
    private static final Double UPDATED_MONTO_REAL_DOLARES = 2D;

    private static final Double DEFAULT_MONTO_REAL_OTROS = 1D;
    private static final Double UPDATED_MONTO_REAL_OTROS = 2D;

    private static final String DEFAULT_USUARIO_ASIGNADO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO_ASIGNADO = "BBBBBBBBBB";

    private static final String DEFAULT_COMENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final Boolean DEFAULT_IND_DEL = false;
    private static final Boolean UPDATED_IND_DEL = true;

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

    private static final String ENTITY_API_URL = "/api/historico-cajas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private HistoricoCajaRepository historicoCajaRepository;

    @Autowired
    private MockMvc restHistoricoCajaMockMvc;

    private HistoricoCaja historicoCaja;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoCaja createEntity() {
        HistoricoCaja historicoCaja = new HistoricoCaja()
            .fecIniVig(DEFAULT_FEC_INI_VIG)
            .fecFinVig(DEFAULT_FEC_FIN_VIG)
            .estado(DEFAULT_ESTADO)
            .montoInicialSoles(DEFAULT_MONTO_INICIAL_SOLES)
            .montoMaximoSoles(DEFAULT_MONTO_MAXIMO_SOLES)
            .montoRealSoles(DEFAULT_MONTO_REAL_SOLES)
            .montoInicialDolares(DEFAULT_MONTO_INICIAL_DOLARES)
            .montoMaximoDolares(DEFAULT_MONTO_MAXIMO_DOLARES)
            .montoRealDolares(DEFAULT_MONTO_REAL_DOLARES)
            .montoRealOtros(DEFAULT_MONTO_REAL_OTROS)
            .usuarioAsignado(DEFAULT_USUARIO_ASIGNADO)
            .comentario(DEFAULT_COMENTARIO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return historicoCaja;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoCaja createUpdatedEntity() {
        HistoricoCaja historicoCaja = new HistoricoCaja()
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .estado(UPDATED_ESTADO)
            .montoInicialSoles(UPDATED_MONTO_INICIAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .montoRealSoles(UPDATED_MONTO_REAL_SOLES)
            .montoInicialDolares(UPDATED_MONTO_INICIAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .montoRealDolares(UPDATED_MONTO_REAL_DOLARES)
            .montoRealOtros(UPDATED_MONTO_REAL_OTROS)
            .usuarioAsignado(UPDATED_USUARIO_ASIGNADO)
            .comentario(UPDATED_COMENTARIO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return historicoCaja;
    }

    @BeforeEach
    public void initTest() {
        historicoCajaRepository.deleteAll();
        historicoCaja = createEntity();
    }

    @Test
    void createHistoricoCaja() throws Exception {
        int databaseSizeBeforeCreate = historicoCajaRepository.findAll().size();
        // Create the HistoricoCaja
        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isCreated());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeCreate + 1);
        HistoricoCaja testHistoricoCaja = historicoCajaList.get(historicoCajaList.size() - 1);
        assertThat(testHistoricoCaja.getFecIniVig()).isEqualTo(DEFAULT_FEC_INI_VIG);
        assertThat(testHistoricoCaja.getFecFinVig()).isEqualTo(DEFAULT_FEC_FIN_VIG);
        assertThat(testHistoricoCaja.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testHistoricoCaja.getMontoInicialSoles()).isEqualTo(DEFAULT_MONTO_INICIAL_SOLES);
        assertThat(testHistoricoCaja.getMontoMaximoSoles()).isEqualTo(DEFAULT_MONTO_MAXIMO_SOLES);
        assertThat(testHistoricoCaja.getMontoRealSoles()).isEqualTo(DEFAULT_MONTO_REAL_SOLES);
        assertThat(testHistoricoCaja.getMontoInicialDolares()).isEqualTo(DEFAULT_MONTO_INICIAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoMaximoDolares()).isEqualTo(DEFAULT_MONTO_MAXIMO_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealDolares()).isEqualTo(DEFAULT_MONTO_REAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealOtros()).isEqualTo(DEFAULT_MONTO_REAL_OTROS);
        assertThat(testHistoricoCaja.getUsuarioAsignado()).isEqualTo(DEFAULT_USUARIO_ASIGNADO);
        assertThat(testHistoricoCaja.getComentario()).isEqualTo(DEFAULT_COMENTARIO);
        assertThat(testHistoricoCaja.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testHistoricoCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testHistoricoCaja.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testHistoricoCaja.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testHistoricoCaja.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testHistoricoCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testHistoricoCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testHistoricoCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createHistoricoCajaWithExistingId() throws Exception {
        // Create the HistoricoCaja with an existing ID
        historicoCaja.setId("existing_id");

        int databaseSizeBeforeCreate = historicoCajaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkFecIniVigIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setFecIniVig(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecFinVigIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setFecFinVig(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setEstado(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoInicialSolesIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setMontoInicialSoles(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoMaximoSolesIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setMontoMaximoSoles(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoInicialDolaresIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setMontoInicialDolares(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMontoMaximoDolaresIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setMontoMaximoDolares(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setVersion(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setIndDel(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setFecCrea(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setUsuCrea(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = historicoCajaRepository.findAll().size();
        // set the field null
        historicoCaja.setIpCrea(null);

        // Create the HistoricoCaja, which fails.

        restHistoricoCajaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isBadRequest());

        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllHistoricoCajas() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        // Get all the historicoCajaList
        restHistoricoCajaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historicoCaja.getId())))
            .andExpect(jsonPath("$.[*].fecIniVig").value(hasItem(sameInstant(DEFAULT_FEC_INI_VIG))))
            .andExpect(jsonPath("$.[*].fecFinVig").value(hasItem(sameInstant(DEFAULT_FEC_FIN_VIG))))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].montoInicialSoles").value(hasItem(DEFAULT_MONTO_INICIAL_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoMaximoSoles").value(hasItem(DEFAULT_MONTO_MAXIMO_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoRealSoles").value(hasItem(DEFAULT_MONTO_REAL_SOLES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoInicialDolares").value(hasItem(DEFAULT_MONTO_INICIAL_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoMaximoDolares").value(hasItem(DEFAULT_MONTO_MAXIMO_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoRealDolares").value(hasItem(DEFAULT_MONTO_REAL_DOLARES.doubleValue())))
            .andExpect(jsonPath("$.[*].montoRealOtros").value(hasItem(DEFAULT_MONTO_REAL_OTROS.doubleValue())))
            .andExpect(jsonPath("$.[*].usuarioAsignado").value(hasItem(DEFAULT_USUARIO_ASIGNADO)))
            .andExpect(jsonPath("$.[*].comentario").value(hasItem(DEFAULT_COMENTARIO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].indDel").value(hasItem(DEFAULT_IND_DEL.booleanValue())))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getHistoricoCaja() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        // Get the historicoCaja
        restHistoricoCajaMockMvc
            .perform(get(ENTITY_API_URL_ID, historicoCaja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historicoCaja.getId()))
            .andExpect(jsonPath("$.fecIniVig").value(sameInstant(DEFAULT_FEC_INI_VIG)))
            .andExpect(jsonPath("$.fecFinVig").value(sameInstant(DEFAULT_FEC_FIN_VIG)))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.montoInicialSoles").value(DEFAULT_MONTO_INICIAL_SOLES.doubleValue()))
            .andExpect(jsonPath("$.montoMaximoSoles").value(DEFAULT_MONTO_MAXIMO_SOLES.doubleValue()))
            .andExpect(jsonPath("$.montoRealSoles").value(DEFAULT_MONTO_REAL_SOLES.doubleValue()))
            .andExpect(jsonPath("$.montoInicialDolares").value(DEFAULT_MONTO_INICIAL_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.montoMaximoDolares").value(DEFAULT_MONTO_MAXIMO_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.montoRealDolares").value(DEFAULT_MONTO_REAL_DOLARES.doubleValue()))
            .andExpect(jsonPath("$.montoRealOtros").value(DEFAULT_MONTO_REAL_OTROS.doubleValue()))
            .andExpect(jsonPath("$.usuarioAsignado").value(DEFAULT_USUARIO_ASIGNADO))
            .andExpect(jsonPath("$.comentario").value(DEFAULT_COMENTARIO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.indDel").value(DEFAULT_IND_DEL.booleanValue()))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingHistoricoCaja() throws Exception {
        // Get the historicoCaja
        restHistoricoCajaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewHistoricoCaja() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();

        // Update the historicoCaja
        HistoricoCaja updatedHistoricoCaja = historicoCajaRepository.findById(historicoCaja.getId()).get();
        updatedHistoricoCaja
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .estado(UPDATED_ESTADO)
            .montoInicialSoles(UPDATED_MONTO_INICIAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .montoRealSoles(UPDATED_MONTO_REAL_SOLES)
            .montoInicialDolares(UPDATED_MONTO_INICIAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .montoRealDolares(UPDATED_MONTO_REAL_DOLARES)
            .montoRealOtros(UPDATED_MONTO_REAL_OTROS)
            .usuarioAsignado(UPDATED_USUARIO_ASIGNADO)
            .comentario(UPDATED_COMENTARIO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restHistoricoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHistoricoCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHistoricoCaja))
            )
            .andExpect(status().isOk());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
        HistoricoCaja testHistoricoCaja = historicoCajaList.get(historicoCajaList.size() - 1);
        assertThat(testHistoricoCaja.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testHistoricoCaja.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testHistoricoCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testHistoricoCaja.getMontoInicialSoles()).isEqualTo(UPDATED_MONTO_INICIAL_SOLES);
        assertThat(testHistoricoCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testHistoricoCaja.getMontoRealSoles()).isEqualTo(UPDATED_MONTO_REAL_SOLES);
        assertThat(testHistoricoCaja.getMontoInicialDolares()).isEqualTo(UPDATED_MONTO_INICIAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealDolares()).isEqualTo(UPDATED_MONTO_REAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealOtros()).isEqualTo(UPDATED_MONTO_REAL_OTROS);
        assertThat(testHistoricoCaja.getUsuarioAsignado()).isEqualTo(UPDATED_USUARIO_ASIGNADO);
        assertThat(testHistoricoCaja.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testHistoricoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testHistoricoCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testHistoricoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testHistoricoCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testHistoricoCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testHistoricoCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testHistoricoCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testHistoricoCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, historicoCaja.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(historicoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(historicoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(historicoCaja)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateHistoricoCajaWithPatch() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();

        // Update the historicoCaja using partial update
        HistoricoCaja partialUpdatedHistoricoCaja = new HistoricoCaja();
        partialUpdatedHistoricoCaja.setId(historicoCaja.getId());

        partialUpdatedHistoricoCaja
            .estado(UPDATED_ESTADO)
            .montoInicialSoles(UPDATED_MONTO_INICIAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .montoInicialDolares(UPDATED_MONTO_INICIAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .montoRealDolares(UPDATED_MONTO_REAL_DOLARES)
            .montoRealOtros(UPDATED_MONTO_REAL_OTROS)
            .usuarioAsignado(UPDATED_USUARIO_ASIGNADO)
            .version(UPDATED_VERSION)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA);

        restHistoricoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoricoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoricoCaja))
            )
            .andExpect(status().isOk());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
        HistoricoCaja testHistoricoCaja = historicoCajaList.get(historicoCajaList.size() - 1);
        assertThat(testHistoricoCaja.getFecIniVig()).isEqualTo(DEFAULT_FEC_INI_VIG);
        assertThat(testHistoricoCaja.getFecFinVig()).isEqualTo(DEFAULT_FEC_FIN_VIG);
        assertThat(testHistoricoCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testHistoricoCaja.getMontoInicialSoles()).isEqualTo(UPDATED_MONTO_INICIAL_SOLES);
        assertThat(testHistoricoCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testHistoricoCaja.getMontoRealSoles()).isEqualTo(DEFAULT_MONTO_REAL_SOLES);
        assertThat(testHistoricoCaja.getMontoInicialDolares()).isEqualTo(UPDATED_MONTO_INICIAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealDolares()).isEqualTo(UPDATED_MONTO_REAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealOtros()).isEqualTo(UPDATED_MONTO_REAL_OTROS);
        assertThat(testHistoricoCaja.getUsuarioAsignado()).isEqualTo(UPDATED_USUARIO_ASIGNADO);
        assertThat(testHistoricoCaja.getComentario()).isEqualTo(DEFAULT_COMENTARIO);
        assertThat(testHistoricoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testHistoricoCaja.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testHistoricoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testHistoricoCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testHistoricoCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testHistoricoCaja.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testHistoricoCaja.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testHistoricoCaja.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateHistoricoCajaWithPatch() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();

        // Update the historicoCaja using partial update
        HistoricoCaja partialUpdatedHistoricoCaja = new HistoricoCaja();
        partialUpdatedHistoricoCaja.setId(historicoCaja.getId());

        partialUpdatedHistoricoCaja
            .fecIniVig(UPDATED_FEC_INI_VIG)
            .fecFinVig(UPDATED_FEC_FIN_VIG)
            .estado(UPDATED_ESTADO)
            .montoInicialSoles(UPDATED_MONTO_INICIAL_SOLES)
            .montoMaximoSoles(UPDATED_MONTO_MAXIMO_SOLES)
            .montoRealSoles(UPDATED_MONTO_REAL_SOLES)
            .montoInicialDolares(UPDATED_MONTO_INICIAL_DOLARES)
            .montoMaximoDolares(UPDATED_MONTO_MAXIMO_DOLARES)
            .montoRealDolares(UPDATED_MONTO_REAL_DOLARES)
            .montoRealOtros(UPDATED_MONTO_REAL_OTROS)
            .usuarioAsignado(UPDATED_USUARIO_ASIGNADO)
            .comentario(UPDATED_COMENTARIO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restHistoricoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoricoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoricoCaja))
            )
            .andExpect(status().isOk());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
        HistoricoCaja testHistoricoCaja = historicoCajaList.get(historicoCajaList.size() - 1);
        assertThat(testHistoricoCaja.getFecIniVig()).isEqualTo(UPDATED_FEC_INI_VIG);
        assertThat(testHistoricoCaja.getFecFinVig()).isEqualTo(UPDATED_FEC_FIN_VIG);
        assertThat(testHistoricoCaja.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testHistoricoCaja.getMontoInicialSoles()).isEqualTo(UPDATED_MONTO_INICIAL_SOLES);
        assertThat(testHistoricoCaja.getMontoMaximoSoles()).isEqualTo(UPDATED_MONTO_MAXIMO_SOLES);
        assertThat(testHistoricoCaja.getMontoRealSoles()).isEqualTo(UPDATED_MONTO_REAL_SOLES);
        assertThat(testHistoricoCaja.getMontoInicialDolares()).isEqualTo(UPDATED_MONTO_INICIAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoMaximoDolares()).isEqualTo(UPDATED_MONTO_MAXIMO_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealDolares()).isEqualTo(UPDATED_MONTO_REAL_DOLARES);
        assertThat(testHistoricoCaja.getMontoRealOtros()).isEqualTo(UPDATED_MONTO_REAL_OTROS);
        assertThat(testHistoricoCaja.getUsuarioAsignado()).isEqualTo(UPDATED_USUARIO_ASIGNADO);
        assertThat(testHistoricoCaja.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testHistoricoCaja.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testHistoricoCaja.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testHistoricoCaja.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testHistoricoCaja.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testHistoricoCaja.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testHistoricoCaja.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testHistoricoCaja.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testHistoricoCaja.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, historicoCaja.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(historicoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(historicoCaja))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamHistoricoCaja() throws Exception {
        int databaseSizeBeforeUpdate = historicoCajaRepository.findAll().size();
        historicoCaja.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoricoCajaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(historicoCaja))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoricoCaja in the database
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteHistoricoCaja() throws Exception {
        // Initialize the database
        historicoCajaRepository.save(historicoCaja);

        int databaseSizeBeforeDelete = historicoCajaRepository.findAll().size();

        // Delete the historicoCaja
        restHistoricoCajaMockMvc
            .perform(delete(ENTITY_API_URL_ID, historicoCaja.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoricoCaja> historicoCajaList = historicoCajaRepository.findAll();
        assertThat(historicoCajaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
