package com.ppanticona.fabio.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ppanticona.fabio.component.ProductoInventarioConverter;
import com.ppanticona.fabio.domain.Producto;
import com.ppanticona.fabio.model.ProductoInventarioModel;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
import com.ppanticona.fabio.repository.ProductoRepository;
import com.ppanticona.fabio.repository.SecuenciasRepository;
import com.ppanticona.fabio.service.ProductoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final Logger log = LoggerFactory.getLogger(ProductoService.class);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final SecuenciasRepository secuenciasRepository;
    private final MovimientoProductoRepository movimientoProductoRepository;
    private final ProductoRepository productoRepository;
    private final SecuenciasServiceImpl secuenciasServiceImpl;
    private final ProductoInventarioConverter productoInventarioConverter;

    public ProductoServiceImpl(
        SecuenciasRepository secuenciasRepository,
        ProductoRepository productoRepository,
        SecuenciasServiceImpl secuenciasServiceImpl,
        ProductoInventarioConverter productoInventarioConverter,
        MovimientoProductoRepository movimientoProductoRepository
    ) {
        this.secuenciasRepository = secuenciasRepository;
        this.productoRepository = productoRepository;
        this.secuenciasServiceImpl = secuenciasServiceImpl;
        this.productoInventarioConverter = productoInventarioConverter;
        this.movimientoProductoRepository = movimientoProductoRepository;
    }

    @Override
    public Producto registrarProducto(Producto productoBean) {
        try {
            Integer numProducto = secuenciasServiceImpl.getNextByNameSeq("seqProductos");

            productoBean.setCodProducto(String.valueOf(numProducto));

            Producto result = productoRepository.save(productoBean);

            String jsonData = "{\"Result\":\"OK\",\"codProducto\":\"" + result.getCodProducto() + "\"}";

            return result;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante el registro del producto ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String obtenerProductosInventario(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Producto> pageProductos = productoRepository.findAllByIndDelAndEstado(false, "01", paging);

            List<Producto> productos = new ArrayList<Producto>();
            productos = pageProductos.getContent();
            List<ProductoInventarioModel> listProductoInventarioModel = new ArrayList<ProductoInventarioModel>();

            for (Producto p : productos) {
                ProductoInventarioModel productoInventarioModel = productoInventarioConverter.convertEntity2Model(p); // convertimos el domain al modelo que tiene ingreso y salida
                String ingreso = movimientoProductoRepository.sumCantByProductoAndTipMovimiento(p.getId(), "I"); // obtenemos el valor total de cantidad por ingreso

                if (ingreso != null) {
                    productoInventarioModel.setMontoIngreso(Double.valueOf(String.valueOf(ingreso))); //asignamos valor obtenido
                } else {
                    productoInventarioModel.setMontoIngreso(0.0);
                }

                String salida = movimientoProductoRepository.sumCantByProductoAndTipMovimiento(p.getId(), "S"); // obtenemos el valor total de cantidad por ingreso

                if (salida != null) {
                    productoInventarioModel.setMontoSalida(Double.valueOf(String.valueOf(salida))); //asignamos valor obtenido
                } else {
                    productoInventarioModel.setMontoSalida(0.0);
                }

                listProductoInventarioModel.add(productoInventarioModel);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("productos", listProductoInventarioModel.toString());
            response.put("currentPage", pageProductos.getNumber());
            response.put("totalItems", pageProductos.getTotalElements());
            response.put("totalPages", pageProductos.getTotalPages());

            String listProductoInventarioModelString = gson.toJson(listProductoInventarioModel);

            this.log.debug("to string " + listProductoInventarioModel.toString());
            this.log.debug("sin el yo  string " + listProductoInventarioModel);
            String jsonData =
                "{\"Result\":\"OK\",\"productos\":" +
                listProductoInventarioModelString +
                ",\"currentPage\":" +
                pageProductos.getNumber() +
                ",\"totalItems\":" +
                pageProductos.getTotalElements() +
                ",\"totalPages\":" +
                pageProductos.getTotalPages() +
                "}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante el get de inventario de productos ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return null;
        }
    }
}
