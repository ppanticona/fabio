package com.ppanticona.fabio.service.impl;

import com.ppanticona.fabio.domain.*;
import com.ppanticona.fabio.repository.*;
import com.ppanticona.fabio.service.ComprobanteService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private final SecuenciasServiceImpl secuenciasServiceImpl;
    private final OrdenRepository ordenRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;
    private final RegVentaRepository regVentaRepository;
    private final LibDiarioRepository libDiarioRepository;
    private final DetalleOperaContableRepository detalleOperaContableRepository;
    private final OperaContableRepository operaContableRepository;
    private final HistoricoCajaRepository historicoCajaRepository;
    private final MovimientoCajaRepository movimientoCajaRepository;
    private final Logger log = LoggerFactory.getLogger(ComprobanteService.class);

    public ComprobanteServiceImpl(
        SecuenciasServiceImpl secuenciasServiceImpl,
        OrdenRepository ordenRepository,
        DetalleOrdenRepository detalleOrdenRepository,
        RegVentaRepository regVentaRepository,
        LibDiarioRepository libDiarioRepository,
        DetalleOperaContableRepository detalleOperaContableRepository,
        OperaContableRepository operaContableRepository,
        HistoricoCajaRepository historicoCajaRepository,
        MovimientoCajaRepository movimientoCajaRepository
    ) {
        this.secuenciasServiceImpl = secuenciasServiceImpl;
        this.ordenRepository = ordenRepository;
        this.detalleOrdenRepository = detalleOrdenRepository;
        this.regVentaRepository = regVentaRepository;
        this.libDiarioRepository = libDiarioRepository;
        this.detalleOperaContableRepository = detalleOperaContableRepository;
        this.operaContableRepository = operaContableRepository;
        this.historicoCajaRepository = historicoCajaRepository;
        this.movimientoCajaRepository = movimientoCajaRepository;
    }

    @Override
    public String generarComprobante(Map datosMap) {
        try {
            //registro de orden
            //registro de detalleorden(varios)
            //registro de venta
            //registro de libro diario
            //registro en movimiento caja

            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            String periodo = Integer.toString(fecCrea.getYear()) + Integer.toString(fecCrea.getMonthValue()) + "00";
            String usuCrea = String.valueOf(datosMap.get("usuCrea"));
            Double tipCambio = Double.valueOf(String.valueOf(datosMap.get("tipCambio")));
            Double montoRecibido = Double.valueOf(String.valueOf(datosMap.get("montoRecibido")));

            Cliente clienteBean = new Cliente();
            Map<String, Object> clienteMap = (Map<String, Object>) datosMap.get("cliente");

            List<Map<String, Object>> detalleOrden = (List<Map<String, Object>>) datosMap.get("detalleOrden");

            //  ############ REGISTRO DE ORDEN ######################33
            Orden ordenBean = new Orden();

            clienteBean.setId(String.valueOf(clienteMap.get("id")));

            ordenBean.setCliente(clienteBean);

            if (Boolean.valueOf(String.valueOf(datosMap.get("aPedido")))) {
                ordenBean.setTipOrden("02");
                ordenBean.setEstado("02");
                ordenBean.setFecEstiEnt(ZonedDateTime.parse(String.valueOf(datosMap.get("fecEstiEnt"))));
            } else {
                ordenBean.setTipOrden("01");
                ordenBean.setEstado("01");
            }

            ordenBean.setVersion(1);
            ordenBean.setIndDel(false);
            ordenBean.setFecCrea(fecCrea);
            ordenBean.setUsuCrea(usuCrea);
            ordenBean.setIpCrea("0.0.0.0");

            // obtenemos la secuencia para la orden
            Integer numOrden = secuenciasServiceImpl.getNextByNameSeq("seqOrden");

            ordenBean.setNumOrden(numOrden);

            Orden ordenGenerada = ordenRepository.save(ordenBean);

            //  ############ REGISTRO DE DETALLE DE ORDEN ######################33
            String numSerieComp = "";
            Integer numCorrelComp = 0;

            if (String.valueOf(datosMap.get("tipComp")).equals("03")) {
                numSerieComp = "B" + 1;

                numCorrelComp = secuenciasServiceImpl.getNextByNameSeq("seqCorrelBoleta");
            } else if (String.valueOf(datosMap.get("tipComp")).equals("01")) {
                numSerieComp = "F" + 1;

                numCorrelComp = secuenciasServiceImpl.getNextByNameSeq("seqCorrelFactura");
            }

            for (Map<String, Object> bean : detalleOrden) {
                DetalleOrden detalleOrdenBean = new DetalleOrden();

                detalleOrdenBean.setOrden(ordenGenerada);

                detalleOrdenBean.setCantidad(Double.valueOf(String.valueOf(bean.get("cantidad"))));
                detalleOrdenBean.setValUni(Double.valueOf(String.valueOf(bean.get("valUni"))));
                //por desarrollar
                detalleOrdenBean.setDcto(0.0);
                detalleOrdenBean.setSubtotal(Double.valueOf(String.valueOf(bean.get("subtotal"))));
                detalleOrdenBean.setObservacion((String.valueOf(bean.get("observacion"))));

                detalleOrdenBean.setEstado("01");
                detalleOrdenBean.setVersion(1);
                detalleOrdenBean.setIndDel(false);
                detalleOrdenBean.setFecCrea(fecCrea);
                detalleOrdenBean.setUsuCrea(usuCrea);
                detalleOrdenBean.setIpCrea("0.0.0.0");

                detalleOrdenRepository.save(detalleOrdenBean);
            }

            //  ############ REGISTRO DE REG VENTAS ######################33
            RegVenta regVentaBean = new RegVenta();

            Integer seqCuo = secuenciasServiceImpl.getNextByNameSeq("seqCuo");

            Double igv =
                Double.valueOf(String.valueOf(datosMap.get("valorTotalOrden"))) -
                (Double.valueOf(String.valueOf(datosMap.get("valorTotalOrden"))) / 1.18);
            Double importeTotalComp = Double.valueOf(String.valueOf(datosMap.get("valorTotalOrden")));
            Double valorTotalOrden = Double.valueOf(String.valueOf(datosMap.get("valorTotalOrden")));

            regVentaBean.setPeriodo(periodo);
            regVentaBean.setCuo("14-" + seqCuo);
            regVentaBean.setAsientContab("M-" + seqCuo);
            regVentaBean.setFecEmiComp(fecCrea);
            regVentaBean.setFecVencComp(fecCrea);
            regVentaBean.setTipComp(String.valueOf(datosMap.get("tipComp")));
            regVentaBean.setNroSerieComp(numSerieComp);
            regVentaBean.setNroComp(Integer.toString(numCorrelComp));
            regVentaBean.setTipDocCli(String.valueOf(clienteMap.get("tipDocCli")));
            regVentaBean.setNroDocCli(String.valueOf(clienteMap.get("nroDocCli")));
            regVentaBean.setApeRazSocCli(String.valueOf(clienteMap.get("nombresCli")) + String.valueOf(clienteMap.get("apellidosCli")));
            regVentaBean.setBaseImpOperGrav(valorTotalOrden / 1.18);
            regVentaBean.setDsctoBaseImp(0.0);
            regVentaBean.setIgvIpm(igv);
            regVentaBean.setDsctoIgvIpm(0.0);
            regVentaBean.setImpOpeExo(0.0);
            regVentaBean.setImpTotOpeInafec(0.0);
            regVentaBean.setImporteTotalComp(importeTotalComp);
            regVentaBean.setCodMoneda("1");
            regVentaBean.setTipCambio(0.0);
            regVentaBean.setEstadoOperaCancMp("1");
            regVentaBean.setFormPago(String.valueOf(datosMap.get("formPago")));
            regVentaBean.setMoneda(String.valueOf(datosMap.get("moneda")));
            regVentaBean.setIndDel(false);
            regVentaBean.setFecCrea(fecCrea);
            regVentaBean.setUsuCrea(usuCrea);
            regVentaBean.setIpCrea("0.0.0.0");

            regVentaRepository.save(regVentaBean);

            //  ############ REGISTRO DE LIBRO DIARIO######################33

            OperaContable resOperaContable = operaContableRepository.findByDescOpera("RegistroVentaAlContado");
            List<DetalleOperaContable> detalleOperacionesContables = detalleOperaContableRepository.findByOperaContableId(
                resOperaContable.getId()
            );

            for (int i = 0; i < detalleOperacionesContables.size(); i++) {
                LibDiario libDiarioBean = new LibDiario();

                libDiarioBean.setPeriodo(periodo);
                libDiarioBean.setCuo("5-" + seqCuo);
                libDiarioBean.setAsientContab("5-" + seqCuo);

                libDiarioBean.setTipCompDocAsoc("");
                libDiarioBean.setNroCompDocAsoc("");
                libDiarioBean.setFecContable(fecCrea);
                libDiarioBean.setFecOpeEmi(fecCrea);
                libDiarioBean.setDescOperac("VENTA AL CONTADO");
                libDiarioBean.setIndEstOpe(1);
                libDiarioBean.setFecCrea(fecCrea);
                libDiarioBean.setUsuCrea(usuCrea);
                libDiarioBean.setIpCrea("0.0.0.0");

                if (detalleOperacionesContables.get(i).getDescripcion().equals("TOTAL")) {
                    libDiarioBean.setCodCtaContable(Integer.parseInt(detalleOperacionesContables.get(i).getPlanContable().getCodPlan()));
                    if (detalleOperacionesContables.get(i).getTipMovimiento().equals("D")) {
                        libDiarioBean.setDebe(valorTotalOrden);
                        libDiarioBean.setHaber(0.0);
                    } else {
                        libDiarioBean.setDebe(0.0);
                        libDiarioBean.setHaber(valorTotalOrden);
                    }
                } else if (detalleOperacionesContables.get(i).getDescripcion().equals("BASE")) {
                    libDiarioBean.setCodCtaContable(Integer.parseInt(detalleOperacionesContables.get(i).getPlanContable().getCodPlan()));
                    if (detalleOperacionesContables.get(i).getTipMovimiento().equals("D")) {
                        libDiarioBean.setDebe(valorTotalOrden / 1.18);
                        libDiarioBean.setHaber(0.0);
                    } else {
                        libDiarioBean.setDebe(0.0);
                        libDiarioBean.setHaber(valorTotalOrden / 1.18);
                    }
                } else if (detalleOperacionesContables.get(i).getDescripcion().equals("IGV")) {
                    libDiarioBean.setCodCtaContable(Integer.parseInt(detalleOperacionesContables.get(i).getPlanContable().getCodPlan()));
                    if (detalleOperacionesContables.get(i).getTipMovimiento().equals("D")) {
                        libDiarioBean.setDebe(valorTotalOrden - valorTotalOrden / 1.18);
                        libDiarioBean.setHaber(0.0);
                    } else {
                        libDiarioBean.setDebe(0.0);
                        libDiarioBean.setHaber(valorTotalOrden - valorTotalOrden / 1.18);
                    }
                }

                libDiarioRepository.save(libDiarioBean);
            }

            //  ############ REGISTRO DE MOVIMIENTO DE CAJA ######################33

            // Buscamos el historico caja relacionado

            HistoricoCaja historicoCaja = historicoCajaRepository.findByUsuarioAsignadoAndAndEstado(usuCrea, "01");

            MovimientoCaja movimientoCajaBean = new MovimientoCaja();

            movimientoCajaBean.setTipMovimiento("I");

            if (String.valueOf(datosMap.get("moneda")).equals("soles")) {
                movimientoCajaBean.setMonto(valorTotalOrden);
            } else if (String.valueOf(datosMap.get("moneda")).equals("dolares")) {
                movimientoCajaBean.setMonto(montoRecibido);
            }

            movimientoCajaBean.setTipMoneda(String.valueOf(datosMap.get("moneda")));
            movimientoCajaBean.setFecMovimiento(fecCrea);
            movimientoCajaBean.setVersion(1);
            movimientoCajaBean.setIndDel(false);
            movimientoCajaBean.setFecCrea(fecCrea);
            movimientoCajaBean.setUsuCrea(usuCrea);
            movimientoCajaBean.setIpCrea("0.0.0.0");
            movimientoCajaBean.setHistoricoCaja(historicoCaja);

            movimientoCajaRepository.save(movimientoCajaBean);

            if (String.valueOf(datosMap.get("moneda")).equals("dolares")) {
                if (montoRecibido > (valorTotalOrden / tipCambio)) {
                    Double vueltoEnSoles = (montoRecibido - (valorTotalOrden / tipCambio)) * tipCambio;

                    movimientoCajaBean.setTipMovimiento("E");
                    movimientoCajaBean.setMonto(vueltoEnSoles);
                    movimientoCajaBean.setTipMoneda("soles");

                    movimientoCajaRepository.save(movimientoCajaBean);
                }
            }

            String jsonData = "{\"Result\":\"OK\",\"codOrdenRegistrada\":\"" + ordenGenerada.getNumOrden() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante la generación del comprobante ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }
}
