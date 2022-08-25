package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.Empleado;
import com.ppanticona.fabio.repository.EmpleadoRepository;
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
 * Integration tests for the {@link EmpleadoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpleadoResourceIT {

    private static final String DEFAULT_TIP_DOC_EMP = "AAAAAAAAAA";
    private static final String UPDATED_TIP_DOC_EMP = "BBBBBBBBBB";

    private static final String DEFAULT_NRO_DOC_EMP = "AAAAAAAAAA";
    private static final String UPDATED_NRO_DOC_EMP = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRES_EMP = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRES_EMP = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS_EMP = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS_EMP = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_REF_DIRECC = "AAAAAAAAAA";
    private static final String UPDATED_REF_DIRECC = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_DISTRITO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_INGRESO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_INGRESO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FEC_NAC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_NAC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/empleados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private MockMvc restEmpleadoMockMvc;

    private Empleado empleado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleado createEntity() {
        Empleado empleado = new Empleado()
            .tipDocEmp(DEFAULT_TIP_DOC_EMP)
            .nroDocEmp(DEFAULT_NRO_DOC_EMP)
            .nombresEmp(DEFAULT_NOMBRES_EMP)
            .apellidosEmp(DEFAULT_APELLIDOS_EMP)
            .categoria(DEFAULT_CATEGORIA)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .correo(DEFAULT_CORREO)
            .direccion(DEFAULT_DIRECCION)
            .refDirecc(DEFAULT_REF_DIRECC)
            .distrito(DEFAULT_DISTRITO)
            .fecIngreso(DEFAULT_FEC_INGRESO)
            .fecNac(DEFAULT_FEC_NAC)
            .userId(DEFAULT_USER_ID)
            .estado(DEFAULT_ESTADO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return empleado;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleado createUpdatedEntity() {
        Empleado empleado = new Empleado()
            .tipDocEmp(UPDATED_TIP_DOC_EMP)
            .nroDocEmp(UPDATED_NRO_DOC_EMP)
            .nombresEmp(UPDATED_NOMBRES_EMP)
            .apellidosEmp(UPDATED_APELLIDOS_EMP)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return empleado;
    }

    @BeforeEach
    public void initTest() {
        empleadoRepository.deleteAll();
        empleado = createEntity();
    }

    @Test
    void createEmpleado() throws Exception {
        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();
        // Create the Empleado
        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isCreated());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate + 1);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getTipDocEmp()).isEqualTo(DEFAULT_TIP_DOC_EMP);
        assertThat(testEmpleado.getNroDocEmp()).isEqualTo(DEFAULT_NRO_DOC_EMP);
        assertThat(testEmpleado.getNombresEmp()).isEqualTo(DEFAULT_NOMBRES_EMP);
        assertThat(testEmpleado.getApellidosEmp()).isEqualTo(DEFAULT_APELLIDOS_EMP);
        assertThat(testEmpleado.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testEmpleado.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testEmpleado.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testEmpleado.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testEmpleado.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEmpleado.getRefDirecc()).isEqualTo(DEFAULT_REF_DIRECC);
        assertThat(testEmpleado.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testEmpleado.getFecIngreso()).isEqualTo(DEFAULT_FEC_INGRESO);
        assertThat(testEmpleado.getFecNac()).isEqualTo(DEFAULT_FEC_NAC);
        assertThat(testEmpleado.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEmpleado.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testEmpleado.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testEmpleado.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testEmpleado.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testEmpleado.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testEmpleado.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testEmpleado.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testEmpleado.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testEmpleado.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createEmpleadoWithExistingId() throws Exception {
        // Create the Empleado with an existing ID
        empleado.setId("existing_id");

        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTipDocEmpIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setTipDocEmp(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNroDocEmpIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setNroDocEmp(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNombresEmpIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setNombresEmp(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkApellidosEmpIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setApellidosEmp(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecNacIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setFecNac(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setEstado(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setVersion(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setIndDel(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setFecCrea(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setUsuCrea(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setIpCrea(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllEmpleados() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        // Get all the empleadoList
        restEmpleadoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleado.getId())))
            .andExpect(jsonPath("$.[*].tipDocEmp").value(hasItem(DEFAULT_TIP_DOC_EMP)))
            .andExpect(jsonPath("$.[*].nroDocEmp").value(hasItem(DEFAULT_NRO_DOC_EMP)))
            .andExpect(jsonPath("$.[*].nombresEmp").value(hasItem(DEFAULT_NOMBRES_EMP)))
            .andExpect(jsonPath("$.[*].apellidosEmp").value(hasItem(DEFAULT_APELLIDOS_EMP)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1)))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2)))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].refDirecc").value(hasItem(DEFAULT_REF_DIRECC)))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO)))
            .andExpect(jsonPath("$.[*].fecIngreso").value(hasItem(sameInstant(DEFAULT_FEC_INGRESO))))
            .andExpect(jsonPath("$.[*].fecNac").value(hasItem(sameInstant(DEFAULT_FEC_NAC))))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
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
    void getEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        // Get the empleado
        restEmpleadoMockMvc
            .perform(get(ENTITY_API_URL_ID, empleado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empleado.getId()))
            .andExpect(jsonPath("$.tipDocEmp").value(DEFAULT_TIP_DOC_EMP))
            .andExpect(jsonPath("$.nroDocEmp").value(DEFAULT_NRO_DOC_EMP))
            .andExpect(jsonPath("$.nombresEmp").value(DEFAULT_NOMBRES_EMP))
            .andExpect(jsonPath("$.apellidosEmp").value(DEFAULT_APELLIDOS_EMP))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.refDirecc").value(DEFAULT_REF_DIRECC))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO))
            .andExpect(jsonPath("$.fecIngreso").value(sameInstant(DEFAULT_FEC_INGRESO)))
            .andExpect(jsonPath("$.fecNac").value(sameInstant(DEFAULT_FEC_NAC)))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
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
    void getNonExistingEmpleado() throws Exception {
        // Get the empleado
        restEmpleadoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Update the empleado
        Empleado updatedEmpleado = empleadoRepository.findById(empleado.getId()).get();
        updatedEmpleado
            .tipDocEmp(UPDATED_TIP_DOC_EMP)
            .nroDocEmp(UPDATED_NRO_DOC_EMP)
            .nombresEmp(UPDATED_NOMBRES_EMP)
            .apellidosEmp(UPDATED_APELLIDOS_EMP)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmpleado.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEmpleado))
            )
            .andExpect(status().isOk());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getTipDocEmp()).isEqualTo(UPDATED_TIP_DOC_EMP);
        assertThat(testEmpleado.getNroDocEmp()).isEqualTo(UPDATED_NRO_DOC_EMP);
        assertThat(testEmpleado.getNombresEmp()).isEqualTo(UPDATED_NOMBRES_EMP);
        assertThat(testEmpleado.getApellidosEmp()).isEqualTo(UPDATED_APELLIDOS_EMP);
        assertThat(testEmpleado.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testEmpleado.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testEmpleado.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testEmpleado.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testEmpleado.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpleado.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleado.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEmpleado.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleado.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleado.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEmpleado.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleado.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testEmpleado.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleado.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testEmpleado.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testEmpleado.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testEmpleado.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleado.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testEmpleado.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empleado.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleado))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(empleado))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEmpleadoWithPatch() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Update the empleado using partial update
        Empleado partialUpdatedEmpleado = new Empleado();
        partialUpdatedEmpleado.setId(empleado.getId());

        partialUpdatedEmpleado
            .tipDocEmp(UPDATED_TIP_DOC_EMP)
            .nroDocEmp(UPDATED_NRO_DOC_EMP)
            .apellidosEmp(UPDATED_APELLIDOS_EMP)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .usuCrea(UPDATED_USU_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleado))
            )
            .andExpect(status().isOk());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getTipDocEmp()).isEqualTo(UPDATED_TIP_DOC_EMP);
        assertThat(testEmpleado.getNroDocEmp()).isEqualTo(UPDATED_NRO_DOC_EMP);
        assertThat(testEmpleado.getNombresEmp()).isEqualTo(DEFAULT_NOMBRES_EMP);
        assertThat(testEmpleado.getApellidosEmp()).isEqualTo(UPDATED_APELLIDOS_EMP);
        assertThat(testEmpleado.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testEmpleado.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testEmpleado.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testEmpleado.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testEmpleado.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEmpleado.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleado.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEmpleado.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleado.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleado.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEmpleado.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleado.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testEmpleado.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleado.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testEmpleado.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testEmpleado.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testEmpleado.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleado.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testEmpleado.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateEmpleadoWithPatch() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Update the empleado using partial update
        Empleado partialUpdatedEmpleado = new Empleado();
        partialUpdatedEmpleado.setId(empleado.getId());

        partialUpdatedEmpleado
            .tipDocEmp(UPDATED_TIP_DOC_EMP)
            .nroDocEmp(UPDATED_NRO_DOC_EMP)
            .nombresEmp(UPDATED_NOMBRES_EMP)
            .apellidosEmp(UPDATED_APELLIDOS_EMP)
            .categoria(UPDATED_CATEGORIA)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .correo(UPDATED_CORREO)
            .direccion(UPDATED_DIRECCION)
            .refDirecc(UPDATED_REF_DIRECC)
            .distrito(UPDATED_DISTRITO)
            .fecIngreso(UPDATED_FEC_INGRESO)
            .fecNac(UPDATED_FEC_NAC)
            .userId(UPDATED_USER_ID)
            .estado(UPDATED_ESTADO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmpleado))
            )
            .andExpect(status().isOk());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getTipDocEmp()).isEqualTo(UPDATED_TIP_DOC_EMP);
        assertThat(testEmpleado.getNroDocEmp()).isEqualTo(UPDATED_NRO_DOC_EMP);
        assertThat(testEmpleado.getNombresEmp()).isEqualTo(UPDATED_NOMBRES_EMP);
        assertThat(testEmpleado.getApellidosEmp()).isEqualTo(UPDATED_APELLIDOS_EMP);
        assertThat(testEmpleado.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testEmpleado.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testEmpleado.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testEmpleado.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testEmpleado.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpleado.getRefDirecc()).isEqualTo(UPDATED_REF_DIRECC);
        assertThat(testEmpleado.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEmpleado.getFecIngreso()).isEqualTo(UPDATED_FEC_INGRESO);
        assertThat(testEmpleado.getFecNac()).isEqualTo(UPDATED_FEC_NAC);
        assertThat(testEmpleado.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEmpleado.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmpleado.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testEmpleado.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testEmpleado.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testEmpleado.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testEmpleado.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testEmpleado.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testEmpleado.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testEmpleado.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empleado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleado))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(empleado))
            )
            .andExpect(status().isBadRequest());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();
        empleado.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.save(empleado);

        int databaseSizeBeforeDelete = empleadoRepository.findAll().size();

        // Delete the empleado
        restEmpleadoMockMvc
            .perform(delete(ENTITY_API_URL_ID, empleado.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
