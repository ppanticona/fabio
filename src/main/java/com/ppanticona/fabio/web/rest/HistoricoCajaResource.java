package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.HistoricoCaja;
import com.ppanticona.fabio.repository.HistoricoCajaRepository;
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
 * REST controller for managing {@link com.ppanticona.fabio.domain.HistoricoCaja}.
 */
@RestController
@RequestMapping("/api")
public class HistoricoCajaResource {

    private final Logger log = LoggerFactory.getLogger(HistoricoCajaResource.class);

    private static final String ENTITY_NAME = "historicoCaja";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoricoCajaRepository historicoCajaRepository;

    public HistoricoCajaResource(HistoricoCajaRepository historicoCajaRepository) {
        this.historicoCajaRepository = historicoCajaRepository;
    }

    /**
     * {@code POST  /historico-cajas} : Create a new historicoCaja.
     *
     * @param historicoCaja the historicoCaja to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historicoCaja, or with status {@code 400 (Bad Request)} if the historicoCaja has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historico-cajas")
    public ResponseEntity<HistoricoCaja> createHistoricoCaja(@Valid @RequestBody HistoricoCaja historicoCaja) throws URISyntaxException {
        log.debug("REST request to save HistoricoCaja : {}", historicoCaja);
        if (historicoCaja.getId() != null) {
            throw new BadRequestAlertException("A new historicoCaja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoricoCaja result = historicoCajaRepository.save(historicoCaja);
        return ResponseEntity
            .created(new URI("/api/historico-cajas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /historico-cajas/:id} : Updates an existing historicoCaja.
     *
     * @param id the id of the historicoCaja to save.
     * @param historicoCaja the historicoCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicoCaja,
     * or with status {@code 400 (Bad Request)} if the historicoCaja is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historicoCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historico-cajas/{id}")
    public ResponseEntity<HistoricoCaja> updateHistoricoCaja(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody HistoricoCaja historicoCaja
    ) throws URISyntaxException {
        log.debug("REST request to update HistoricoCaja : {}, {}", id, historicoCaja);
        if (historicoCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historicoCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historicoCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HistoricoCaja result = historicoCajaRepository.save(historicoCaja);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historicoCaja.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /historico-cajas/:id} : Partial updates given fields of an existing historicoCaja, field will ignore if it is null
     *
     * @param id the id of the historicoCaja to save.
     * @param historicoCaja the historicoCaja to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicoCaja,
     * or with status {@code 400 (Bad Request)} if the historicoCaja is not valid,
     * or with status {@code 404 (Not Found)} if the historicoCaja is not found,
     * or with status {@code 500 (Internal Server Error)} if the historicoCaja couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/historico-cajas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HistoricoCaja> partialUpdateHistoricoCaja(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody HistoricoCaja historicoCaja
    ) throws URISyntaxException {
        log.debug("REST request to partial update HistoricoCaja partially : {}, {}", id, historicoCaja);
        if (historicoCaja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, historicoCaja.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!historicoCajaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HistoricoCaja> result = historicoCajaRepository
            .findById(historicoCaja.getId())
            .map(existingHistoricoCaja -> {
                if (historicoCaja.getFecIniVig() != null) {
                    existingHistoricoCaja.setFecIniVig(historicoCaja.getFecIniVig());
                }
                if (historicoCaja.getFecFinVig() != null) {
                    existingHistoricoCaja.setFecFinVig(historicoCaja.getFecFinVig());
                }
                if (historicoCaja.getEstado() != null) {
                    existingHistoricoCaja.setEstado(historicoCaja.getEstado());
                }
                if (historicoCaja.getMontoInicialSoles() != null) {
                    existingHistoricoCaja.setMontoInicialSoles(historicoCaja.getMontoInicialSoles());
                }
                if (historicoCaja.getMontoMaximoSoles() != null) {
                    existingHistoricoCaja.setMontoMaximoSoles(historicoCaja.getMontoMaximoSoles());
                }
                if (historicoCaja.getMontoRealSoles() != null) {
                    existingHistoricoCaja.setMontoRealSoles(historicoCaja.getMontoRealSoles());
                }
                if (historicoCaja.getMontoInicialDolares() != null) {
                    existingHistoricoCaja.setMontoInicialDolares(historicoCaja.getMontoInicialDolares());
                }
                if (historicoCaja.getMontoMaximoDolares() != null) {
                    existingHistoricoCaja.setMontoMaximoDolares(historicoCaja.getMontoMaximoDolares());
                }
                if (historicoCaja.getMontoRealDolares() != null) {
                    existingHistoricoCaja.setMontoRealDolares(historicoCaja.getMontoRealDolares());
                }
                if (historicoCaja.getMontoRealOtros() != null) {
                    existingHistoricoCaja.setMontoRealOtros(historicoCaja.getMontoRealOtros());
                }
                if (historicoCaja.getUsuarioAsignado() != null) {
                    existingHistoricoCaja.setUsuarioAsignado(historicoCaja.getUsuarioAsignado());
                }
                if (historicoCaja.getComentario() != null) {
                    existingHistoricoCaja.setComentario(historicoCaja.getComentario());
                }
                if (historicoCaja.getVersion() != null) {
                    existingHistoricoCaja.setVersion(historicoCaja.getVersion());
                }
                if (historicoCaja.getIndDel() != null) {
                    existingHistoricoCaja.setIndDel(historicoCaja.getIndDel());
                }
                if (historicoCaja.getFecCrea() != null) {
                    existingHistoricoCaja.setFecCrea(historicoCaja.getFecCrea());
                }
                if (historicoCaja.getUsuCrea() != null) {
                    existingHistoricoCaja.setUsuCrea(historicoCaja.getUsuCrea());
                }
                if (historicoCaja.getIpCrea() != null) {
                    existingHistoricoCaja.setIpCrea(historicoCaja.getIpCrea());
                }
                if (historicoCaja.getFecModif() != null) {
                    existingHistoricoCaja.setFecModif(historicoCaja.getFecModif());
                }
                if (historicoCaja.getUsuModif() != null) {
                    existingHistoricoCaja.setUsuModif(historicoCaja.getUsuModif());
                }
                if (historicoCaja.getIpModif() != null) {
                    existingHistoricoCaja.setIpModif(historicoCaja.getIpModif());
                }

                return existingHistoricoCaja;
            })
            .map(historicoCajaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historicoCaja.getId())
        );
    }

    /**
     * {@code GET  /historico-cajas} : get all the historicoCajas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historicoCajas in body.
     */
    @GetMapping("/historico-cajas")
    public List<HistoricoCaja> getAllHistoricoCajas() {
        log.debug("REST request to get all HistoricoCajas");
        return historicoCajaRepository.findAll();
    }

    /**
     * {@code GET  /historico-cajas/:id} : get the "id" historicoCaja.
     *
     * @param id the id of the historicoCaja to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historicoCaja, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historico-cajas/{id}")
    public ResponseEntity<HistoricoCaja> getHistoricoCaja(@PathVariable String id) {
        log.debug("REST request to get HistoricoCaja : {}", id);
        Optional<HistoricoCaja> historicoCaja = historicoCajaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(historicoCaja);
    }

    /**
     * {@code DELETE  /historico-cajas/:id} : delete the "id" historicoCaja.
     *
     * @param id the id of the historicoCaja to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historico-cajas/{id}")
    public ResponseEntity<Void> deleteHistoricoCaja(@PathVariable String id) {
        log.debug("REST request to delete HistoricoCaja : {}", id);
        historicoCajaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
