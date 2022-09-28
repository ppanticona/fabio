package com.ppanticona.fabio.web.rest;

import com.ppanticona.fabio.domain.Producto;
import com.ppanticona.fabio.repository.ProductoRepository;
import com.ppanticona.fabio.service.ProductoService;
import com.ppanticona.fabio.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ppanticona.fabio.domain.Producto}.
 */
@RestController
@RequestMapping("/api")
public class ProductoResource {

    private final Logger log = LoggerFactory.getLogger(ProductoResource.class);

    private static final String ENTITY_NAME = "producto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductoRepository productoRepository;

    private final ProductoService productoService;

    public ProductoResource(ProductoRepository productoRepository, ProductoService productoService) {
        this.productoRepository = productoRepository;

        this.productoService = productoService;
    }

    /**
     * {@code POST  /productos} : Create a new producto.
     *
     * @param producto the producto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new producto, or with status {@code 400 (Bad Request)} if the producto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productos")
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) throws URISyntaxException {
        log.debug("REST request to save Producto : {}", producto);
        if (producto.getId() != null) {
            throw new BadRequestAlertException("A new producto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Producto result = productoRepository.save(producto);
        return ResponseEntity
            .created(new URI("/api/productos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /productos/:id} : Updates an existing producto.
     *
     * @param id the id of the producto to save.
     * @param producto the producto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated producto,
     * or with status {@code 400 (Bad Request)} if the producto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the producto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Producto producto
    ) throws URISyntaxException {
        log.debug("REST request to update Producto : {}, {}", id, producto);
        if (producto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, producto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Producto result = productoRepository.save(producto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, producto.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /productos/:id} : Partial updates given fields of an existing producto, field will ignore if it is null
     *
     * @param id the id of the producto to save.
     * @param producto the producto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated producto,
     * or with status {@code 400 (Bad Request)} if the producto is not valid,
     * or with status {@code 404 (Not Found)} if the producto is not found,
     * or with status {@code 500 (Internal Server Error)} if the producto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/productos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Producto> partialUpdateProducto(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Producto producto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Producto partially : {}, {}", id, producto);
        if (producto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, producto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Producto> result = productoRepository
            .findById(producto.getId())
            .map(existingProducto -> {
                if (producto.getCodProducto() != null) {
                    existingProducto.setCodProducto(producto.getCodProducto());
                }
                if (producto.getCodQr() != null) {
                    existingProducto.setCodQr(producto.getCodQr());
                }
                if (producto.getCodBarra() != null) {
                    existingProducto.setCodBarra(producto.getCodBarra());
                }
                if (producto.getDescripcion() != null) {
                    existingProducto.setDescripcion(producto.getDescripcion());
                }
                if (producto.getDetalleDesc() != null) {
                    existingProducto.setDetalleDesc(producto.getDetalleDesc());
                }
                if (producto.getValor() != null) {
                    existingProducto.setValor(producto.getValor());
                }
                if (producto.getFecIniVig() != null) {
                    existingProducto.setFecIniVig(producto.getFecIniVig());
                }
                if (producto.getFecFinVig() != null) {
                    existingProducto.setFecFinVig(producto.getFecFinVig());
                }
                if (producto.getCostoProd() != null) {
                    existingProducto.setCostoProd(producto.getCostoProd());
                }
                if (producto.getUrlImage() != null) {
                    existingProducto.setUrlImage(producto.getUrlImage());
                }
                if (producto.getEstado() != null) {
                    existingProducto.setEstado(producto.getEstado());
                }
                if (producto.getVersion() != null) {
                    existingProducto.setVersion(producto.getVersion());
                }
                if (producto.getIndDel() != null) {
                    existingProducto.setIndDel(producto.getIndDel());
                }
                if (producto.getFecCrea() != null) {
                    existingProducto.setFecCrea(producto.getFecCrea());
                }
                if (producto.getUsuCrea() != null) {
                    existingProducto.setUsuCrea(producto.getUsuCrea());
                }
                if (producto.getIpCrea() != null) {
                    existingProducto.setIpCrea(producto.getIpCrea());
                }
                if (producto.getFecModif() != null) {
                    existingProducto.setFecModif(producto.getFecModif());
                }
                if (producto.getUsuModif() != null) {
                    existingProducto.setUsuModif(producto.getUsuModif());
                }
                if (producto.getIpModif() != null) {
                    existingProducto.setIpModif(producto.getIpModif());
                }

                return existingProducto;
            })
            .map(productoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, producto.getId())
        );
    }

    /**
     * {@code GET  /productos} : get all the productos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productos in body.
     */
    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        log.debug("REST request to get all Productos");
        return productoRepository.findAll();
    }

    @GetMapping("/productosActivos")
    public ResponseEntity<Map<String, Object>> getAllProductosActivos(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        log.debug("REST request to get all Productos con inddel false y estado 01 ");

        try {
            List<Producto> productos = new ArrayList<Producto>();
            Pageable paging = PageRequest.of(page, size);

            Page<Producto> pageProductos = productoRepository.findAllByIndDelAndEstado(false, "01", paging);
            productos = pageProductos.getContent();

            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("productos", productos);
            response.put("currentPage", pageProductos.getNumber());
            response.put("totalItems", pageProductos.getTotalElements());
            response.put("totalPages", pageProductos.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos/inventarioProductos")
    public ResponseEntity<String> getAllInventarioProductos(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        log.debug("REST request to get inventario all Productos con inddel false y estado 01 ");

        try {
            String resultado = productoService.obtenerProductosInventario(page, size);

            StringBuffer data = new StringBuffer();
            data.append(resultado);

            return new ResponseEntity(data.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productosPorDescripcion/{cadena}")
    public ResponseEntity<Map<String, Object>> getAllProductosPorDescripcion(
        @PathVariable(value = "cadena", required = true) final String cadena,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        log.debug("REST request to get all Productos por regex descripcion con inddel false y estado 01 ");

        try {
            List<Producto> productos = new ArrayList<Producto>();
            Pageable paging = PageRequest.of(page, size);

            Page<Producto> pageProductos = productoRepository.findAllByDescripcionRegexAndIndDelIsFalseAndEstado(cadena, "01", paging);
            productos = pageProductos.getContent();

            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("productos", productos);
            response.put("currentPage", pageProductos.getNumber());
            response.put("totalItems", pageProductos.getTotalElements());
            response.put("totalPages", pageProductos.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productosPorCodigo/{cadena}")
    public ResponseEntity<Map<String, Object>> getAllProductosPorCodigoProducto(
        @PathVariable(value = "cadena", required = true) final String cadena,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        log.debug("REST request to get all Productos por codigo con inddel false y estado 01 ");

        try {
            List<Producto> productos = new ArrayList<Producto>();
            Pageable paging = PageRequest.of(page, size);

            Page<Producto> pageProductos = productoRepository.findAllByCodProductoAndEstadoAndIndDelIsFalse(cadena, "01", paging);
            productos = pageProductos.getContent();

            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("productos", productos);
            response.put("currentPage", pageProductos.getNumber());
            response.put("totalItems", pageProductos.getTotalElements());
            response.put("totalPages", pageProductos.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * {@code GET  /productos/:id} : get the "id" producto.
     *
     * @param id the id of the producto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the producto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable String id) {
        log.debug("REST request to get Producto : {}", id);
        Optional<Producto> producto = productoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(producto);
    }

    /**
     * {@code DELETE  /productos/:id} : delete the "id" producto.
     *
     * @param id the id of the producto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
        log.debug("REST request to delete Producto : {}", id);
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @PostMapping("/productos/registrarProducto")
    public ResponseEntity<Producto> registrarProducto(@Valid @RequestBody Producto producto) throws URISyntaxException {
        log.debug("REST request to save Producto : {}", producto);
        if (producto.getId() != null) {
            throw new BadRequestAlertException("A new producto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Producto result = productoService.registrarProducto(producto);
        return ResponseEntity
            .created(new URI("/api/productos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }
}
