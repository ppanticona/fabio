package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.DetalleOrden;
import com.ppanticona.fabio.repository.DetalleOrdenRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link DetalleOrdenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetalleOrdenResourceIT {

    private static final Double DEFAULT_CANTIDAD = 1D;
    private static final Double UPDATED_CANTIDAD = 2D;

    private static final Double DEFAULT_VAL_UNI = 1D;
    private static final Double UPDATED_VAL_UNI = 2D;

    private static final Double DEFAULT_DCTO = 1D;
    private static final Double UPDATED_DCTO = 2D;

    private static final Double DEFAULT_SUBTOTAL = 1D;
    private static final Double UPDATED_SUBTOTAL = 2D;

    private static final String DEFAULT_OBSERVACION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACION = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/detalle-ordens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private MockMvc restDetalleOrdenMockMvc;

    private DetalleOrden detalleOrden;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetalleOrden createEntity() {
        DetalleOrden detalleOrden = new DetalleOrden()
            .cantidad(DEFAULT_CANTIDAD)
            .valUni(DEFAULT_VAL_UNI)
            .dcto(DEFAULT_DCTO)
            .subtotal(DEFAULT_SUBTOTAL)
            .observacion(DEFAULT_OBSERVACION)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return detalleOrden;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetalleOrden createUpdatedEntity() {
        DetalleOrden detalleOrden = new DetalleOrden()
            .cantidad(UPDATED_CANTIDAD)
            .valUni(UPDATED_VAL_UNI)
            .dcto(UPDATED_DCTO)
            .subtotal(UPDATED_SUBTOTAL)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return detalleOrden;
    }

    @BeforeEach
    public void initTest() {
        detalleOrdenRepository.deleteAll();
        detalleOrden = createEntity();
    }

    @Test
    void createDetalleOrden() throws Exception {
        int databaseSizeBeforeCreate = detalleOrdenRepository.findAll().size();
        // Create the DetalleOrden
        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isCreated());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeCreate + 1);
        DetalleOrden testDetalleOrden = detalleOrdenList.get(detalleOrdenList.size() - 1);
        assertThat(testDetalleOrden.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testDetalleOrden.getValUni()).isEqualTo(DEFAULT_VAL_UNI);
        assertThat(testDetalleOrden.getDcto()).isEqualTo(DEFAULT_DCTO);
        assertThat(testDetalleOrden.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testDetalleOrden.getObservacion()).isEqualTo(DEFAULT_OBSERVACION);
        assertThat(testDetalleOrden.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testDetalleOrden.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testDetalleOrden.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testDetalleOrden.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testDetalleOrden.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testDetalleOrden.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testDetalleOrden.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetalleOrden.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetalleOrden.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createDetalleOrdenWithExistingId() throws Exception {
        // Create the DetalleOrden with an existing ID
        detalleOrden.setId("existing_id");

        int databaseSizeBeforeCreate = detalleOrdenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCantidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setCantidad(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkValUniIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setValUni(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDctoIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setDcto(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSubtotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setSubtotal(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setEstado(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setVersion(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setIndDel(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setFecCrea(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setUsuCrea(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOrdenRepository.findAll().size();
        // set the field null
        detalleOrden.setIpCrea(null);

        // Create the DetalleOrden, which fails.

        restDetalleOrdenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isBadRequest());

        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDetalleOrdens() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        // Get all the detalleOrdenList
        restDetalleOrdenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detalleOrden.getId())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD.doubleValue())))
            .andExpect(jsonPath("$.[*].valUni").value(hasItem(DEFAULT_VAL_UNI.doubleValue())))
            .andExpect(jsonPath("$.[*].dcto").value(hasItem(DEFAULT_DCTO.doubleValue())))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].observacion").value(hasItem(DEFAULT_OBSERVACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
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
    void getDetalleOrden() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        // Get the detalleOrden
        restDetalleOrdenMockMvc
            .perform(get(ENTITY_API_URL_ID, detalleOrden.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detalleOrden.getId()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD.doubleValue()))
            .andExpect(jsonPath("$.valUni").value(DEFAULT_VAL_UNI.doubleValue()))
            .andExpect(jsonPath("$.dcto").value(DEFAULT_DCTO.doubleValue()))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.observacion").value(DEFAULT_OBSERVACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
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
    void getNonExistingDetalleOrden() throws Exception {
        // Get the detalleOrden
        restDetalleOrdenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewDetalleOrden() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();

        // Update the detalleOrden
        DetalleOrden updatedDetalleOrden = detalleOrdenRepository.findById(detalleOrden.getId()).get();
        updatedDetalleOrden
            .cantidad(UPDATED_CANTIDAD)
            .valUni(UPDATED_VAL_UNI)
            .dcto(UPDATED_DCTO)
            .subtotal(UPDATED_SUBTOTAL)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetalleOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDetalleOrden.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDetalleOrden))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
        DetalleOrden testDetalleOrden = detalleOrdenList.get(detalleOrdenList.size() - 1);
        assertThat(testDetalleOrden.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testDetalleOrden.getValUni()).isEqualTo(UPDATED_VAL_UNI);
        assertThat(testDetalleOrden.getDcto()).isEqualTo(UPDATED_DCTO);
        assertThat(testDetalleOrden.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testDetalleOrden.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testDetalleOrden.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetalleOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testDetalleOrden.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testDetalleOrden.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetalleOrden.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetalleOrden.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetalleOrden.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetalleOrden.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetalleOrden.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detalleOrden.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOrden))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOrden))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOrden)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDetalleOrdenWithPatch() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();

        // Update the detalleOrden using partial update
        DetalleOrden partialUpdatedDetalleOrden = new DetalleOrden();
        partialUpdatedDetalleOrden.setId(detalleOrden.getId());

        partialUpdatedDetalleOrden
            .cantidad(UPDATED_CANTIDAD)
            .valUni(UPDATED_VAL_UNI)
            .subtotal(UPDATED_SUBTOTAL)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .ipModif(UPDATED_IP_MODIF);

        restDetalleOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetalleOrden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetalleOrden))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
        DetalleOrden testDetalleOrden = detalleOrdenList.get(detalleOrdenList.size() - 1);
        assertThat(testDetalleOrden.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testDetalleOrden.getValUni()).isEqualTo(UPDATED_VAL_UNI);
        assertThat(testDetalleOrden.getDcto()).isEqualTo(DEFAULT_DCTO);
        assertThat(testDetalleOrden.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testDetalleOrden.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testDetalleOrden.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetalleOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testDetalleOrden.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testDetalleOrden.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testDetalleOrden.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testDetalleOrden.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testDetalleOrden.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetalleOrden.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetalleOrden.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateDetalleOrdenWithPatch() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();

        // Update the detalleOrden using partial update
        DetalleOrden partialUpdatedDetalleOrden = new DetalleOrden();
        partialUpdatedDetalleOrden.setId(detalleOrden.getId());

        partialUpdatedDetalleOrden
            .cantidad(UPDATED_CANTIDAD)
            .valUni(UPDATED_VAL_UNI)
            .dcto(UPDATED_DCTO)
            .subtotal(UPDATED_SUBTOTAL)
            .observacion(UPDATED_OBSERVACION)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetalleOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetalleOrden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetalleOrden))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
        DetalleOrden testDetalleOrden = detalleOrdenList.get(detalleOrdenList.size() - 1);
        assertThat(testDetalleOrden.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testDetalleOrden.getValUni()).isEqualTo(UPDATED_VAL_UNI);
        assertThat(testDetalleOrden.getDcto()).isEqualTo(UPDATED_DCTO);
        assertThat(testDetalleOrden.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testDetalleOrden.getObservacion()).isEqualTo(UPDATED_OBSERVACION);
        assertThat(testDetalleOrden.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testDetalleOrden.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testDetalleOrden.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testDetalleOrden.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetalleOrden.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetalleOrden.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetalleOrden.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetalleOrden.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetalleOrden.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detalleOrden.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detalleOrden))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detalleOrden))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDetalleOrden() throws Exception {
        int databaseSizeBeforeUpdate = detalleOrdenRepository.findAll().size();
        detalleOrden.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOrdenMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(detalleOrden))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetalleOrden in the database
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDetalleOrden() throws Exception {
        // Initialize the database
        detalleOrdenRepository.save(detalleOrden);

        int databaseSizeBeforeDelete = detalleOrdenRepository.findAll().size();

        // Delete the detalleOrden
        restDetalleOrdenMockMvc
            .perform(delete(ENTITY_API_URL_ID, detalleOrden.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetalleOrden> detalleOrdenList = detalleOrdenRepository.findAll();
        assertThat(detalleOrdenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
