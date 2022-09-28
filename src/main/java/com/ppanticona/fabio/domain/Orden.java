package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Orden.
 */
@Document(collection = "orden")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("num_orden")
    private Integer numOrden;

    @Field("fec_esti_ent")
    private ZonedDateTime fecEstiEnt;

    @Field("fec_recog")
    private ZonedDateTime fecRecog;

    @Field("observacion")
    private String observacion;

    @NotNull
    @Field("tip_orden")
    private String tipOrden;

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
    @Field("cliente")
    private Cliente cliente;

    @DBRef
    @Field("proveedor")
    private Proveedor proveedor;

    @DBRef
    @Field("autorizacion")
    private Autorizacion autorizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Orden id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumOrden() {
        return this.numOrden;
    }

    public Orden numOrden(Integer numOrden) {
        this.setNumOrden(numOrden);
        return this;
    }

    public void setNumOrden(Integer numOrden) {
        this.numOrden = numOrden;
    }

    public ZonedDateTime getFecEstiEnt() {
        return this.fecEstiEnt;
    }

    public Orden fecEstiEnt(ZonedDateTime fecEstiEnt) {
        this.setFecEstiEnt(fecEstiEnt);
        return this;
    }

    public void setFecEstiEnt(ZonedDateTime fecEstiEnt) {
        this.fecEstiEnt = fecEstiEnt;
    }

    public ZonedDateTime getFecRecog() {
        return this.fecRecog;
    }

    public Orden fecRecog(ZonedDateTime fecRecog) {
        this.setFecRecog(fecRecog);
        return this;
    }

    public void setFecRecog(ZonedDateTime fecRecog) {
        this.fecRecog = fecRecog;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public Orden observacion(String observacion) {
        this.setObservacion(observacion);
        return this;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipOrden() {
        return this.tipOrden;
    }

    public Orden tipOrden(String tipOrden) {
        this.setTipOrden(tipOrden);
        return this;
    }

    public void setTipOrden(String tipOrden) {
        this.tipOrden = tipOrden;
    }

    public String getEstado() {
        return this.estado;
    }

    public Orden estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Orden version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Orden indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Orden fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Orden usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Orden ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Orden fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Orden usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Orden ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Orden cliente(Cliente cliente) {
        this.setCliente(cliente);
        return this;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Orden proveedor(Proveedor proveedor) {
        this.setProveedor(proveedor);
        return this;
    }

    public Autorizacion getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Orden autorizacion(Autorizacion autorizacion) {
        this.setAutorizacion(autorizacion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orden)) {
            return false;
        }
        return id != null && id.equals(((Orden) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Orden{" +
            "id=" + getId() +
            ", numOrden=" + getNumOrden() +
            ", fecEstiEnt='" + getFecEstiEnt() + "'" +
            ", fecRecog='" + getFecRecog() + "'" +
            ", observacion='" + getObservacion() + "'" +
            ", tipOrden='" + getTipOrden() + "'" +
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
