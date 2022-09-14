package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.MovimientoProducto;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
import com.ppanticona.fabio.service.MovimientoProductoService;
import com.ppanticona.fabio.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.fabio.domain.MovimientoProducto}.
 */
@RestController
@RequestMapping("/api")
public class MovimientoProductoResource {

    private final Logger log = LoggerFactory.getLogger(MovimientoProductoResource.class);

    private static final String ENTITY_NAME = "movimientoProducto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovimientoProductoRepository movimientoProductoRepository;
    private final MovimientoProductoService movimientoProductoService;

    public MovimientoProductoResource(
        MovimientoProductoRepository movimientoProductoRepository,
        MovimientoProductoService movimientoProductoService
    ) {
        this.movimientoProductoRepository = movimientoProductoRepository;
        this.movimientoProductoService = movimientoProductoService;
    }

    /**
     * {@code POST  /movimiento-productos} : Create a new movimientoProducto.
     *
     * @param movimientoProducto the movimientoProducto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movimientoProducto, or with status {@code 400 (Bad Request)} if the movimientoProducto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movimiento-productos")
    public ResponseEntity<MovimientoProducto> createMovimientoProducto(@Valid @RequestBody MovimientoProducto movimientoProducto)
        throws URISyntaxException {
        log.debug("REST request to save MovimientoProducto : {}", movimientoProducto);
        if (movimientoProducto.getId() != null) {
            throw new BadRequestAlertException("A new movimientoProducto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovimientoProducto result = movimientoProductoRepository.save(movimientoProducto);
        return ResponseEntity
            .created(new URI("/api/movimiento-productos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /movimiento-productos/:id} : Updates an existing movimientoProducto.
     *
     * @param id the id of the movimientoProducto to save.
     * @param movimientoProducto the movimientoProducto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movimientoProducto,
     * or with status {@code 400 (Bad Request)} if the movimientoProducto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movimientoProducto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movimiento-productos/{id}")
    public ResponseEntity<MovimientoProducto> updateMovimientoProducto(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody MovimientoProducto movimientoProducto
    ) throws URISyntaxException {
        log.debug("REST request to update MovimientoProducto : {}, {}", id, movimientoProducto);
        if (movimientoProducto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, movimientoProducto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!movimientoProductoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MovimientoProducto result = movimientoProductoRepository.save(movimientoProducto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, movimientoProducto.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /movimiento-productos/:id} : Partial updates given fields of an existing movimientoProducto, field will ignore if it is null
     *
     * @param id the id of the movimientoProducto to save.
     * @param movimientoProducto the movimientoProducto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movimientoProducto,
     * or with status {@code 400 (Bad Request)} if the movimientoProducto is not valid,
     * or with status {@code 404 (Not Found)} if the movimientoProducto is not found,
     * or with status {@code 500 (Internal Server Error)} if the movimientoProducto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/movimiento-productos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MovimientoProducto> partialUpdateMovimientoProducto(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody MovimientoProducto movimientoProducto
    ) throws URISyntaxException {
        log.debug("REST request to partial update MovimientoProducto partially : {}, {}", id, movimientoProducto);
        if (movimientoProducto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, movimientoProducto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!movimientoProductoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MovimientoProducto> result = movimientoProductoRepository
            .findById(movimientoProducto.getId())
            .map(existingMovimientoProducto -> {
                if (movimientoProducto.getTipMovimiento() != null) {
                    existingMovimientoProducto.setTipMovimiento(movimientoProducto.getTipMovimiento());
                }
                if (movimientoProducto.getTip2Movimiento() != null) {
                    existingMovimientoProducto.setTip2Movimiento(movimientoProducto.getTip2Movimiento());
                }
                if (movimientoProducto.getPrecCompra() != null) {
                    existingMovimientoProducto.setPrecCompra(movimientoProducto.getPrecCompra());
                }
                if (movimientoProducto.getCnt() != null) {
                    existingMovimientoProducto.setCnt(movimientoProducto.getCnt());
                }

                if (movimientoProducto.getLote() != null) {
                    existingMovimientoProducto.setLote(movimientoProducto.getLote());
                }
                if (movimientoProducto.getFecMovimiento() != null) {
                    existingMovimientoProducto.setFecMovimiento(movimientoProducto.getFecMovimiento());
                }
                if (movimientoProducto.getVersion() != null) {
                    existingMovimientoProducto.setVersion(movimientoProducto.getVersion());
                }
                if (movimientoProducto.getIndDel() != null) {
                    existingMovimientoProducto.setIndDel(movimientoProducto.getIndDel());
                }
                if (movimientoProducto.getFecCrea() != null) {
                    existingMovimientoProducto.setFecCrea(movimientoProducto.getFecCrea());
                }
                if (movimientoProducto.getUsuCrea() != null) {
                    existingMovimientoProducto.setUsuCrea(movimientoProducto.getUsuCrea());
                }
                if (movimientoProducto.getIpCrea() != null) {
                    existingMovimientoProducto.setIpCrea(movimientoProducto.getIpCrea());
                }
                if (movimientoProducto.getFecModif() != null) {
                    existingMovimientoProducto.setFecModif(movimientoProducto.getFecModif());
                }
                if (movimientoProducto.getUsuModif() != null) {
                    existingMovimientoProducto.setUsuModif(movimientoProducto.getUsuModif());
                }
                if (movimientoProducto.getIpModif() != null) {
                    existingMovimientoProducto.setIpModif(movimientoProducto.getIpModif());
                }

                return existingMovimientoProducto;
            })
            .map(movimientoProductoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, movimientoProducto.getId())
        );
    }

    /**
     * {@code GET  /movimiento-productos} : get all the movimientoProductos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movimientoProductos in body.
     */
    @GetMapping("/movimiento-productos")
    public List<MovimientoProducto> getAllMovimientoProductos() {
        log.debug("REST request to get all MovimientoProductos");
        return movimientoProductoRepository.findAll();
    }

    /**
     * {@code GET  /movimiento-productos/:id} : get the "id" movimientoProducto.
     *
     * @param id the id of the movimientoProducto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movimientoProducto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movimiento-productos/{id}")
    public ResponseEntity<MovimientoProducto> getMovimientoProducto(@PathVariable String id) {
        log.debug("REST request to get MovimientoProducto : {}", id);
        Optional<MovimientoProducto> movimientoProducto = movimientoProductoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(movimientoProducto);
    }

    /**
     * {@code DELETE  /movimiento-productos/:id} : delete the "id" movimientoProducto.
     *
     * @param id the id of the movimientoProducto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movimiento-productos/{id}")
    public ResponseEntity<Void> deleteMovimientoProducto(@PathVariable String id) {
        log.debug("REST request to delete MovimientoProducto : {}", id);
        movimientoProductoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @PostMapping("/movimiento-productos/registrarIngreso")
    public ResponseEntity<String> registrarIngreso(@Valid @RequestBody Map<String, Object> Mapeo) throws URISyntaxException {
        log.debug("REST request to registrarIngreso : {}", Mapeo);

        StringBuffer data = new StringBuffer();

        HttpHeaders responseHeaders = new HttpHeaders();

        String result = movimientoProductoService.registrarIngreso(Mapeo);
        data.append(result);
        return ResponseEntity.ok().body(data.toString());
    }
}
