package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.PlanContable;
import com.ppanticona.fabio.repository.PlanContableRepository;
import com.ppanticona.fabio.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.fabio.domain.PlanContable}.
 */
@RestController
@RequestMapping("/api")
public class PlanContableResource {

    private final Logger log = LoggerFactory.getLogger(PlanContableResource.class);

    private static final String ENTITY_NAME = "planContable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanContableRepository planContableRepository;

    public PlanContableResource(PlanContableRepository planContableRepository) {
        this.planContableRepository = planContableRepository;
    }

    /**
     * {@code POST  /plan-contables} : Create a new planContable.
     *
     * @param planContable the planContable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planContable, or with status {@code 400 (Bad Request)} if the planContable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-contables")
    public ResponseEntity<PlanContable> createPlanContable(@Valid @RequestBody PlanContable planContable) throws URISyntaxException {
        log.debug("REST request to save PlanContable : {}", planContable);
        if (planContable.getId() != null) {
            throw new BadRequestAlertException("A new planContable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanContable result = planContableRepository.save(planContable);
        return ResponseEntity
            .created(new URI("/api/plan-contables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-contables/:id} : Updates an existing planContable.
     *
     * @param id the id of the planContable to save.
     * @param planContable the planContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planContable,
     * or with status {@code 400 (Bad Request)} if the planContable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-contables/{id}")
    public ResponseEntity<PlanContable> updatePlanContable(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody PlanContable planContable
    ) throws URISyntaxException {
        log.debug("REST request to update PlanContable : {}, {}", id, planContable);
        if (planContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PlanContable result = planContableRepository.save(planContable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planContable.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /plan-contables/:id} : Partial updates given fields of an existing planContable, field will ignore if it is null
     *
     * @param id the id of the planContable to save.
     * @param planContable the planContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planContable,
     * or with status {@code 400 (Bad Request)} if the planContable is not valid,
     * or with status {@code 404 (Not Found)} if the planContable is not found,
     * or with status {@code 500 (Internal Server Error)} if the planContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/plan-contables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PlanContable> partialUpdatePlanContable(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody PlanContable planContable
    ) throws URISyntaxException {
        log.debug("REST request to partial update PlanContable partially : {}, {}", id, planContable);
        if (planContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, planContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!planContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PlanContable> result = planContableRepository
            .findById(planContable.getId())
            .map(existingPlanContable -> {
                if (planContable.getTipPlan() != null) {
                    existingPlanContable.setTipPlan(planContable.getTipPlan());
                }
                if (planContable.getNivPlan() != null) {
                    existingPlanContable.setNivPlan(planContable.getNivPlan());
                }
                if (planContable.getCodPlan() != null) {
                    existingPlanContable.setCodPlan(planContable.getCodPlan());
                }
                if (planContable.getDescCuenta() != null) {
                    existingPlanContable.setDescCuenta(planContable.getDescCuenta());
                }
                if (planContable.getAnhoPlan() != null) {
                    existingPlanContable.setAnhoPlan(planContable.getAnhoPlan());
                }
                if (planContable.getResoPlan() != null) {
                    existingPlanContable.setResoPlan(planContable.getResoPlan());
                }
                if (planContable.getFecCrea() != null) {
                    existingPlanContable.setFecCrea(planContable.getFecCrea());
                }
                if (planContable.getUsuCrea() != null) {
                    existingPlanContable.setUsuCrea(planContable.getUsuCrea());
                }
                if (planContable.getIpCrea() != null) {
                    existingPlanContable.setIpCrea(planContable.getIpCrea());
                }
                if (planContable.getFecModif() != null) {
                    existingPlanContable.setFecModif(planContable.getFecModif());
                }
                if (planContable.getUsuModif() != null) {
                    existingPlanContable.setUsuModif(planContable.getUsuModif());
                }
                if (planContable.getIpModif() != null) {
                    existingPlanContable.setIpModif(planContable.getIpModif());
                }

                return existingPlanContable;
            })
            .map(planContableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, planContable.getId())
        );
    }

    /**
     * {@code GET  /plan-contables} : get all the planContables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planContables in body.
     */
    @GetMapping("/plan-contables")
    public List<PlanContable> getAllPlanContables() {
        log.debug("REST request to get all PlanContables");
        return planContableRepository.findAll();
    }

    /**
     * {@code GET  /plan-contables/:id} : get the "id" planContable.
     *
     * @param id the id of the planContable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planContable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-contables/{id}")
    public ResponseEntity<PlanContable> getPlanContable(@PathVariable String id) {
        log.debug("REST request to get PlanContable : {}", id);
        Optional<PlanContable> planContable = planContableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(planContable);
    }

    /**
     * {@code DELETE  /plan-contables/:id} : delete the "id" planContable.
     *
     * @param id the id of the planContable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-contables/{id}")
    public ResponseEntity<Void> deletePlanContable(@PathVariable String id) {
        log.debug("REST request to delete PlanContable : {}", id);
        planContableRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
