package com.ppanticona.fabio.model;

import java.time.ZonedDateTime;

public class ProductoInventarioModel {

    private String id;

    private String codProducto;

    private String codQr;

    private String codBarra;

    private String descripcion;

    private String detalleDesc;

    private Double valor;

    private Double montoIngreso; //este model es para agregar lo ingresado de este producto por inventario

    private Double montoSalida; //este model es para agregar lo saliente de este producto por inventario

    private ZonedDateTime fecIniVig;

    private ZonedDateTime fecFinVig;

    private Double costoProd;

    private String urlImage;

    private String estado;

    private Integer version;

    private Boolean indDel;

    private ZonedDateTime fecCrea;

    private String usuCrea;

    private String ipCrea;

    private ZonedDateTime fecModif;

    private String usuModif;

    private String ipModif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getCodQr() {
        return codQr;
    }

    public void setCodQr(String codQr) {
        this.codQr = codQr;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalleDesc() {
        return detalleDesc;
    }

    public void setDetalleDesc(String detalleDesc) {
        this.detalleDesc = detalleDesc;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(Double montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public Double getMontoSalida() {
        return montoSalida;
    }

    public void setMontoSalida(Double montoSalida) {
        this.montoSalida = montoSalida;
    }

    public ZonedDateTime getFecIniVig() {
        return fecIniVig;
    }

    public void setFecIniVig(ZonedDateTime fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public ZonedDateTime getFecFinVig() {
        return fecFinVig;
    }

    public void setFecFinVig(ZonedDateTime fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public Double getCostoProd() {
        return costoProd;
    }

    public void setCostoProd(Double costoProd) {
        this.costoProd = costoProd;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return indDel;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return fecCrea;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return usuCrea;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return ipCrea;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return fecModif;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return usuModif;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return ipModif;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public ProductoInventarioModel(
        String id,
        String codProducto,
        String codQr,
        String codBarra,
        String descripcion,
        String detalleDesc,
        Double valor,
        Double montoIngreso,
        Double montoSalida,
        ZonedDateTime fecIniVig,
        ZonedDateTime fecFinVig,
        Double costoProd,
        String urlImage,
        String estado,
        Integer version,
        Boolean indDel,
        ZonedDateTime fecCrea,
        String usuCrea,
        String ipCrea,
        ZonedDateTime fecModif,
        String usuModif,
        String ipModif
    ) {
        super();
        this.id = id;
        this.codProducto = codProducto;
        this.codQr = codQr;
        this.codBarra = codBarra;
        this.descripcion = descripcion;
        this.detalleDesc = detalleDesc;
        this.valor = valor;
        this.montoIngreso = montoIngreso;
        this.montoSalida = montoSalida;
        this.fecIniVig = fecIniVig;
        this.fecFinVig = fecFinVig;
        this.costoProd = costoProd;
        this.urlImage = urlImage;
        this.estado = estado;
        this.version = version;
        this.indDel = indDel;
        this.fecCrea = fecCrea;
        this.usuCrea = usuCrea;
        this.ipCrea = ipCrea;
        this.fecModif = fecModif;
        this.usuModif = usuModif;
        this.ipModif = ipModif;
    }

    public ProductoInventarioModel() {}
}
