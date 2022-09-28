package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.Proveedor;
import com.ppanticona.fabio.repository.ProveedorRepository;
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
 * REST controller for managing {@link com.ppanticona.fabio.domain.Proveedor}.
 */
@RestController
@RequestMapping("/api")
public class ProveedorResource {

    private final Logger log = LoggerFactory.getLogger(ProveedorResource.class);

    private static final String ENTITY_NAME = "proveedor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProveedorRepository proveedorRepository;

    public ProveedorResource(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    /**
     * {@code POST  /proveedors} : Create a new proveedor.
     *
     * @param proveedor the proveedor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proveedor, or with status {@code 400 (Bad Request)} if the proveedor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proveedors")
    public ResponseEntity<Proveedor> createProveedor(@Valid @RequestBody Proveedor proveedor) throws URISyntaxException {
        log.debug("REST request to save Proveedor : {}", proveedor);
        if (proveedor.getId() != null) {
            throw new BadRequestAlertException("A new proveedor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Proveedor result = proveedorRepository.save(proveedor);
        return ResponseEntity
            .created(new URI("/api/proveedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /proveedors/:id} : Updates an existing proveedor.
     *
     * @param id the id of the proveedor to save.
     * @param proveedor the proveedor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proveedor,
     * or with status {@code 400 (Bad Request)} if the proveedor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proveedor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proveedors/{id}")
    public ResponseEntity<Proveedor> updateProveedor(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Proveedor proveedor
    ) throws URISyntaxException {
        log.debug("REST request to update Proveedor : {}, {}", id, proveedor);
        if (proveedor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, proveedor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proveedorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Proveedor result = proveedorRepository.save(proveedor);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, proveedor.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /proveedors/:id} : Partial updates given fields of an existing proveedor, field will ignore if it is null
     *
     * @param id the id of the proveedor to save.
     * @param proveedor the proveedor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proveedor,
     * or with status {@code 400 (Bad Request)} if the proveedor is not valid,
     * or with status {@code 404 (Not Found)} if the proveedor is not found,
     * or with status {@code 500 (Internal Server Error)} if the proveedor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/proveedors/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Proveedor> partialUpdateProveedor(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Proveedor proveedor
    ) throws URISyntaxException {
        log.debug("REST request to partial update Proveedor partially : {}, {}", id, proveedor);
        if (proveedor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, proveedor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!proveedorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Proveedor> result = proveedorRepository
            .findById(proveedor.getId())
            .map(existingProveedor -> {
                if (proveedor.getTipDocProv() != null) {
                    existingProveedor.setTipDocProv(proveedor.getTipDocProv());
                }
                if (proveedor.getNroDocProv() != null) {
                    existingProveedor.setNroDocProv(proveedor.getNroDocProv());
                }
                if (proveedor.getNombresRazonSocProv() != null) {
                    existingProveedor.setNombresRazonSocProv(proveedor.getNombresRazonSocProv());
                }
                if (proveedor.getTel1() != null) {
                    existingProveedor.setTel1(proveedor.getTel1());
                }
                if (proveedor.getTel2() != null) {
                    existingProveedor.setTel2(proveedor.getTel2());
                }
                if (proveedor.getCorreo() != null) {
                    existingProveedor.setCorreo(proveedor.getCorreo());
                }
                if (proveedor.getDireccion() != null) {
                    existingProveedor.setDireccion(proveedor.getDireccion());
                }
                if (proveedor.getRefDireccion() != null) {
                    existingProveedor.setRefDireccion(proveedor.getRefDireccion());
                }
                if (proveedor.getDistrito() != null) {
                    existingProveedor.setDistrito(proveedor.getDistrito());
                }
                if (proveedor.getEstado() != null) {
                    existingProveedor.setEstado(proveedor.getEstado());
                }
                if (proveedor.getVersion() != null) {
                    existingProveedor.setVersion(proveedor.getVersion());
                }
                if (proveedor.getIndDel() != null) {
                    existingProveedor.setIndDel(proveedor.getIndDel());
                }
                if (proveedor.getFecCrea() != null) {
                    existingProveedor.setFecCrea(proveedor.getFecCrea());
                }
                if (proveedor.getUsuCrea() != null) {
                    existingProveedor.setUsuCrea(proveedor.getUsuCrea());
                }
                if (proveedor.getIpCrea() != null) {
                    existingProveedor.setIpCrea(proveedor.getIpCrea());
                }
                if (proveedor.getFecModif() != null) {
                    existingProveedor.setFecModif(proveedor.getFecModif());
                }
                if (proveedor.getUsuModif() != null) {
                    existingProveedor.setUsuModif(proveedor.getUsuModif());
                }
                if (proveedor.getIpModif() != null) {
                    existingProveedor.setIpModif(proveedor.getIpModif());
                }

                return existingProveedor;
            })
            .map(proveedorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, proveedor.getId())
        );
    }

    /**
     * {@code GET  /proveedors} : get all the proveedors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proveedors in body.
     */
    @GetMapping("/proveedors")
    public List<Proveedor> getAllProveedors() {
        log.debug("REST request to get all Proveedors");
        return proveedorRepository.findAll();
    }

    /**
     * {@code GET  /proveedors/:id} : get the "id" proveedor.
     *
     * @param id the id of the proveedor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proveedor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proveedors/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable String id) {
        log.debug("REST request to get Proveedor : {}", id);
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(proveedor);
    }

    /**
     * {@code DELETE  /proveedors/:id} : delete the "id" proveedor.
     *
     * @param id the id of the proveedor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proveedors/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable String id) {
        log.debug("REST request to delete Proveedor : {}", id);
        proveedorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @GetMapping("/proveedors/buscarportn/{tipDocProv}/{nroDocProv}")
    public ResponseEntity<Proveedor> getProveedorByTipAndNro(
        @PathVariable("tipDocProv") String tipDocProv,
        @PathVariable("nroDocProv") String nroDocProv
    ) {
        log.debug("REST request to getProveedorByTipAndNro Cliente : {}", tipDocProv, nroDocProv);
        Proveedor proveedor = proveedorRepository.findBytipDocProvAndNroDocProv(tipDocProv, nroDocProv);
        return ResponseEntity.ok().body(proveedor);
    }
}
