package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.PlanContable;
import com.ppanticona.fabio.repository.PlanContableRepository;
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
 * Integration tests for the {@link PlanContableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanContableResourceIT {

    private static final String DEFAULT_TIP_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_TIP_PLAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_NIV_PLAN = 1;
    private static final Integer UPDATED_NIV_PLAN = 2;

    private static final String DEFAULT_COD_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_COD_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_CUENTA = "AAAAAAAAAA";
    private static final String UPDATED_DESC_CUENTA = "BBBBBBBBBB";

    private static final String DEFAULT_ANHO_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_ANHO_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_RESO_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_RESO_PLAN = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/plan-contables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PlanContableRepository planContableRepository;

    @Autowired
    private MockMvc restPlanContableMockMvc;

    private PlanContable planContable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanContable createEntity() {
        PlanContable planContable = new PlanContable()
            .tipPlan(DEFAULT_TIP_PLAN)
            .nivPlan(DEFAULT_NIV_PLAN)
            .codPlan(DEFAULT_COD_PLAN)
            .descCuenta(DEFAULT_DESC_CUENTA)
            .anhoPlan(DEFAULT_ANHO_PLAN)
            .resoPlan(DEFAULT_RESO_PLAN)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return planContable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanContable createUpdatedEntity() {
        PlanContable planContable = new PlanContable()
            .tipPlan(UPDATED_TIP_PLAN)
            .nivPlan(UPDATED_NIV_PLAN)
            .codPlan(UPDATED_COD_PLAN)
            .descCuenta(UPDATED_DESC_CUENTA)
            .anhoPlan(UPDATED_ANHO_PLAN)
            .resoPlan(UPDATED_RESO_PLAN)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return planContable;
    }

    @BeforeEach
    public void initTest() {
        planContableRepository.deleteAll();
        planContable = createEntity();
    }

    @Test
    void createPlanContable() throws Exception {
        int databaseSizeBeforeCreate = planContableRepository.findAll().size();
        // Create the PlanContable
        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isCreated());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeCreate + 1);
        PlanContable testPlanContable = planContableList.get(planContableList.size() - 1);
        assertThat(testPlanContable.getTipPlan()).isEqualTo(DEFAULT_TIP_PLAN);
        assertThat(testPlanContable.getNivPlan()).isEqualTo(DEFAULT_NIV_PLAN);
        assertThat(testPlanContable.getCodPlan()).isEqualTo(DEFAULT_COD_PLAN);
        assertThat(testPlanContable.getDescCuenta()).isEqualTo(DEFAULT_DESC_CUENTA);
        assertThat(testPlanContable.getAnhoPlan()).isEqualTo(DEFAULT_ANHO_PLAN);
        assertThat(testPlanContable.getResoPlan()).isEqualTo(DEFAULT_RESO_PLAN);
        assertThat(testPlanContable.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testPlanContable.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testPlanContable.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testPlanContable.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testPlanContable.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testPlanContable.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createPlanContableWithExistingId() throws Exception {
        // Create the PlanContable with an existing ID
        planContable.setId("existing_id");

        int databaseSizeBeforeCreate = planContableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNivPlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setNivPlan(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodPlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setCodPlan(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescCuentaIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setDescCuenta(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setFecCrea(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setUsuCrea(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = planContableRepository.findAll().size();
        // set the field null
        planContable.setIpCrea(null);

        // Create the PlanContable, which fails.

        restPlanContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isBadRequest());

        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPlanContables() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        // Get all the planContableList
        restPlanContableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planContable.getId())))
            .andExpect(jsonPath("$.[*].tipPlan").value(hasItem(DEFAULT_TIP_PLAN)))
            .andExpect(jsonPath("$.[*].nivPlan").value(hasItem(DEFAULT_NIV_PLAN)))
            .andExpect(jsonPath("$.[*].codPlan").value(hasItem(DEFAULT_COD_PLAN)))
            .andExpect(jsonPath("$.[*].descCuenta").value(hasItem(DEFAULT_DESC_CUENTA)))
            .andExpect(jsonPath("$.[*].anhoPlan").value(hasItem(DEFAULT_ANHO_PLAN)))
            .andExpect(jsonPath("$.[*].resoPlan").value(hasItem(DEFAULT_RESO_PLAN)))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getPlanContable() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        // Get the planContable
        restPlanContableMockMvc
            .perform(get(ENTITY_API_URL_ID, planContable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planContable.getId()))
            .andExpect(jsonPath("$.tipPlan").value(DEFAULT_TIP_PLAN))
            .andExpect(jsonPath("$.nivPlan").value(DEFAULT_NIV_PLAN))
            .andExpect(jsonPath("$.codPlan").value(DEFAULT_COD_PLAN))
            .andExpect(jsonPath("$.descCuenta").value(DEFAULT_DESC_CUENTA))
            .andExpect(jsonPath("$.anhoPlan").value(DEFAULT_ANHO_PLAN))
            .andExpect(jsonPath("$.resoPlan").value(DEFAULT_RESO_PLAN))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingPlanContable() throws Exception {
        // Get the planContable
        restPlanContableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewPlanContable() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();

        // Update the planContable
        PlanContable updatedPlanContable = planContableRepository.findById(planContable.getId()).get();
        updatedPlanContable
            .tipPlan(UPDATED_TIP_PLAN)
            .nivPlan(UPDATED_NIV_PLAN)
            .codPlan(UPDATED_COD_PLAN)
            .descCuenta(UPDATED_DESC_CUENTA)
            .anhoPlan(UPDATED_ANHO_PLAN)
            .resoPlan(UPDATED_RESO_PLAN)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPlanContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPlanContable))
            )
            .andExpect(status().isOk());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
        PlanContable testPlanContable = planContableList.get(planContableList.size() - 1);
        assertThat(testPlanContable.getTipPlan()).isEqualTo(UPDATED_TIP_PLAN);
        assertThat(testPlanContable.getNivPlan()).isEqualTo(UPDATED_NIV_PLAN);
        assertThat(testPlanContable.getCodPlan()).isEqualTo(UPDATED_COD_PLAN);
        assertThat(testPlanContable.getDescCuenta()).isEqualTo(UPDATED_DESC_CUENTA);
        assertThat(testPlanContable.getAnhoPlan()).isEqualTo(UPDATED_ANHO_PLAN);
        assertThat(testPlanContable.getResoPlan()).isEqualTo(UPDATED_RESO_PLAN);
        assertThat(testPlanContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPlanContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPlanContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPlanContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPlanContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPlanContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(planContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(planContable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePlanContableWithPatch() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();

        // Update the planContable using partial update
        PlanContable partialUpdatedPlanContable = new PlanContable();
        partialUpdatedPlanContable.setId(planContable.getId());

        partialUpdatedPlanContable
            .descCuenta(UPDATED_DESC_CUENTA)
            .anhoPlan(UPDATED_ANHO_PLAN)
            .resoPlan(UPDATED_RESO_PLAN)
            .fecCrea(UPDATED_FEC_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPlanContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanContable))
            )
            .andExpect(status().isOk());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
        PlanContable testPlanContable = planContableList.get(planContableList.size() - 1);
        assertThat(testPlanContable.getTipPlan()).isEqualTo(DEFAULT_TIP_PLAN);
        assertThat(testPlanContable.getNivPlan()).isEqualTo(DEFAULT_NIV_PLAN);
        assertThat(testPlanContable.getCodPlan()).isEqualTo(DEFAULT_COD_PLAN);
        assertThat(testPlanContable.getDescCuenta()).isEqualTo(UPDATED_DESC_CUENTA);
        assertThat(testPlanContable.getAnhoPlan()).isEqualTo(UPDATED_ANHO_PLAN);
        assertThat(testPlanContable.getResoPlan()).isEqualTo(UPDATED_RESO_PLAN);
        assertThat(testPlanContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPlanContable.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testPlanContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPlanContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPlanContable.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testPlanContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdatePlanContableWithPatch() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();

        // Update the planContable using partial update
        PlanContable partialUpdatedPlanContable = new PlanContable();
        partialUpdatedPlanContable.setId(planContable.getId());

        partialUpdatedPlanContable
            .tipPlan(UPDATED_TIP_PLAN)
            .nivPlan(UPDATED_NIV_PLAN)
            .codPlan(UPDATED_COD_PLAN)
            .descCuenta(UPDATED_DESC_CUENTA)
            .anhoPlan(UPDATED_ANHO_PLAN)
            .resoPlan(UPDATED_RESO_PLAN)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restPlanContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlanContable))
            )
            .andExpect(status().isOk());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
        PlanContable testPlanContable = planContableList.get(planContableList.size() - 1);
        assertThat(testPlanContable.getTipPlan()).isEqualTo(UPDATED_TIP_PLAN);
        assertThat(testPlanContable.getNivPlan()).isEqualTo(UPDATED_NIV_PLAN);
        assertThat(testPlanContable.getCodPlan()).isEqualTo(UPDATED_COD_PLAN);
        assertThat(testPlanContable.getDescCuenta()).isEqualTo(UPDATED_DESC_CUENTA);
        assertThat(testPlanContable.getAnhoPlan()).isEqualTo(UPDATED_ANHO_PLAN);
        assertThat(testPlanContable.getResoPlan()).isEqualTo(UPDATED_RESO_PLAN);
        assertThat(testPlanContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testPlanContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testPlanContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testPlanContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testPlanContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testPlanContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(planContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPlanContable() throws Exception {
        int databaseSizeBeforeUpdate = planContableRepository.findAll().size();
        planContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanContableMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(planContable))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanContable in the database
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePlanContable() throws Exception {
        // Initialize the database
        planContableRepository.save(planContable);

        int databaseSizeBeforeDelete = planContableRepository.findAll().size();

        // Delete the planContable
        restPlanContableMockMvc
            .perform(delete(ENTITY_API_URL_ID, planContable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanContable> planContableList = planContableRepository.findAll();
        assertThat(planContableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
