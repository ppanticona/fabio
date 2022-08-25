package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.OperaContable;
import com.ppanticona.fabio.repository.OperaContableRepository;
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
 * REST controller for managing {@link com.ppanticona.fabio.domain.OperaContable}.
 */
@RestController
@RequestMapping("/api")
public class OperaContableResource {

    private final Logger log = LoggerFactory.getLogger(OperaContableResource.class);

    private static final String ENTITY_NAME = "operaContable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperaContableRepository operaContableRepository;

    public OperaContableResource(OperaContableRepository operaContableRepository) {
        this.operaContableRepository = operaContableRepository;
    }

    /**
     * {@code POST  /opera-contables} : Create a new operaContable.
     *
     * @param operaContable the operaContable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operaContable, or with status {@code 400 (Bad Request)} if the operaContable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opera-contables")
    public ResponseEntity<OperaContable> createOperaContable(@Valid @RequestBody OperaContable operaContable) throws URISyntaxException {
        log.debug("REST request to save OperaContable : {}", operaContable);
        if (operaContable.getId() != null) {
            throw new BadRequestAlertException("A new operaContable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperaContable result = operaContableRepository.save(operaContable);
        return ResponseEntity
            .created(new URI("/api/opera-contables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /opera-contables/:id} : Updates an existing operaContable.
     *
     * @param id the id of the operaContable to save.
     * @param operaContable the operaContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operaContable,
     * or with status {@code 400 (Bad Request)} if the operaContable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operaContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opera-contables/{id}")
    public ResponseEntity<OperaContable> updateOperaContable(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody OperaContable operaContable
    ) throws URISyntaxException {
        log.debug("REST request to update OperaContable : {}, {}", id, operaContable);
        if (operaContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operaContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operaContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperaContable result = operaContableRepository.save(operaContable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, operaContable.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /opera-contables/:id} : Partial updates given fields of an existing operaContable, field will ignore if it is null
     *
     * @param id the id of the operaContable to save.
     * @param operaContable the operaContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operaContable,
     * or with status {@code 400 (Bad Request)} if the operaContable is not valid,
     * or with status {@code 404 (Not Found)} if the operaContable is not found,
     * or with status {@code 500 (Internal Server Error)} if the operaContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/opera-contables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OperaContable> partialUpdateOperaContable(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody OperaContable operaContable
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperaContable partially : {}, {}", id, operaContable);
        if (operaContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operaContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operaContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperaContable> result = operaContableRepository
            .findById(operaContable.getId())
            .map(existingOperaContable -> {
                if (operaContable.getDescOpera() != null) {
                    existingOperaContable.setDescOpera(operaContable.getDescOpera());
                }
                if (operaContable.getAreaRela() != null) {
                    existingOperaContable.setAreaRela(operaContable.getAreaRela());
                }
                if (operaContable.getCodLib() != null) {
                    existingOperaContable.setCodLib(operaContable.getCodLib());
                }
                if (operaContable.getFecCrea() != null) {
                    existingOperaContable.setFecCrea(operaContable.getFecCrea());
                }
                if (operaContable.getUsuCrea() != null) {
                    existingOperaContable.setUsuCrea(operaContable.getUsuCrea());
                }
                if (operaContable.getIpCrea() != null) {
                    existingOperaContable.setIpCrea(operaContable.getIpCrea());
                }
                if (operaContable.getFecModif() != null) {
                    existingOperaContable.setFecModif(operaContable.getFecModif());
                }
                if (operaContable.getUsuModif() != null) {
                    existingOperaContable.setUsuModif(operaContable.getUsuModif());
                }
                if (operaContable.getIpModif() != null) {
                    existingOperaContable.setIpModif(operaContable.getIpModif());
                }

                return existingOperaContable;
            })
            .map(operaContableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, operaContable.getId())
        );
    }

    /**
     * {@code GET  /opera-contables} : get all the operaContables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operaContables in body.
     */
    @GetMapping("/opera-contables")
    public List<OperaContable> getAllOperaContables() {
        log.debug("REST request to get all OperaContables");
        return operaContableRepository.findAll();
    }

    /**
     * {@code GET  /opera-contables/:id} : get the "id" operaContable.
     *
     * @param id the id of the operaContable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operaContable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opera-contables/{id}")
    public ResponseEntity<OperaContable> getOperaContable(@PathVariable String id) {
        log.debug("REST request to get OperaContable : {}", id);
        Optional<OperaContable> operaContable = operaContableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(operaContable);
    }

    /**
     * {@code DELETE  /opera-contables/:id} : delete the "id" operaContable.
     *
     * @param id the id of the operaContable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opera-contables/{id}")
    public ResponseEntity<Void> deleteOperaContable(@PathVariable String id) {
        log.debug("REST request to delete OperaContable : {}", id);
        operaContableRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
