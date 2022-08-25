package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.LibDiario;
import com.ppanticona.fabio.repository.LibDiarioRepository;
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
 * REST controller for managing {@link com.ppanticona.fabio.domain.LibDiario}.
 */
@RestController
@RequestMapping("/api")
public class LibDiarioResource {

    private final Logger log = LoggerFactory.getLogger(LibDiarioResource.class);

    private static final String ENTITY_NAME = "libDiario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LibDiarioRepository libDiarioRepository;

    public LibDiarioResource(LibDiarioRepository libDiarioRepository) {
        this.libDiarioRepository = libDiarioRepository;
    }

    /**
     * {@code POST  /lib-diarios} : Create a new libDiario.
     *
     * @param libDiario the libDiario to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new libDiario, or with status {@code 400 (Bad Request)} if the libDiario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lib-diarios")
    public ResponseEntity<LibDiario> createLibDiario(@Valid @RequestBody LibDiario libDiario) throws URISyntaxException {
        log.debug("REST request to save LibDiario : {}", libDiario);
        if (libDiario.getId() != null) {
            throw new BadRequestAlertException("A new libDiario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LibDiario result = libDiarioRepository.save(libDiario);
        return ResponseEntity
            .created(new URI("/api/lib-diarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /lib-diarios/:id} : Updates an existing libDiario.
     *
     * @param id the id of the libDiario to save.
     * @param libDiario the libDiario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated libDiario,
     * or with status {@code 400 (Bad Request)} if the libDiario is not valid,
     * or with status {@code 500 (Internal Server Error)} if the libDiario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lib-diarios/{id}")
    public ResponseEntity<LibDiario> updateLibDiario(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody LibDiario libDiario
    ) throws URISyntaxException {
        log.debug("REST request to update LibDiario : {}, {}", id, libDiario);
        if (libDiario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, libDiario.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!libDiarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LibDiario result = libDiarioRepository.save(libDiario);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, libDiario.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /lib-diarios/:id} : Partial updates given fields of an existing libDiario, field will ignore if it is null
     *
     * @param id the id of the libDiario to save.
     * @param libDiario the libDiario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated libDiario,
     * or with status {@code 400 (Bad Request)} if the libDiario is not valid,
     * or with status {@code 404 (Not Found)} if the libDiario is not found,
     * or with status {@code 500 (Internal Server Error)} if the libDiario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lib-diarios/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LibDiario> partialUpdateLibDiario(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody LibDiario libDiario
    ) throws URISyntaxException {
        log.debug("REST request to partial update LibDiario partially : {}, {}", id, libDiario);
        if (libDiario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, libDiario.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!libDiarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LibDiario> result = libDiarioRepository
            .findById(libDiario.getId())
            .map(existingLibDiario -> {
                if (libDiario.getPeriodo() != null) {
                    existingLibDiario.setPeriodo(libDiario.getPeriodo());
                }
                if (libDiario.getCuo() != null) {
                    existingLibDiario.setCuo(libDiario.getCuo());
                }
                if (libDiario.getAsientContab() != null) {
                    existingLibDiario.setAsientContab(libDiario.getAsientContab());
                }
                if (libDiario.getCodCtaContable() != null) {
                    existingLibDiario.setCodCtaContable(libDiario.getCodCtaContable());
                }
                if (libDiario.getCodUnidOper() != null) {
                    existingLibDiario.setCodUnidOper(libDiario.getCodUnidOper());
                }
                if (libDiario.getCodCentroCui() != null) {
                    existingLibDiario.setCodCentroCui(libDiario.getCodCentroCui());
                }
                if (libDiario.getTipMonedaOri() != null) {
                    existingLibDiario.setTipMonedaOri(libDiario.getTipMonedaOri());
                }
                if (libDiario.getTipDocIdenEmi() != null) {
                    existingLibDiario.setTipDocIdenEmi(libDiario.getTipDocIdenEmi());
                }
                if (libDiario.getNroDocIdenEmi() != null) {
                    existingLibDiario.setNroDocIdenEmi(libDiario.getNroDocIdenEmi());
                }
                if (libDiario.getTipCompDocAsoc() != null) {
                    existingLibDiario.setTipCompDocAsoc(libDiario.getTipCompDocAsoc());
                }
                if (libDiario.getNroSerCompDocAsoc() != null) {
                    existingLibDiario.setNroSerCompDocAsoc(libDiario.getNroSerCompDocAsoc());
                }
                if (libDiario.getNroCompDocAsoc() != null) {
                    existingLibDiario.setNroCompDocAsoc(libDiario.getNroCompDocAsoc());
                }
                if (libDiario.getFecContable() != null) {
                    existingLibDiario.setFecContable(libDiario.getFecContable());
                }
                if (libDiario.getFecVenc() != null) {
                    existingLibDiario.setFecVenc(libDiario.getFecVenc());
                }
                if (libDiario.getFecOpeEmi() != null) {
                    existingLibDiario.setFecOpeEmi(libDiario.getFecOpeEmi());
                }
                if (libDiario.getDescOperac() != null) {
                    existingLibDiario.setDescOperac(libDiario.getDescOperac());
                }
                if (libDiario.getGlosaRef() != null) {
                    existingLibDiario.setGlosaRef(libDiario.getGlosaRef());
                }
                if (libDiario.getDebe() != null) {
                    existingLibDiario.setDebe(libDiario.getDebe());
                }
                if (libDiario.getHaber() != null) {
                    existingLibDiario.setHaber(libDiario.getHaber());
                }
                if (libDiario.getDatoEstruct() != null) {
                    existingLibDiario.setDatoEstruct(libDiario.getDatoEstruct());
                }
                if (libDiario.getIndEstOpe() != null) {
                    existingLibDiario.setIndEstOpe(libDiario.getIndEstOpe());
                }
                if (libDiario.getCampoLibre() != null) {
                    existingLibDiario.setCampoLibre(libDiario.getCampoLibre());
                }
                if (libDiario.getFecCrea() != null) {
                    existingLibDiario.setFecCrea(libDiario.getFecCrea());
                }
                if (libDiario.getUsuCrea() != null) {
                    existingLibDiario.setUsuCrea(libDiario.getUsuCrea());
                }
                if (libDiario.getIpCrea() != null) {
                    existingLibDiario.setIpCrea(libDiario.getIpCrea());
                }
                if (libDiario.getFecModif() != null) {
                    existingLibDiario.setFecModif(libDiario.getFecModif());
                }
                if (libDiario.getUsuModif() != null) {
                    existingLibDiario.setUsuModif(libDiario.getUsuModif());
                }
                if (libDiario.getIpModif() != null) {
                    existingLibDiario.setIpModif(libDiario.getIpModif());
                }

                return existingLibDiario;
            })
            .map(libDiarioRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, libDiario.getId())
        );
    }

    /**
     * {@code GET  /lib-diarios} : get all the libDiarios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of libDiarios in body.
     */
    @GetMapping("/lib-diarios")
    public List<LibDiario> getAllLibDiarios() {
        log.debug("REST request to get all LibDiarios");
        return libDiarioRepository.findAll();
    }

    /**
     * {@code GET  /lib-diarios/:id} : get the "id" libDiario.
     *
     * @param id the id of the libDiario to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the libDiario, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lib-diarios/{id}")
    public ResponseEntity<LibDiario> getLibDiario(@PathVariable String id) {
        log.debug("REST request to get LibDiario : {}", id);
        Optional<LibDiario> libDiario = libDiarioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(libDiario);
    }

    /**
     * {@code DELETE  /lib-diarios/:id} : delete the "id" libDiario.
     *
     * @param id the id of the libDiario to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lib-diarios/{id}")
    public ResponseEntity<Void> deleteLibDiario(@PathVariable String id) {
        log.debug("REST request to delete LibDiario : {}", id);
        libDiarioRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
