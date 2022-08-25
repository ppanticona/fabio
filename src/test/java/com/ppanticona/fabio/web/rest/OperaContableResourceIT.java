package com.ppanticona.fabio.web.rest;

import static com.ppanticona.fabio.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppanticona.fabio.IntegrationTest;
import com.ppanticona.fabio.domain.OperaContable;
import com.ppanticona.fabio.repository.OperaContableRepository;
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
 * Integration tests for the {@link OperaContableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OperaContableResourceIT {

    private static final String DEFAULT_DESC_OPERA = "AAAAAAAAAA";
    private static final String UPDATED_DESC_OPERA = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_RELA = "AAAAAAAAAA";
    private static final String UPDATED_AREA_RELA = "BBBBBBBBBB";

    private static final String DEFAULT_COD_LIB = "AAAAAAAAAA";
    private static final String UPDATED_COD_LIB = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/opera-contables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private OperaContableRepository operaContableRepository;

    @Autowired
    private MockMvc restOperaContableMockMvc;

    private OperaContable operaContable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperaContable createEntity() {
        OperaContable operaContable = new OperaContable()
            .descOpera(DEFAULT_DESC_OPERA)
            .areaRela(DEFAULT_AREA_RELA)
            .codLib(DEFAULT_COD_LIB)
            .fecCrea(DEFAULT_FEC_CREA)
            .usuCrea(DEFAULT_USU_CREA)
            .ipCrea(DEFAULT_IP_CREA)
            .fecModif(DEFAULT_FEC_MODIF)
            .usuModif(DEFAULT_USU_MODIF)
            .ipModif(DEFAULT_IP_MODIF);
        return operaContable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperaContable createUpdatedEntity() {
        OperaContable operaContable = new OperaContable()
            .descOpera(UPDATED_DESC_OPERA)
            .areaRela(UPDATED_AREA_RELA)
            .codLib(UPDATED_COD_LIB)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);
        return operaContable;
    }

    @BeforeEach
    public void initTest() {
        operaContableRepository.deleteAll();
        operaContable = createEntity();
    }

    @Test
    void createOperaContable() throws Exception {
        int databaseSizeBeforeCreate = operaContableRepository.findAll().size();
        // Create the OperaContable
        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isCreated());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeCreate + 1);
        OperaContable testOperaContable = operaContableList.get(operaContableList.size() - 1);
        assertThat(testOperaContable.getDescOpera()).isEqualTo(DEFAULT_DESC_OPERA);
        assertThat(testOperaContable.getAreaRela()).isEqualTo(DEFAULT_AREA_RELA);
        assertThat(testOperaContable.getCodLib()).isEqualTo(DEFAULT_COD_LIB);
        assertThat(testOperaContable.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testOperaContable.getUsuCrea()).isEqualTo(DEFAULT_USU_CREA);
        assertThat(testOperaContable.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testOperaContable.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testOperaContable.getUsuModif()).isEqualTo(DEFAULT_USU_MODIF);
        assertThat(testOperaContable.getIpModif()).isEqualTo(DEFAULT_IP_MODIF);
    }

    @Test
    void createOperaContableWithExistingId() throws Exception {
        // Create the OperaContable with an existing ID
        operaContable.setId("existing_id");

        int databaseSizeBeforeCreate = operaContableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isBadRequest());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDescOperaIsRequired() throws Exception {
        int databaseSizeBeforeTest = operaContableRepository.findAll().size();
        // set the field null
        operaContable.setDescOpera(null);

        // Create the OperaContable, which fails.

        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isBadRequest());

        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFecCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = operaContableRepository.findAll().size();
        // set the field null
        operaContable.setFecCrea(null);

        // Create the OperaContable, which fails.

        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isBadRequest());

        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUsuCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = operaContableRepository.findAll().size();
        // set the field null
        operaContable.setUsuCrea(null);

        // Create the OperaContable, which fails.

        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isBadRequest());

        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIpCreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = operaContableRepository.findAll().size();
        // set the field null
        operaContable.setIpCrea(null);

        // Create the OperaContable, which fails.

        restOperaContableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isBadRequest());

        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllOperaContables() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        // Get all the operaContableList
        restOperaContableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operaContable.getId())))
            .andExpect(jsonPath("$.[*].descOpera").value(hasItem(DEFAULT_DESC_OPERA)))
            .andExpect(jsonPath("$.[*].areaRela").value(hasItem(DEFAULT_AREA_RELA)))
            .andExpect(jsonPath("$.[*].codLib").value(hasItem(DEFAULT_COD_LIB)))
            .andExpect(jsonPath("$.[*].fecCrea").value(hasItem(sameInstant(DEFAULT_FEC_CREA))))
            .andExpect(jsonPath("$.[*].usuCrea").value(hasItem(DEFAULT_USU_CREA)))
            .andExpect(jsonPath("$.[*].ipCrea").value(hasItem(DEFAULT_IP_CREA)))
            .andExpect(jsonPath("$.[*].fecModif").value(hasItem(sameInstant(DEFAULT_FEC_MODIF))))
            .andExpect(jsonPath("$.[*].usuModif").value(hasItem(DEFAULT_USU_MODIF)))
            .andExpect(jsonPath("$.[*].ipModif").value(hasItem(DEFAULT_IP_MODIF)));
    }

    @Test
    void getOperaContable() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        // Get the operaContable
        restOperaContableMockMvc
            .perform(get(ENTITY_API_URL_ID, operaContable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operaContable.getId()))
            .andExpect(jsonPath("$.descOpera").value(DEFAULT_DESC_OPERA))
            .andExpect(jsonPath("$.areaRela").value(DEFAULT_AREA_RELA))
            .andExpect(jsonPath("$.codLib").value(DEFAULT_COD_LIB))
            .andExpect(jsonPath("$.fecCrea").value(sameInstant(DEFAULT_FEC_CREA)))
            .andExpect(jsonPath("$.usuCrea").value(DEFAULT_USU_CREA))
            .andExpect(jsonPath("$.ipCrea").value(DEFAULT_IP_CREA))
            .andExpect(jsonPath("$.fecModif").value(sameInstant(DEFAULT_FEC_MODIF)))
            .andExpect(jsonPath("$.usuModif").value(DEFAULT_USU_MODIF))
            .andExpect(jsonPath("$.ipModif").value(DEFAULT_IP_MODIF));
    }

    @Test
    void getNonExistingOperaContable() throws Exception {
        // Get the operaContable
        restOperaContableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewOperaContable() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();

        // Update the operaContable
        OperaContable updatedOperaContable = operaContableRepository.findById(operaContable.getId()).get();
        updatedOperaContable
            .descOpera(UPDATED_DESC_OPERA)
            .areaRela(UPDATED_AREA_RELA)
            .codLib(UPDATED_COD_LIB)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOperaContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
        OperaContable testOperaContable = operaContableList.get(operaContableList.size() - 1);
        assertThat(testOperaContable.getDescOpera()).isEqualTo(UPDATED_DESC_OPERA);
        assertThat(testOperaContable.getAreaRela()).isEqualTo(UPDATED_AREA_RELA);
        assertThat(testOperaContable.getCodLib()).isEqualTo(UPDATED_COD_LIB);
        assertThat(testOperaContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testOperaContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testOperaContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testOperaContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testOperaContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void putNonExistingOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, operaContable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operaContable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOperaContableWithPatch() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();

        // Update the operaContable using partial update
        OperaContable partialUpdatedOperaContable = new OperaContable();
        partialUpdatedOperaContable.setId(operaContable.getId());

        partialUpdatedOperaContable
            .descOpera(UPDATED_DESC_OPERA)
            .usuCrea(UPDATED_USU_CREA)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
        OperaContable testOperaContable = operaContableList.get(operaContableList.size() - 1);
        assertThat(testOperaContable.getDescOpera()).isEqualTo(UPDATED_DESC_OPERA);
        assertThat(testOperaContable.getAreaRela()).isEqualTo(DEFAULT_AREA_RELA);
        assertThat(testOperaContable.getCodLib()).isEqualTo(DEFAULT_COD_LIB);
        assertThat(testOperaContable.getFecCrea()).isEqualTo(DEFAULT_FEC_CREA);
        assertThat(testOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testOperaContable.getIpCrea()).isEqualTo(DEFAULT_IP_CREA);
        assertThat(testOperaContable.getFecModif()).isEqualTo(DEFAULT_FEC_MODIF);
        assertThat(testOperaContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testOperaContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void fullUpdateOperaContableWithPatch() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();

        // Update the operaContable using partial update
        OperaContable partialUpdatedOperaContable = new OperaContable();
        partialUpdatedOperaContable.setId(operaContable.getId());

        partialUpdatedOperaContable
            .descOpera(UPDATED_DESC_OPERA)
            .areaRela(UPDATED_AREA_RELA)
            .codLib(UPDATED_COD_LIB)
            .fecCrea(UPDATED_FEC_CREA)
            .usuCrea(UPDATED_USU_CREA)
            .ipCrea(UPDATED_IP_CREA)
            .fecModif(UPDATED_FEC_MODIF)
            .usuModif(UPDATED_USU_MODIF)
            .ipModif(UPDATED_IP_MODIF);

        restOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperaContable))
            )
            .andExpect(status().isOk());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
        OperaContable testOperaContable = operaContableList.get(operaContableList.size() - 1);
        assertThat(testOperaContable.getDescOpera()).isEqualTo(UPDATED_DESC_OPERA);
        assertThat(testOperaContable.getAreaRela()).isEqualTo(UPDATED_AREA_RELA);
        assertThat(testOperaContable.getCodLib()).isEqualTo(UPDATED_COD_LIB);
        assertThat(testOperaContable.getFecCrea()).isEqualTo(UPDATED_FEC_CREA);
        assertThat(testOperaContable.getUsuCrea()).isEqualTo(UPDATED_USU_CREA);
        assertThat(testOperaContable.getIpCrea()).isEqualTo(UPDATED_IP_CREA);
        assertThat(testOperaContable.getFecModif()).isEqualTo(UPDATED_FEC_MODIF);
        assertThat(testOperaContable.getUsuModif()).isEqualTo(UPDATED_USU_MODIF);
        assertThat(testOperaContable.getIpModif()).isEqualTo(UPDATED_IP_MODIF);
    }

    @Test
    void patchNonExistingOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, operaContable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operaContable))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOperaContable() throws Exception {
        int databaseSizeBeforeUpdate = operaContableRepository.findAll().size();
        operaContable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperaContableMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(operaContable))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperaContable in the database
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOperaContable() throws Exception {
        // Initialize the database
        operaContableRepository.save(operaContable);

        int databaseSizeBeforeDelete = operaContableRepository.findAll().size();

        // Delete the operaContable
        restOperaContableMockMvc
            .perform(delete(ENTITY_API_URL_ID, operaContable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperaContable> operaContableList = operaContableRepository.findAll();
        assertThat(operaContableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
