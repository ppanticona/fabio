package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.DetalleOperaContable;
import com.ppanticona.fabio.repository.DetalleOperaContableRepository;
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
 * REST controller for managing {@link com.ppanticona.fabio.domain.DetalleOperaContable}.
 */
@RestController
@RequestMapping("/api")
public class DetalleOperaContableResource {

    private final Logger log = LoggerFactory.getLogger(DetalleOperaContableResource.class);

    private static final String ENTITY_NAME = "detalleOperaContable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetalleOperaContableRepository detalleOperaContableRepository;

    public DetalleOperaContableResource(DetalleOperaContableRepository detalleOperaContableRepository) {
        this.detalleOperaContableRepository = detalleOperaContableRepository;
    }

    /**
     * {@code POST  /detalle-opera-contables} : Create a new detalleOperaContable.
     *
     * @param detalleOperaContable the detalleOperaContable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detalleOperaContable, or with status {@code 400 (Bad Request)} if the detalleOperaContable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detalle-opera-contables")
    public ResponseEntity<DetalleOperaContable> createDetalleOperaContable(@Valid @RequestBody DetalleOperaContable detalleOperaContable)
        throws URISyntaxException {
        log.debug("REST request to save DetalleOperaContable : {}", detalleOperaContable);
        if (detalleOperaContable.getId() != null) {
            throw new BadRequestAlertException("A new detalleOperaContable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetalleOperaContable result = detalleOperaContableRepository.save(detalleOperaContable);
        return ResponseEntity
            .created(new URI("/api/detalle-opera-contables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /detalle-opera-contables/:id} : Updates an existing detalleOperaContable.
     *
     * @param id the id of the detalleOperaContable to save.
     * @param detalleOperaContable the detalleOperaContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detalleOperaContable,
     * or with status {@code 400 (Bad Request)} if the detalleOperaContable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detalleOperaContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detalle-opera-contables/{id}")
    public ResponseEntity<DetalleOperaContable> updateDetalleOperaContable(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DetalleOperaContable detalleOperaContable
    ) throws URISyntaxException {
        log.debug("REST request to update DetalleOperaContable : {}, {}", id, detalleOperaContable);
        if (detalleOperaContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detalleOperaContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detalleOperaContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetalleOperaContable result = detalleOperaContableRepository.save(detalleOperaContable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, detalleOperaContable.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /detalle-opera-contables/:id} : Partial updates given fields of an existing detalleOperaContable, field will ignore if it is null
     *
     * @param id the id of the detalleOperaContable to save.
     * @param detalleOperaContable the detalleOperaContable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detalleOperaContable,
     * or with status {@code 400 (Bad Request)} if the detalleOperaContable is not valid,
     * or with status {@code 404 (Not Found)} if the detalleOperaContable is not found,
     * or with status {@code 500 (Internal Server Error)} if the detalleOperaContable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/detalle-opera-contables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetalleOperaContable> partialUpdateDetalleOperaContable(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DetalleOperaContable detalleOperaContable
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetalleOperaContable partially : {}, {}", id, detalleOperaContable);
        if (detalleOperaContable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detalleOperaContable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detalleOperaContableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetalleOperaContable> result = detalleOperaContableRepository
            .findById(detalleOperaContable.getId())
            .map(existingDetalleOperaContable -> {
                if (detalleOperaContable.getOperador() != null) {
                    existingDetalleOperaContable.setOperador(detalleOperaContable.getOperador());
                }
                if (detalleOperaContable.getValorOperacion() != null) {
                    existingDetalleOperaContable.setValorOperacion(detalleOperaContable.getValorOperacion());
                }
                if (detalleOperaContable.getDescripcion() != null) {
                    existingDetalleOperaContable.setDescripcion(detalleOperaContable.getDescripcion());
                }
                if (detalleOperaContable.getTipMovimiento() != null) {
                    existingDetalleOperaContable.setTipMovimiento(detalleOperaContable.getTipMovimiento());
                }
                if (detalleOperaContable.getFecCrea() != null) {
                    existingDetalleOperaContable.setFecCrea(detalleOperaContable.getFecCrea());
                }
                if (detalleOperaContable.getUsuCrea() != null) {
                    existingDetalleOperaContable.setUsuCrea(detalleOperaContable.getUsuCrea());
                }
                if (detalleOperaContable.getIpCrea() != null) {
                    existingDetalleOperaContable.setIpCrea(detalleOperaContable.getIpCrea());
                }
                if (detalleOperaContable.getFecModif() != null) {
                    existingDetalleOperaContable.setFecModif(detalleOperaContable.getFecModif());
                }
                if (detalleOperaContable.getUsuModif() != null) {
                    existingDetalleOperaContable.setUsuModif(detalleOperaContable.getUsuModif());
                }
                if (detalleOperaContable.getIpModif() != null) {
                    existingDetalleOperaContable.setIpModif(detalleOperaContable.getIpModif());
                }

                return existingDetalleOperaContable;
            })
            .map(detalleOperaContableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, detalleOperaContable.getId())
        );
    }

    /**
     * {@code GET  /detalle-opera-contables} : get all the detalleOperaContables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detalleOperaContables in body.
     */
    @GetMapping("/detalle-opera-contables")
    public List<DetalleOperaContable> getAllDetalleOperaContables() {
        log.debug("REST request to get all DetalleOperaContables");
        return detalleOperaContableRepository.findAll();
    }

    /**
     * {@code GET  /detalle-opera-contables/:id} : get the "id" detalleOperaContable.
     *
     * @param id the id of the detalleOperaContable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detalleOperaContable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detalle-opera-contables/{id}")
    public ResponseEntity<DetalleOperaContable> getDetalleOperaContable(@PathVariable String id) {
        log.debug("REST request to get DetalleOperaContable : {}", id);
        Optional<DetalleOperaContable> detalleOperaContable = detalleOperaContableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(detalleOperaContable);
    }

    /**
     * {@code DELETE  /detalle-opera-contables/:id} : delete the "id" detalleOperaContable.
     *
     * @param id the id of the detalleOperaContable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detalle-opera-contables/{id}")
    public ResponseEntity<Void> deleteDetalleOperaContable(@PathVariable String id) {
        log.debug("REST request to delete DetalleOperaContable : {}", id);
        detalleOperaContableRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
