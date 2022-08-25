package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.DetalleOperaContable;
import com.ppanticona.fabio.repository.DetalleOperaContableRepository;
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
 * Integration tests for the {@link DetalleOperaContableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetalleOperaContableResourceIT {

    private static final String DEFAULT_OPERADOR = "AAAAAAAAAA";
    private static final String UPDATED_OPERADOR = "BBBBBBBBBB";

    private static final Double DEFAULT_VALOR_OPERACION = 1D;
    private static final Double UPDATED_VALOR_OPERACION = 2D;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_MOVIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_MOVIMIENTO = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/detalle-opera-contables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DetalleOperaContableRepository detalleOperaContableRepository;

    @Autowired
    private MockMvc restDetalleOperaContableMockMvc;

    private DetalleOperaContable detalleOperaContable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetalleOperaContable createEntity() {
        DetalleOperaContable detalleOperaContable = new DetalleOperaContable()
            .operador(DEFAULT_OPERADOR)
            .valorOperacion(DEFAULT_VALOR_OPERACION)
            .descripcion(DEFAULT_DESCRIPCION)
            .tipMovimiento(DEFAULT_TIP_MOVIMIENTO)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return detalleOperaContable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetalleOperaContable createUpdatedEntity() {
        DetalleOperaContable detalleOperaContable = new DetalleOperaContable()
            .operador(UPDATED_OPERADOR)
            .valorOperacion(UPDATED_VALOR_OPERACION)
            .descripcion(UPDATED_DESCRIPCION)
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return detalleOperaContable;
    }

    @BeforeEach
    public void initTest() {
        detalleOperaContableRepository.deleteAll();
        detalleOperaContable = createEntity();
    }

    @Test
    void createDetalleOperaContable() throws Exception {
        int databaseSizeBeforeCreate = detalleOperaContableRepository.findAll().size();
        // Create the DetalleOperaContable
        restDetalleOperaContableMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isCreated());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeCreate + 1);
        DetalleOperaContable testDetalleOperaContable = detalleOperaContableList.get(detalleOperaContableList.size() - 1);
        assertThat(testDetalleOperaContable.getOperador()).isEqualTo(DEFAULT_OPERADOR);
        assertThat(testDetalleOperaContable.getValorOperacion()).isEqualTo(DEFAULT_VALOR_OPERACION);
        assertThat(testDetalleOperaContable.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testDetalleOperaContable.getTipMovimiento()).isEqualTo(DEFAULT_TIP_MOVIMIENTO);
        assertThat(testDetalleOperaContable.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testDetalleOperaContable.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testDetalleOperaContable.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testDetalleOperaContable.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetalleOperaContable.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetalleOperaContable.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createDetalleOperaContableWithExistingId() throws Exception {
        // Create the DetalleOperaContable with an existing ID
        detalleOperaContable.setId("existing_id");

        int databaseSizeBeforeCreate = detalleOperaContableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetalleOperaContableMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOperaContableRepository.findAll().size();
        // set the field null
        detalleOperaContable.setFecCrea(null);

        // Create the DetalleOperaContable, which fails.

        restDetalleOperaContableMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOperaContableRepository.findAll().size();
        // set the field null
        detalleOperaContable.setUsuCrea(null);

        // Create the DetalleOperaContable, which fails.

        restDetalleOperaContableMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleOperaContableRepository.findAll().size();
        // set the field null
        detalleOperaContable.setIpCrea(null);

        // Create the DetalleOperaContable, which fails.

        restDetalleOperaContableMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDetalleOperaContables() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        // Get all the detalleOperaContableList
        restDetalleOperaContableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detalleOperaContable.getId())))
            .andExpect(jsonPath("$.[*].operador").value(hasItem(DEFAULT_OPERADOR)))
            .andExpect(jsonPath("$.[*].valorOperacion").value(hasItem(DEFAULT_VALOR_OPERACION.doubleValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].tipMovimiento").value(hasItem(DEFAULT_TIP_MOVIMIENTO)))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getDetalleOperaContable() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        // Get the detalleOperaContable
        restDetalleOperaContableMockMvc
            .perform(get(ENTITY_API_URL_ID, detalleOperaContable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detalleOperaContable.getId()))
            .andExpect(jsonPath("$.operador").value(DEFAULT_OPERADOR))
            .andExpect(jsonPath("$.valorOperacion").value(DEFAULT_VALOR_OPERACION.doubleValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.tipMovimiento").value(DEFAULT_TIP_MOVIMIENTO))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingDetalleOperaContable() throws Exception {
        // Get the detalleOperaContable
        restDetalleOperaContableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewDetalleOperaContable() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();

        // Update the detalleOperaContable
        DetalleOperaContable updatedDetalleOperaContable = detalleOperaContableRepository.findById(detalleOperaContable.getId()).get();
        updatedDetalleOperaContable
            .operador(UPDATED_OPERADOR)
            .valorOperacion(UPDATED_VALOR_OPERACION)
            .descripcion(UPDATED_DESCRIPCION)
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetalleOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDetalleOperaContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDetalleOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
        DetalleOperaContable testDetalleOperaContable = detalleOperaContableList.get(detalleOperaContableList.size() - 1);
        assertThat(testDetalleOperaContable.getOperador()).isEqualTo(UPDATED_OPERADOR);
        assertThat(testDetalleOperaContable.getValorOperacion()).isEqualTo(UPDATED_VALOR_OPERACION);
        assertThat(testDetalleOperaContable.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testDetalleOperaContable.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testDetalleOperaContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetalleOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetalleOperaContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetalleOperaContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetalleOperaContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetalleOperaContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detalleOperaContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDetalleOperaContableWithPatch() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();

        // Update the detalleOperaContable using partial update
        DetalleOperaContable partialUpdatedDetalleOperaContable = new DetalleOperaContable();
        partialUpdatedDetalleOperaContable.setId(detalleOperaContable.getId());

        partialUpdatedDetalleOperaContable.fecCrea(UPDATED_FEC_CREA).usuCrea(UPDATED_USU_CREA).ipCrea(UPDATED_IP_CREA);

        restDetalleOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetalleOperaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetalleOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
        DetalleOperaContable testDetalleOperaContable = detalleOperaContableList.get(detalleOperaContableList.size() - 1);
        assertThat(testDetalleOperaContable.getOperador()).isEqualTo(DEFAULT_OPERADOR);
        assertThat(testDetalleOperaContable.getValorOperacion()).isEqualTo(DEFAULT_VALOR_OPERACION);
        assertThat(testDetalleOperaContable.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testDetalleOperaContable.getTipMovimiento()).isEqualTo(DEFAULT_TIP_MOVIMIENTO);
        assertThat(testDetalleOperaContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetalleOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetalleOperaContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetalleOperaContable.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testDetalleOperaContable.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testDetalleOperaContable.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateDetalleOperaContableWithPatch() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();

        // Update the detalleOperaContable using partial update
        DetalleOperaContable partialUpdatedDetalleOperaContable = new DetalleOperaContable();
        partialUpdatedDetalleOperaContable.setId(detalleOperaContable.getId());

        partialUpdatedDetalleOperaContable
            .operador(UPDATED_OPERADOR)
            .valorOperacion(UPDATED_VALOR_OPERACION)
            .descripcion(UPDATED_DESCRIPCION)
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restDetalleOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetalleOperaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetalleOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
        DetalleOperaContable testDetalleOperaContable = detalleOperaContableList.get(detalleOperaContableList.size() - 1);
        assertThat(testDetalleOperaContable.getOperador()).isEqualTo(UPDATED_OPERADOR);
        assertThat(testDetalleOperaContable.getValorOperacion()).isEqualTo(UPDATED_VALOR_OPERACION);
        assertThat(testDetalleOperaContable.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testDetalleOperaContable.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testDetalleOperaContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testDetalleOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testDetalleOperaContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testDetalleOperaContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testDetalleOperaContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testDetalleOperaContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detalleOperaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDetalleOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = detalleOperaContableRepository.findAll().size();
        detalleOperaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetalleOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detalleOperaContable))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetalleOperaContable in the database
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDetalleOperaContable() throws Exception {
        // Initialize the database
        detalleOperaContableRepository.save(detalleOperaContable);

        int databaseSizeBeforeDelete = detalleOperaContableRepository.findAll().size();

        // Delete the detalleOperaContable
        restDetalleOperaContableMockMvc
            .perform(delete(ENTITY_API_URL_ID, detalleOperaContable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetalleOperaContable> detalleOperaContableList = detalleOperaContableRepository.findAll();
        assertThat(detalleOperaContableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
