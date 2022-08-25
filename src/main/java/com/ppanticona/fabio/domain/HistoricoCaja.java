package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A HistoricoCaja.
 */
@Document(collection = "historico_caja")
public class HistoricoCaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("fec_ini_vig")
    private ZonedDateTime fecIniVig;

    @NotNull
    @Field("fec_fin_vig")
    private ZonedDateTime fecFinVig;

    @NotNull
    @Field("estado")
    private String estado;

    @NotNull
    @Field("monto_inicial_soles")
    private Double montoInicialSoles;

    @NotNull
    @Field("monto_maximo_soles")
    private Double montoMaximoSoles;

    @Field("monto_real_soles")
    private Double montoRealSoles;

    @NotNull
    @Field("monto_inicial_dolares")
    private Double montoInicialDolares;

    @NotNull
    @Field("monto_maximo_dolares")
    private Double montoMaximoDolares;

    @Field("monto_real_dolares")
    private Double montoRealDolares;

    @Field("monto_real_otros")
    private Double montoRealOtros;

    @Field("usuario_asignado")
    private String usuarioAsignado;

    @Field("comentario")
    private String comentario;

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
    @Field("caja")
    private Caja caja;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public HistoricoCaja id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getFecIniVig() {
        return this.fecIniVig;
    }

    public HistoricoCaja fecIniVig(ZonedDateTime fecIniVig) {
        this.setFecIniVig(fecIniVig);
        return this;
    }

    public void setFecIniVig(ZonedDateTime fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    public ZonedDateTime getFecFinVig() {
        return this.fecFinVig;
    }

    public HistoricoCaja fecFinVig(ZonedDateTime fecFinVig) {
        this.setFecFinVig(fecFinVig);
        return this;
    }

    public void setFecFinVig(ZonedDateTime fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    public String getEstado() {
        return this.estado;
    }

    public HistoricoCaja estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMontoInicialSoles() {
        return this.montoInicialSoles;
    }

    public HistoricoCaja montoInicialSoles(Double montoInicialSoles) {
        this.setMontoInicialSoles(montoInicialSoles);
        return this;
    }

    public void setMontoInicialSoles(Double montoInicialSoles) {
        this.montoInicialSoles = montoInicialSoles;
    }

    public Double getMontoMaximoSoles() {
        return this.montoMaximoSoles;
    }

    public HistoricoCaja montoMaximoSoles(Double montoMaximoSoles) {
        this.setMontoMaximoSoles(montoMaximoSoles);
        return this;
    }

    public void setMontoMaximoSoles(Double montoMaximoSoles) {
        this.montoMaximoSoles = montoMaximoSoles;
    }

    public Double getMontoRealSoles() {
        return this.montoRealSoles;
    }

    public HistoricoCaja montoRealSoles(Double montoRealSoles) {
        this.setMontoRealSoles(montoRealSoles);
        return this;
    }

    public void setMontoRealSoles(Double montoRealSoles) {
        this.montoRealSoles = montoRealSoles;
    }

    public Double getMontoInicialDolares() {
        return this.montoInicialDolares;
    }

    public HistoricoCaja montoInicialDolares(Double montoInicialDolares) {
        this.setMontoInicialDolares(montoInicialDolares);
        return this;
    }

    public void setMontoInicialDolares(Double montoInicialDolares) {
        this.montoInicialDolares = montoInicialDolares;
    }

    public Double getMontoMaximoDolares() {
        return this.montoMaximoDolares;
    }

    public HistoricoCaja montoMaximoDolares(Double montoMaximoDolares) {
        this.setMontoMaximoDolares(montoMaximoDolares);
        return this;
    }

    public void setMontoMaximoDolares(Double montoMaximoDolares) {
        this.montoMaximoDolares = montoMaximoDolares;
    }

    public Double getMontoRealDolares() {
        return this.montoRealDolares;
    }

    public HistoricoCaja montoRealDolares(Double montoRealDolares) {
        this.setMontoRealDolares(montoRealDolares);
        return this;
    }

    public void setMontoRealDolares(Double montoRealDolares) {
        this.montoRealDolares = montoRealDolares;
    }

    public Double getMontoRealOtros() {
        return this.montoRealOtros;
    }

    public HistoricoCaja montoRealOtros(Double montoRealOtros) {
        this.setMontoRealOtros(montoRealOtros);
        return this;
    }

    public void setMontoRealOtros(Double montoRealOtros) {
        this.montoRealOtros = montoRealOtros;
    }

    public String getUsuarioAsignado() {
        return this.usuarioAsignado;
    }

    public HistoricoCaja usuarioAsignado(String usuarioAsignado) {
        this.setUsuarioAsignado(usuarioAsignado);
        return this;
    }

    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public String getComentario() {
        return this.comentario;
    }

    public HistoricoCaja comentario(String comentario) {
        this.setComentario(comentario);
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getVersion() {
        return this.version;
    }

    public HistoricoCaja version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public HistoricoCaja indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public HistoricoCaja fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public HistoricoCaja usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public HistoricoCaja ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public HistoricoCaja fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public HistoricoCaja usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public HistoricoCaja ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Caja getCaja() {
        return this.caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public HistoricoCaja caja(Caja caja) {
        this.setCaja(caja);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoricoCaja)) {
            return false;
        }
        return id != null && id.equals(((HistoricoCaja) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoricoCaja{" +
            "id=" + getId() +
            ", fecIniVig='" + getFecIniVig() + "'" +
            ", fecFinVig='" + getFecFinVig() + "'" +
            ", estado='" + getEstado() + "'" +
            ", montoInicialSoles=" + getMontoInicialSoles() +
            ", montoMaximoSoles=" + getMontoMaximoSoles() +
            ", montoRealSoles=" + getMontoRealSoles() +
            ", montoInicialDolares=" + getMontoInicialDolares() +
            ", montoMaximoDolares=" + getMontoMaximoDolares() +
            ", montoRealDolares=" + getMontoRealDolares() +
            ", montoRealOtros=" + getMontoRealOtros() +
            ", usuarioAsignado='" + getUsuarioAsignado() + "'" +
            ", comentario='" + getComentario() + "'" +
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
