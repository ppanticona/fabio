package com.ppanticona.fabio.service.impl;

import com.ppanticona.fabio.domain.*;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
import com.ppanticona.fabio.repository.RegComprasRepository;
import com.ppanticona.fabio.service.ComprobanteService;
import com.ppanticona.fabio.service.MovimientoProductoService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MovimientoProductoServiceImpl implements MovimientoProductoService {

    private final MovimientoProductoRepository movimientoProductoRepository;
    private final RegComprasRepository regComprasRepository;
    private final Logger log = LoggerFactory.getLogger(ComprobanteService.class);

    public MovimientoProductoServiceImpl(
        MovimientoProductoRepository movimientoProductoRepository,
        RegComprasRepository regComprasRepository
    ) {
        this.movimientoProductoRepository = movimientoProductoRepository;
        this.regComprasRepository = regComprasRepository;
    }

    @Override
    public String registrarIngreso(Map datosMap) {
        try {
            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            String usuCrea = String.valueOf(datosMap.get("usuCrea"));
            String tipIngreso = String.valueOf(datosMap.get("tipIngreso"));
            String lote = String.valueOf(datosMap.get("lote"));
            String tipDocIngreso = String.valueOf(datosMap.get("tipDocIngreso"));
            String numDocIngreso = String.valueOf(datosMap.get("numDocIngreso"));
            String tipDocProv = String.valueOf(datosMap.get("tipDocProv"));
            String numDocProv = String.valueOf(datosMap.get("numDocProv"));
            String NomApeRazSocProv = String.valueOf(datosMap.get("NomApeRazSocProv"));
            String proveedorID = String.valueOf(datosMap.get("proveedorID"));

            List<Map<String, Object>> detalleOrden = (List<Map<String, Object>>) datosMap.get("detalleIngreso");

            RegCompras regComprasBean = new RegCompras();

            regComprasBean.setTipDocProv(tipDocProv);
            regComprasBean.setNroDocProv(numDocProv);
            regComprasBean.setTipComp(tipDocIngreso);
            regComprasBean.setNroComp(numDocIngreso);
            regComprasBean.setFecEmiComp(fecCrea);
            regComprasBean.setNroSerieComp(numDocIngreso);
            regComprasBean.setNomApeRazSocProv(NomApeRazSocProv);
            regComprasBean.setBaseImponible(0.0);
            regComprasBean.setMontoIgv(0.0);
            regComprasBean.setBaseImponible2(0.0);
            regComprasBean.setMontoIgv2(0.0);
            regComprasBean.setBaseImponible3(0.0);
            regComprasBean.setMontoIgv3(0.0);
            Proveedor proveedor = new Proveedor();
            proveedor.setId(proveedorID);
            regComprasBean.setProveedor(proveedor);
            regComprasBean.setIndDel(false);
            regComprasBean.setFecCrea(fecCrea);
            regComprasBean.setUsuCrea(usuCrea);
            regComprasBean.setIpCrea("0.0.0.0");

            RegCompras regComprasGenerada = regComprasRepository.save(regComprasBean);

            for (Map<String, Object> bean : detalleOrden) {
                MovimientoProducto movimientoProductoBean = new MovimientoProducto();

                movimientoProductoBean.setRegCompras(regComprasGenerada);

                Producto productoBean = new Producto();
                productoBean.setId((String.valueOf(bean.get("id"))));
                movimientoProductoBean.setPrecCompra(Double.valueOf(String.valueOf(bean.get("precCompraDet"))));
                movimientoProductoBean.setProducto(productoBean);
                movimientoProductoBean.setTipMovimiento(tipIngreso);
                movimientoProductoBean.setCnt(Double.valueOf(String.valueOf(bean.get("cantidad"))));
                movimientoProductoBean.setLote(lote);

                movimientoProductoBean.setVersion(1);
                movimientoProductoBean.setIndDel(false);
                movimientoProductoBean.setFecCrea(fecCrea);
                movimientoProductoBean.setUsuCrea(usuCrea);
                movimientoProductoBean.setIpCrea("0.0.0.0");

                movimientoProductoRepository.save(movimientoProductoBean);
            }

            String jsonData = "{\"Result\":\"OK\",\"ingresoRegistrado\":\"" + regComprasGenerada.getId() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante el registro del ingreso ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }
}
