package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.Proveedor;
import com.ppanticona.fabio.repository.ProveedorRepository;
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
 * Integration tests for the {@link ProveedorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProveedorResourceIT {

    private static final String DEFAULT_TIP_DOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC_PROV = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC_PROV = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRES_RAZON_SOC_PROV = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRES_RAZON_SOC_PROV = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_REF_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_REF_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_DISTRITO = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/proveedors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private MockMvc restProveedorMockMvc;

    private Proveedor proveedor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proveedor createEntity() {
        Proveedor proveedor = new Proveedor()
            .tipDocProv(DEFAULT_TIP_DOC_PROV)
            .nroDocProv(DEFAULT_NRO_DOC_PROV)
            .nombresRazonSocProv(DEFAULT_NOMBRES_RAZON_SOC_PROV)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .correo(DEFAULT_CORREO)
            .direccion(DEFAULT_DIRECCION)
            .refDireccion(DEFAULT_REF_DIRECCION)
            .distrito(DEFAULT_DISTRITO)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return proveedor;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proveedor createUpdatedEntity() {
        Proveedor proveedor = new Proveedor()
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nombresRazonSocProv(UPDATED_NOMBRES_RAZON_SOC_PROV)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDireccion(UPDATED_REF_DIRECCION)
            .distrito(UPDATED_DISTRITO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return proveedor;
    }

    @BeforeEach
    public void initTest() {
        proveedorRepository.deleteAll();
        proveedor = createEntity();
    }

    @Test
    void createProveedor() throws Exception {
        int databaseSizeBeforeCreate = proveedorRepository.findAll().size();
        // Create the Proveedor
        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isCreated());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeCreate + 1);
        Proveedor testProveedor = proveedorList.get(proveedorList.size() - 1);
        assertThat(testProveedor.getTipDocProv()).isEqualTo(DEFAULT_TIP_DOC_PROV);
        assertThat(testProveedor.getNroDocProv()).isEqualTo(DEFAULT_NRO_DOC_PROV);
        assertThat(testProveedor.getNombresRazonSocProv()).isEqualTo(DEFAULT_NOMBRES_RAZON_SOC_PROV);
        assertThat(testProveedor.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testProveedor.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testProveedor.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testProveedor.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testProveedor.getRefDireccion()).isEqualTo(DEFAULT_REF_DIRECCION);
        assertThat(testProveedor.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testProveedor.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testProveedor.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProveedor.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testProveedor.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testProveedor.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testProveedor.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testProveedor.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testProveedor.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testProveedor.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createProveedorWithExistingId() throws Exception {
        // Create the Proveedor with an existing ID
        proveedor.setId("existing_id");

        int databaseSizeBeforeCreate = proveedorRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipDocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setTipDocProv(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setNroDocProv(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNombresRazonSocProvIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setNombresRazonSocProv(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setEstado(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setVersion(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setIndDel(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setFecCrea(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setUsuCrea(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = proveedorRepository.findAll().size();
        // set the field null
        proveedor.setIpCrea(null);

        // Create the Proveedor, which fails.

        restProveedorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isBadRequest());

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllProveedors() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        // Get all the proveedorList
        restProveedorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proveedor.getId())))
            .andExpect(jsonPath("$.[*].tipDocProv").value(hasItem(DEFAULT_TIP_DOC_PROV)))
            .andExpect(jsonPath("$.[*].nroDocProv").value(hasItem(DEFAULT_NRO_DOC_PROV)))
            .andExpect(jsonPath("$.[*].nombresRazonSocProv").value(hasItem(DEFAULT_NOMBRES_RAZON_SOC_PROV)))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].refDireccion").value(hasItem(DEFAULT_REF_DIRECCION)))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO)))
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
    void getProveedor() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        // Get the proveedor
        restProveedorMockMvc
            .perform(get(ENTITY_API_URL_ID, proveedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proveedor.getId()))
            .andExpect(jsonPath("$.tipDocProv").value(DEFAULT_TIP_DOC_PROV))
            .andExpect(jsonPath("$.nroDocProv").value(DEFAULT_NRO_DOC_PROV))
            .andExpect(jsonPath("$.nombresRazonSocProv").value(DEFAULT_NOMBRES_RAZON_SOC_PROV))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.refDireccion").value(DEFAULT_REF_DIRECCION))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO))
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
    void getNonExistingProveedor() throws Exception {
        // Get the proveedor
        restProveedorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewProveedor() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();

        // Update the proveedor
        Proveedor updatedProveedor = proveedorRepository.findById(proveedor.getId()).get();
        updatedProveedor
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nombresRazonSocProv(UPDATED_NOMBRES_RAZON_SOC_PROV)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDireccion(UPDATED_REF_DIRECCION)
            .distrito(UPDATED_DISTRITO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProveedorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProveedor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProveedor))
            )
            .andExpect(status().isOk());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
        Proveedor testProveedor = proveedorList.get(proveedorList.size() - 1);
        assertThat(testProveedor.getTipDocProv()).isEqualTo(UPDATED_TIP_DOC_PROV);
        assertThat(testProveedor.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testProveedor.getNombresRazonSocProv()).isEqualTo(UPDATED_NOMBRES_RAZON_SOC_PROV);
        assertThat(testProveedor.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testProveedor.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testProveedor.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testProveedor.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testProveedor.getRefDireccion()).isEqualTo(UPDATED_REF_DIRECCION);
        assertThat(testProveedor.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedor.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProveedor.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProveedor.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProveedor.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProveedor.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedor.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProveedor.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProveedor.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProveedor.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, proveedor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proveedor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(proveedor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(proveedor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateProveedorWithPatch() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();

        // Update the proveedor using partial update
        Proveedor partialUpdatedProveedor = new Proveedor();
        partialUpdatedProveedor.setId(proveedor.getId());

        partialUpdatedProveedor
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .distrito(UPDATED_DISTRITO)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA);

        restProveedorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProveedor.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProveedor))
            )
            .andExpect(status().isOk());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
        Proveedor testProveedor = proveedorList.get(proveedorList.size() - 1);
        assertThat(testProveedor.getTipDocProv()).isEqualTo(UPDATED_TIP_DOC_PROV);
        assertThat(testProveedor.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testProveedor.getNombresRazonSocProv()).isEqualTo(DEFAULT_NOMBRES_RAZON_SOC_PROV);
        assertThat(testProveedor.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testProveedor.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testProveedor.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testProveedor.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testProveedor.getRefDireccion()).isEqualTo(DEFAULT_REF_DIRECCION);
        assertThat(testProveedor.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedor.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testProveedor.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProveedor.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testProveedor.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProveedor.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedor.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testProveedor.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testProveedor.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testProveedor.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateProveedorWithPatch() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();

        // Update the proveedor using partial update
        Proveedor partialUpdatedProveedor = new Proveedor();
        partialUpdatedProveedor.setId(proveedor.getId());

        partialUpdatedProveedor
            .tipDocProv(UPDATED_TIP_DOC_PROV)
            .nroDocProv(UPDATED_NRO_DOC_PROV)
            .nombresRazonSocProv(UPDATED_NOMBRES_RAZON_SOC_PROV)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDireccion(UPDATED_REF_DIRECCION)
            .distrito(UPDATED_DISTRITO)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restProveedorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProveedor.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProveedor))
            )
            .andExpect(status().isOk());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
        Proveedor testProveedor = proveedorList.get(proveedorList.size() - 1);
        assertThat(testProveedor.getTipDocProv()).isEqualTo(UPDATED_TIP_DOC_PROV);
        assertThat(testProveedor.getNroDocProv()).isEqualTo(UPDATED_NRO_DOC_PROV);
        assertThat(testProveedor.getNombresRazonSocProv()).isEqualTo(UPDATED_NOMBRES_RAZON_SOC_PROV);
        assertThat(testProveedor.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testProveedor.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testProveedor.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testProveedor.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testProveedor.getRefDireccion()).isEqualTo(UPDATED_REF_DIRECCION);
        assertThat(testProveedor.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testProveedor.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testProveedor.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProveedor.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testProveedor.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testProveedor.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testProveedor.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testProveedor.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testProveedor.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testProveedor.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, proveedor.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proveedor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(proveedor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamProveedor() throws Exception {
        int databaseSizeBeforeUpdate = proveedorRepository.findAll().size();
        proveedor.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProveedorMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(proveedor))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Proveedor in the database
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteProveedor() throws Exception {
        // Initialize the database
        proveedorRepository.save(proveedor);

        int databaseSizeBeforeDelete = proveedorRepository.findAll().size();

        // Delete the proveedor
        restProveedorMockMvc
            .perform(delete(ENTITY_API_URL_ID, proveedor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        assertThat(proveedorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
