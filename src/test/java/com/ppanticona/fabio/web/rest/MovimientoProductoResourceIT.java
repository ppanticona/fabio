package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.MovimientoProducto;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
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
 * Integration tests for the {@link MovimientoProductoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MovimientoProductoResourceIT {

    private static final String DEFAULT_TIP_MOVIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_MOVIMIENTO = "BBBBBBBBBB";

    private static final String DEFAULT_TIP_2_MOVIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIP_2_MOVIMIENTO = "BBBBBBBBBB";

    private static final Double DEFAULT_PRE_VENTA = 1D;
    private static final Double UPDATED_PRE_VENTA = 2D;

    private static final Double DEFAULT_PREC_COMPRA = 1D;
    private static final Double UPDATED_PREC_COMPRA = 2D;

    private static final Double DEFAULT_CNT = 1D;
    private static final Double UPDATED_CNT = 2D;

    private static final String DEFAULT_LOTE = "AAAAAAAAAA";
    private static final String UPDATED_LOTE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FEC_MOVIMIENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FEC_MOVIMIENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/movimiento-productos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MovimientoProductoRepository movimientoProductoRepository;

    @Autowired
    private MockMvc restMovimientoProductoMockMvc;

    private MovimientoProducto movimientoProducto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimientoProducto createEntity() {
        MovimientoProducto movimientoProducto = new MovimientoProducto()
            .tipMovimiento(DEFAULT_TIP_MOVIMIENTO)
            .tip2Movimiento(DEFAULT_TIP_2_MOVIMIENTO)
            .preVenta(DEFAULT_PRE_VENTA)
            .precCompra(DEFAULT_PREC_COMPRA)
            .cnt(DEFAULT_CNT)
            .lote(DEFAULT_LOTE)
            .fecMovimiento(DEFAULT_FEC_MOVIMIENTO)
            .version(DEFAULT_VERSION)
            .indDel(DEFAULT_IND_DEL)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return movimientoProducto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovimientoProducto createUpdatedEntity() {
        MovimientoProducto movimientoProducto = new MovimientoProducto()
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .tip2Movimiento(UPDATED_TIP_2_MOVIMIENTO)
            .preVenta(UPDATED_PRE_VENTA)
            .precCompra(UPDATED_PREC_COMPRA)
            .cnt(UPDATED_CNT)
            .lote(UPDATED_LOTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return movimientoProducto;
    }

    @BeforeEach
    public void initTest() {
        movimientoProductoRepository.deleteAll();
        movimientoProducto = createEntity();
    }

    @Test
    void createMovimientoProducto() throws Exception {
        int databaseSizeBeforeCreate = movimientoProductoRepository.findAll().size();
        // Create the MovimientoProducto
        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isCreated());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeCreate + 1);
        MovimientoProducto testMovimientoProducto = movimientoProductoList.get(movimientoProductoList.size() - 1);
        assertThat(testMovimientoProducto.getTipMovimiento()).isEqualTo(DEFAULT_TIP_MOVIMIENTO);
        assertThat(testMovimientoProducto.getTip2Movimiento()).isEqualTo(DEFAULT_TIP_2_MOVIMIENTO);
        assertThat(testMovimientoProducto.getPreVenta()).isEqualTo(DEFAULT_PRE_VENTA);
        assertThat(testMovimientoProducto.getPrecCompra()).isEqualTo(DEFAULT_PREC_COMPRA);
        assertThat(testMovimientoProducto.getCnt()).isEqualTo(DEFAULT_CNT);
        assertThat(testMovimientoProducto.getLote()).isEqualTo(DEFAULT_LOTE);
        assertThat(testMovimientoProducto.getFecMovimiento()).isEqualTo(DEFAULT_FEC_MOVIMIENTO);
        assertThat(testMovimientoProducto.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMovimientoProducto.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testMovimientoProducto.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testMovimientoProducto.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testMovimientoProducto.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testMovimientoProducto.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testMovimientoProducto.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testMovimientoProducto.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createMovimientoProductoWithExistingId() throws Exception {
        // Create the MovimientoProducto with an existing ID
        movimientoProducto.setId("existing_id");

        int databaseSizeBeforeCreate = movimientoProductoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoProductoRepository.findAll().size();
        // set the field null
        movimientoProducto.setVersion(null);

        // Create the MovimientoProducto, which fails.

        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIndDelIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoProductoRepository.findAll().size();
        // set the field null
        movimientoProducto.setIndDel(null);

        // Create the MovimientoProducto, which fails.

        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoProductoRepository.findAll().size();
        // set the field null
        movimientoProducto.setFecCrea(null);

        // Create the MovimientoProducto, which fails.

        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoProductoRepository.findAll().size();
        // set the field null
        movimientoProducto.setUsuCrea(null);

        // Create the MovimientoProducto, which fails.

        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = movimientoProductoRepository.findAll().size();
        // set the field null
        movimientoProducto.setIpCrea(null);

        // Create the MovimientoProducto, which fails.

        restMovimientoProductoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMovimientoProductos() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        // Get all the movimientoProductoList
        restMovimientoProductoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movimientoProducto.getId())))
            .andExpect(jsonPath("$.[*].tipMovimiento").value(hasItem(DEFAULT_TIP_MOVIMIENTO)))
            .andExpect(jsonPath("$.[*].tip2Movimiento").value(hasItem(DEFAULT_TIP_2_MOVIMIENTO)))
            .andExpect(jsonPath("$.[*].preVenta").value(hasItem(DEFAULT_PRE_VENTA.doubleValue())))
            .andExpect(jsonPath("$.[*].precCompra").value(hasItem(DEFAULT_PREC_COMPRA.doubleValue())))
            .andExpect(jsonPath("$.[*].cnt").value(hasItem(DEFAULT_CNT.doubleValue())))
            .andExpect(jsonPath("$.[*].lote").value(hasItem(DEFAULT_LOTE)))
            .andExpect(jsonPath("$.[*].fecMovimiento").value(hasItem(sameInstant(DEFAULT_FEC_MOVIMIENTO))))
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
    void getMovimientoProducto() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        // Get the movimientoProducto
        restMovimientoProductoMockMvc
            .perform(get(ENTITY_API_URL_ID, movimientoProducto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(movimientoProducto.getId()))
            .andExpect(jsonPath("$.tipMovimiento").value(DEFAULT_TIP_MOVIMIENTO))
            .andExpect(jsonPath("$.tip2Movimiento").value(DEFAULT_TIP_2_MOVIMIENTO))
            .andExpect(jsonPath("$.preVenta").value(DEFAULT_PRE_VENTA.doubleValue()))
            .andExpect(jsonPath("$.precCompra").value(DEFAULT_PREC_COMPRA.doubleValue()))
            .andExpect(jsonPath("$.cnt").value(DEFAULT_CNT.doubleValue()))
            .andExpect(jsonPath("$.lote").value(DEFAULT_LOTE))
            .andExpect(jsonPath("$.fecMovimiento").value(sameInstant(DEFAULT_FEC_MOVIMIENTO)))
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
    void getNonExistingMovimientoProducto() throws Exception {
        // Get the movimientoProducto
        restMovimientoProductoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewMovimientoProducto() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();

        // Update the movimientoProducto
        MovimientoProducto updatedMovimientoProducto = movimientoProductoRepository.findById(movimientoProducto.getId()).get();
        updatedMovimientoProducto
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .tip2Movimiento(UPDATED_TIP_2_MOVIMIENTO)
            .preVenta(UPDATED_PRE_VENTA)
            .precCompra(UPDATED_PREC_COMPRA)
            .cnt(UPDATED_CNT)
            .lote(UPDATED_LOTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMovimientoProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMovimientoProducto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMovimientoProducto))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
        MovimientoProducto testMovimientoProducto = movimientoProductoList.get(movimientoProductoList.size() - 1);
        assertThat(testMovimientoProducto.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testMovimientoProducto.getTip2Movimiento()).isEqualTo(UPDATED_TIP_2_MOVIMIENTO);
        assertThat(testMovimientoProducto.getPreVenta()).isEqualTo(UPDATED_PRE_VENTA);
        assertThat(testMovimientoProducto.getPrecCompra()).isEqualTo(UPDATED_PREC_COMPRA);
        assertThat(testMovimientoProducto.getCnt()).isEqualTo(UPDATED_CNT);
        assertThat(testMovimientoProducto.getLote()).isEqualTo(UPDATED_LOTE);
        assertThat(testMovimientoProducto.getFecMovimiento()).isEqualTo(UPDATED_FEC_MOVIMIENTO);
        assertThat(testMovimientoProducto.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMovimientoProducto.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMovimientoProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMovimientoProducto.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMovimientoProducto.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMovimientoProducto.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, movimientoProducto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMovimientoProductoWithPatch() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();

        // Update the movimientoProducto using partial update
        MovimientoProducto partialUpdatedMovimientoProducto = new MovimientoProducto();
        partialUpdatedMovimientoProducto.setId(movimientoProducto.getId());

        partialUpdatedMovimientoProducto
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .tip2Movimiento(UPDATED_TIP_2_MOVIMIENTO)
            .lote(UPDATED_LOTE)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF);

        restMovimientoProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMovimientoProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMovimientoProducto))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
        MovimientoProducto testMovimientoProducto = movimientoProductoList.get(movimientoProductoList.size() - 1);
        assertThat(testMovimientoProducto.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testMovimientoProducto.getTip2Movimiento()).isEqualTo(UPDATED_TIP_2_MOVIMIENTO);
        assertThat(testMovimientoProducto.getPreVenta()).isEqualTo(DEFAULT_PRE_VENTA);
        assertThat(testMovimientoProducto.getPrecCompra()).isEqualTo(DEFAULT_PREC_COMPRA);
        assertThat(testMovimientoProducto.getCnt()).isEqualTo(DEFAULT_CNT);
        assertThat(testMovimientoProducto.getLote()).isEqualTo(UPDATED_LOTE);
        assertThat(testMovimientoProducto.getFecMovimiento()).isEqualTo(DEFAULT_FEC_MOVIMIENTO);
        assertThat(testMovimientoProducto.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMovimientoProducto.getIndDel()).isEqualTo(DEFAULT_IND_DEL);
        assertThat(testMovimientoProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMovimientoProducto.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testMovimientoProducto.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMovimientoProducto.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void fullUpdateMovimientoProductoWithPatch() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();

        // Update the movimientoProducto using partial update
        MovimientoProducto partialUpdatedMovimientoProducto = new MovimientoProducto();
        partialUpdatedMovimientoProducto.setId(movimientoProducto.getId());

        partialUpdatedMovimientoProducto
            .tipMovimiento(UPDATED_TIP_MOVIMIENTO)
            .tip2Movimiento(UPDATED_TIP_2_MOVIMIENTO)
            .preVenta(UPDATED_PRE_VENTA)
            .precCompra(UPDATED_PREC_COMPRA)
            .cnt(UPDATED_CNT)
            .lote(UPDATED_LOTE)
            .fecMovimiento(UPDATED_FEC_MOVIMIENTO)
            .version(UPDATED_VERSION)
            .indDel(UPDATED_IND_DEL)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restMovimientoProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMovimientoProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMovimientoProducto))
            )
            .andExpect(status().isOk());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
        MovimientoProducto testMovimientoProducto = movimientoProductoList.get(movimientoProductoList.size() - 1);
        assertThat(testMovimientoProducto.getTipMovimiento()).isEqualTo(UPDATED_TIP_MOVIMIENTO);
        assertThat(testMovimientoProducto.getTip2Movimiento()).isEqualTo(UPDATED_TIP_2_MOVIMIENTO);
        assertThat(testMovimientoProducto.getPreVenta()).isEqualTo(UPDATED_PRE_VENTA);
        assertThat(testMovimientoProducto.getPrecCompra()).isEqualTo(UPDATED_PREC_COMPRA);
        assertThat(testMovimientoProducto.getCnt()).isEqualTo(UPDATED_CNT);
        assertThat(testMovimientoProducto.getLote()).isEqualTo(UPDATED_LOTE);
        assertThat(testMovimientoProducto.getFecMovimiento()).isEqualTo(UPDATED_FEC_MOVIMIENTO);
        assertThat(testMovimientoProducto.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMovimientoProducto.getIndDel()).isEqualTo(UPDATED_IND_DEL);
        assertThat(testMovimientoProducto.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testMovimientoProducto.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testMovimientoProducto.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testMovimientoProducto.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testMovimientoProducto.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testMovimientoProducto.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, movimientoProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isBadRequest());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMovimientoProducto() throws Exception {
        int databaseSizeBeforeUpdate = movimientoProductoRepository.findAll().size();
        movimientoProducto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMovimientoProductoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(movimientoProducto))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MovimientoProducto in the database
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMovimientoProducto() throws Exception {
        // Initialize the database
        movimientoProductoRepository.save(movimientoProducto);

        int databaseSizeBeforeDelete = movimientoProductoRepository.findAll().size();

        // Delete the movimientoProducto
        restMovimientoProductoMockMvc
            .perform(delete(ENTITY_API_URL_ID, movimientoProducto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MovimientoProducto> movimientoProductoList = movimientoProductoRepository.findAll();
        assertThat(movimientoProductoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
