package com.ppanticona.fabio.service.impl;

import com.ppanticona.fabio.component.ProductoInventarioConverter;
import com.ppanticona.fabio.domain.Producto;
import com.ppanticona.fabio.model.ProductoInventarioModel;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
import com.ppanticona.fabio.repository.ProductoRepository;
import com.ppanticona.fabio.repository.SecuenciasRepository;
import com.ppanticona.fabio.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final Logger log = LoggerFactory.getLogger(ProductoService.class);

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
    public String obtenerProductosInventario() {
        try {
            List<Producto> listProductos = productoRepository.findAll();
            List<ProductoInventarioModel> listProductoInventarioModel = new ArrayList<ProductoInventarioModel>();

            for (Producto p : listProductos) {
                ProductoInventarioModel productoInventarioModel = productoInventarioConverter.convertEntity2Model(p); // convertimos el domain al modelo que tiene ingreso y salida
                //   Double ingreso = movimientoProductoRepository.getSumCantidadPorCodProductoAndTipMovimiento(p.getCodProducto(), "I"); // obtenemos el valor total de cantidad por ingreso
                //  productoInventarioModel.setMontoIngreso(ingreso); //asignamos valor obtenido
                //  Double salida = movimientoProductoRepository.getSumCantidadPorCodProductoAndTipMovimiento(p.getCodProducto(), "S"); // obtenemos el valor total de cantidad por ingreso
                //  productoInventarioModel.setMontoSalida(salida);
                listProductoInventarioModel.add(productoInventarioModel);
            }

            String jsonData = "{\"Result\":\"OK\",\"ServicioDetalle\":" + listProductoInventarioModel.toString() + "}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante el get de inventario de productos ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return null;
        }
    }
}
