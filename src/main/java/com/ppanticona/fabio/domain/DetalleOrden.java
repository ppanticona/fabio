package com.ppanticona.fabio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DetalleOrden.
 */
@Document(collection = "detalle_orden")
public class DetalleOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("cantidad")
    private Double cantidad;

    @NotNull
    @Field("val_uni")
    private Double valUni;

    @NotNull
    @Field("dcto")
    private Double dcto;

    @NotNull
    @Field("subtotal")
    private Double subtotal;

    @Field("observacion")
    private String observacion;

    @NotNull
    @Field("estado")
    private String estado;

    @NotNull
    @Field("version")
    private Integer version;

    @NotNull
    @Field("ind_del")
    private Boolean indDel;

    @NotNull
    @Field("fec_crea")
    private ZonedDateTime fecCrea;

    @NotNull
    @Field("usu_crea")
    private String usuCrea;

    @NotNull
    @Field("ip_crea")
    private String ipCrea;

    @Field("fec_modif")
    private ZonedDateTime fecModif;

    @Field("usu_modif")
    private String usuModif;

    @Field("ip_modif")
    private String ipModif;

    @DBRef
    @Field("orden")
    @JsonIgnoreProperties(value = { "cliente", "proveedor", "autorizacion" }, allowSetters = true)
    private Orden orden;

    @DBRef
    @Field("producto")
    private Producto producto;

    @DBRef
    @Field("promocion")
    private Promocion promocion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DetalleOrden id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCantidad() {
        return this.cantidad;
    }

    public DetalleOrden cantidad(Double cantidad) {
        this.setCantidad(cantidad);
        return this;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValUni() {
        return this.valUni;
    }

    public DetalleOrden valUni(Double valUni) {
        this.setValUni(valUni);
        return this;
    }

    public void setValUni(Double valUni) {
        this.valUni = valUni;
    }

    public Double getDcto() {
        return this.dcto;
    }

    public DetalleOrden dcto(Double dcto) {
        this.setDcto(dcto);
        return this;
    }

    public void setDcto(Double dcto) {
        this.dcto = dcto;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public DetalleOrden subtotal(Double subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public DetalleOrden observacion(String observacion) {
        this.setObservacion(observacion);
        return this;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return this.estado;
    }

    public DetalleOrden estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public DetalleOrden version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public DetalleOrden indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public DetalleOrden fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public DetalleOrden usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public DetalleOrden ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public DetalleOrden fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public DetalleOrden usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public DetalleOrden ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Orden getOrden() {
        return this.orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public DetalleOrden orden(Orden orden) {
        this.setOrden(orden);
        return this;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public DetalleOrden producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    public Promocion getPromocion() {
        return this.promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public DetalleOrden promocion(Promocion promocion) {
        this.setPromocion(promocion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetalleOrden)) {
            return false;
        }
        return id != null && id.equals(((DetalleOrden) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetalleOrden{" +
            "id=" + getId() +
            ", cantidad=" + getCantidad() +
            ", valUni=" + getValUni() +
            ", dcto=" + getDcto() +
            ", subtotal=" + getSubtotal() +
            ", observacion='" + getObservacion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", version=" + getVersion() +
            ", indDel='" + getIndDel() + "'" +
            ", fecCrea='" + getFecCrea() + "'" +
            ", usuCrea='" + getUsuCrea() + "'" +
            ", ipCrea='" + getIpCrea() + "'" +
            ", fecModif='" + getFecModif() + "'" +
            ", usuModif='" + getUsuModif() + "'" +
            ", ipModif='" + getIpModif() + "'" +
            "}";
    }
}
