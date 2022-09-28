package com.ppanticona.fabio.service.impl;

import com.ppanticona.fabio.domain.*;
import com.ppanticona.fabio.repository.MovimientoProductoRepository;
import com.ppanticona.fabio.repository.OrdenRepository;
import com.ppanticona.fabio.repository.RegComprasRepository;
import com.ppanticona.fabio.repository.RegVentaRepository;
import com.ppanticona.fabio.service.ComprobanteService;
import com.ppanticona.fabio.service.MovimientoProductoService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MovimientoProductoServiceImpl implements MovimientoProductoService {

    private final MovimientoProductoRepository movimientoProductoRepository;
    private final OrdenRepository ordenRepository;
    private final RegComprasRepository regComprasRepository;
    private final RegVentaRepository regVentaRepository;
    private final SecuenciasServiceImpl secuenciasServiceImpl;
    private final Logger log = LoggerFactory.getLogger(ComprobanteService.class);

    public MovimientoProductoServiceImpl(
        MovimientoProductoRepository movimientoProductoRepository,
        OrdenRepository ordenRepository,
        RegComprasRepository regComprasRepository,
        SecuenciasServiceImpl secuenciasServiceImpl,
        RegVentaRepository regVentaRepository
    ) {
        this.movimientoProductoRepository = movimientoProductoRepository;
        this.regComprasRepository = regComprasRepository;
        this.ordenRepository = ordenRepository;
        this.secuenciasServiceImpl = secuenciasServiceImpl;
        this.regVentaRepository = regVentaRepository;
    }

    @Override
    public String registrarIngreso(Map datosMap) {
        try {
            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            String periodo = Integer.toString(fecCrea.getYear()) + Integer.toString(fecCrea.getMonthValue()) + "00";
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

            Proveedor proveedor = new Proveedor();
            proveedor.setId(proveedorID);
            Integer numOrden = secuenciasServiceImpl.getNextByNameSeq("seqOrden");

            Orden ordenBean = new Orden();

            ordenBean.setProveedor(proveedor);
            ordenBean.setNumOrden(numOrden);
            ordenBean.setTipOrden("05"); // "05 - ORDEN DE INGRESO"
            ordenBean.setEstado("01");
            ordenBean.setIndDel(false);
            ordenBean.setVersion(1);
            ordenBean.setFecCrea(fecCrea);
            ordenBean.setUsuCrea(usuCrea);
            ordenBean.setIpCrea("0.0.0.0");

            Orden ordenGenerada = ordenRepository.save(ordenBean);

            // se registra la compra
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
            regComprasBean.setCuo("");
            regComprasBean.setPeriodo(periodo);
            regComprasBean.setOrden(ordenGenerada);
            regComprasBean.setIndDel(false);
            regComprasBean.setFecCrea(fecCrea);
            regComprasBean.setUsuCrea(usuCrea);
            regComprasBean.setIpCrea("0.0.0.0");

            RegCompras regComprasGenerada = regComprasRepository.save(regComprasBean);
            //se registra la orden de ingreso

            for (Map<String, Object> bean : detalleOrden) {
                MovimientoProducto movimientoProductoBean = new MovimientoProducto();

                movimientoProductoBean.setRegCompras(regComprasGenerada);

                Producto productoBean = new Producto();
                productoBean.setId((String.valueOf(bean.get("id"))));
                movimientoProductoBean.setPrecCompra(Double.valueOf(String.valueOf(bean.get("precCompraDet"))));
                movimientoProductoBean.setPreVenta(Double.valueOf(String.valueOf(bean.get("precVentaDet"))));
                movimientoProductoBean.setProducto(productoBean);
                movimientoProductoBean.setTipMovimiento("I");
                movimientoProductoBean.setTip2Movimiento(tipIngreso);
                movimientoProductoBean.setCnt(Double.valueOf(String.valueOf(bean.get("cantidad"))));
                movimientoProductoBean.setLote(lote);
                movimientoProductoBean.setOrden(ordenGenerada);
                movimientoProductoBean.setRegCompras(regComprasGenerada);
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

    @Override
    public String registrarSalida(Map datosMap) {
        try {
            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            String periodo = Integer.toString(fecCrea.getYear()) + Integer.toString(fecCrea.getMonthValue()) + "00";
            String usuCrea = String.valueOf(datosMap.get("usuCrea"));
            String tipSalida = String.valueOf(datosMap.get("tipSalida"));
            String tipDocSalida = String.valueOf(datosMap.get("tipDocSalida"));
            String numDocSalida = String.valueOf(datosMap.get("numDocSalida"));
            String tipDocCli = String.valueOf(datosMap.get("tipDocCli"));
            String numDocCli = String.valueOf(datosMap.get("numDocCli"));
            String nombresCli = String.valueOf(datosMap.get("nombresCli"));
            String clienteId = String.valueOf(datosMap.get("clienteId"));

            List<Map<String, Object>> detalleOrden = (List<Map<String, Object>>) datosMap.get("detalleIngreso");

            //registro de orden de salida   06
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            Integer numOrden = secuenciasServiceImpl.getNextByNameSeq("seqOrden");
            Orden ordenBean = new Orden();
            ordenBean.setCliente(cliente);
            ordenBean.setNumOrden(numOrden);
            ordenBean.setTipOrden("06"); // "06 - ORDEN DE SALIDA"
            ordenBean.setEstado("01");
            ordenBean.setIndDel(false);
            ordenBean.setVersion(1);
            ordenBean.setFecCrea(fecCrea);
            ordenBean.setUsuCrea(usuCrea);
            ordenBean.setIpCrea("0.0.0.0");

            Orden ordenGenerada = ordenRepository.save(ordenBean);
            //////fin registro orden salida

            RegVenta regVentaBean = new RegVenta();

            regVentaBean.setTipDocCli(tipDocCli);
            regVentaBean.setNroDocCli(numDocCli);
            regVentaBean.setTipComp(tipDocSalida);
            regVentaBean.setNroComp(numDocSalida);
            regVentaBean.setOrden(ordenGenerada);
            regVentaBean.setFecEmiComp(fecCrea);
            regVentaBean.setNroSerieComp(numDocSalida);
            regVentaBean.setCuo("");
            regVentaBean.setPeriodo(periodo);
            regVentaBean.setDsctoBaseImp(0.0);
            regVentaBean.setTipCambio(0.0);
            regVentaBean.setBaseImpOperGrav(0.0);
            regVentaBean.setApeRazSocCli(nombresCli);
            regVentaBean.setIgvIpm(0.0);
            regVentaBean.setImpTotOpeInafec(0.0);
            regVentaBean.setImporteTotalComp(0.0); // importe total
            regVentaBean.setDsctoIgvIpm(0.0);
            regVentaBean.setCodMoneda("1");
            regVentaBean.setImpOpeExo(0.0);
            regVentaBean.setIndDel(false);
            regVentaBean.setFecCrea(fecCrea);
            regVentaBean.setUsuCrea(usuCrea);
            regVentaBean.setIpCrea("0.0.0.0");

            RegVenta regVentaGenerada = regVentaRepository.save(regVentaBean);

            for (Map<String, Object> bean : detalleOrden) {
                MovimientoProducto movimientoProductoBean = new MovimientoProducto();

                movimientoProductoBean.setRegVenta(regVentaGenerada);

                Producto productoBean = new Producto();
                productoBean.setId((String.valueOf(bean.get("id"))));
                movimientoProductoBean.setPrecCompra(Double.valueOf(String.valueOf(bean.get("precCompraDet"))));
                movimientoProductoBean.setPreVenta(Double.valueOf(String.valueOf(bean.get("precVentaDet"))));
                movimientoProductoBean.setProducto(productoBean);
                movimientoProductoBean.setTipMovimiento("S");
                movimientoProductoBean.setTip2Movimiento(tipSalida);
                movimientoProductoBean.setCnt(Double.valueOf(String.valueOf(bean.get("cantidad"))));
                movimientoProductoBean.setOrden(ordenGenerada);
                movimientoProductoBean.setRegVenta(regVentaGenerada);
                movimientoProductoBean.setVersion(1);
                movimientoProductoBean.setIndDel(false);
                movimientoProductoBean.setFecCrea(fecCrea);
                movimientoProductoBean.setUsuCrea(usuCrea);
                movimientoProductoBean.setIpCrea("0.0.0.0");

                movimientoProductoRepository.save(movimientoProductoBean);
            }

            String jsonData = "{\"Result\":\"OK\",\"salidaRegistrada\":\"" + regVentaGenerada.getId() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante el registro de la salida ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }
}
