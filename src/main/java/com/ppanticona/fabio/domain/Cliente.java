package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Cliente.
 */
@Document(collection = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tip_doc_cli")
    private String tipDocCli;

    @NotNull
    @Field("nro_doc_cli")
    private String nroDocCli;

    @NotNull
    @Field("nombres_cli")
    private String nombresCli;

    @NotNull
    @Field("apellidos_cli")
    private String apellidosCli;

    @NotNull
    @Field("tel_1")
    private String tel1;

    @Field("tel_2")
    private String tel2;

    @Field("correo")
    private String correo;

    @Field("direccion")
    private String direccion;

    @Field("ref_direccion")
    private String refDireccion;

    @Field("distrito")
    private String distrito;

    @NotNull
    @Field("fec_nac")
    private ZonedDateTime fecNac;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Cliente id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipDocCli() {
        return this.tipDocCli;
    }

    public Cliente tipDocCli(String tipDocCli) {
        this.setTipDocCli(tipDocCli);
        return this;
    }

    public void setTipDocCli(String tipDocCli) {
        this.tipDocCli = tipDocCli;
    }

    public String getNroDocCli() {
        return this.nroDocCli;
    }

    public Cliente nroDocCli(String nroDocCli) {
        this.setNroDocCli(nroDocCli);
        return this;
    }

    public void setNroDocCli(String nroDocCli) {
        this.nroDocCli = nroDocCli;
    }

    public String getNombresCli() {
        return this.nombresCli;
    }

    public Cliente nombresCli(String nombresCli) {
        this.setNombresCli(nombresCli);
        return this;
    }

    public void setNombresCli(String nombresCli) {
        this.nombresCli = nombresCli;
    }

    public String getApellidosCli() {
        return this.apellidosCli;
    }

    public Cliente apellidosCli(String apellidosCli) {
        this.setApellidosCli(apellidosCli);
        return this;
    }

    public void setApellidosCli(String apellidosCli) {
        this.apellidosCli = apellidosCli;
    }

    public String getTel1() {
        return this.tel1;
    }

    public Cliente tel1(String tel1) {
        this.setTel1(tel1);
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public Cliente tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCorreo() {
        return this.correo;
    }

    public Cliente correo(String correo) {
        this.setCorreo(correo);
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Cliente direccion(String direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRefDireccion() {
        return this.refDireccion;
    }

    public Cliente refDireccion(String refDireccion) {
        this.setRefDireccion(refDireccion);
        return this;
    }

    public void setRefDireccion(String refDireccion) {
        this.refDireccion = refDireccion;
    }

    public String getDistrito() {
        return this.distrito;
    }

    public Cliente distrito(String distrito) {
        this.setDistrito(distrito);
        return this;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public ZonedDateTime getFecNac() {
        return this.fecNac;
    }

    public Cliente fecNac(ZonedDateTime fecNac) {
        this.setFecNac(fecNac);
        return this;
    }

    public void setFecNac(ZonedDateTime fecNac) {
        this.fecNac = fecNac;
    }

    public String getEstado() {
        return this.estado;
    }

    public Cliente estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Cliente version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public Cliente indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public Cliente fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public Cliente usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public Cliente ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public Cliente fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public Cliente usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public Cliente ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        return id != null && id.equals(((Cliente) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", tipDocCli='" + getTipDocCli() + "'" +
            ", nroDocCli='" + getNroDocCli() + "'" +
            ", nombresCli='" + getNombresCli() + "'" +
            ", apellidosCli='" + getApellidosCli() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", refDireccion='" + getRefDireccion() + "'" +
            ", distrito='" + getDistrito() + "'" +
            ", fecNac='" + getFecNac() + "'" +
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
