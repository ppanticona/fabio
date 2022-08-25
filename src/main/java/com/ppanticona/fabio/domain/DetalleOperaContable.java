package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DetalleOperaContable.
 */
@Document(collection = "detalle_opera_contable")
public class DetalleOperaContable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("operador")
    private String operador;

    @Field("valor_operacion")
    private Double valorOperacion;

    @Field("descripcion")
    private String descripcion;

    @Field("tip_movimiento")
    private String tipMovimiento;

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
    @Field("planContable")
    private PlanContable planContable;

    @DBRef
    @Field("operaContable")
    private OperaContable operaContable;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DetalleOperaContable id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperador() {
        return this.operador;
    }

    public DetalleOperaContable operador(String operador) {
        this.setOperador(operador);
        return this;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Double getValorOperacion() {
        return this.valorOperacion;
    }

    public DetalleOperaContable valorOperacion(Double valorOperacion) {
        this.setValorOperacion(valorOperacion);
        return this;
    }

    public void setValorOperacion(Double valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public DetalleOperaContable descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipMovimiento() {
        return this.tipMovimiento;
    }

    public DetalleOperaContable tipMovimiento(String tipMovimiento) {
        this.setTipMovimiento(tipMovimiento);
        return this;
    }

    public void setTipMovimiento(String tipMovimiento) {
        this.tipMovimiento = tipMovimiento;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public DetalleOperaContable fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public DetalleOperaContable usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public DetalleOperaContable ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public DetalleOperaContable fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public DetalleOperaContable usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public DetalleOperaContable ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public PlanContable getPlanContable() {
        return this.planContable;
    }

    public void setPlanContable(PlanContable planContable) {
        this.planContable = planContable;
    }

    public DetalleOperaContable planContable(PlanContable planContable) {
        this.setPlanContable(planContable);
        return this;
    }

    public OperaContable getOperaContable() {
        return this.operaContable;
    }

    public void setOperaContable(OperaContable operaContable) {
        this.operaContable = operaContable;
    }

    public DetalleOperaContable operaContable(OperaContable operaContable) {
        this.setOperaContable(operaContable);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetalleOperaContable)) {
            return false;
        }
        return id != null && id.equals(((DetalleOperaContable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetalleOperaContable{" +
            "id=" + getId() +
            ", operador='" + getOperador() + "'" +
            ", valorOperacion=" + getValorOperacion() +
            ", descripcion='" + getDescripcion() + "'" +
            ", tipMovimiento='" + getTipMovimiento() + "'" +
            ", fecCrea='" + getFecCrea() + "'" +
            ", usuCrea='" + getUsuCrea() + "'" +
            ", ipCrea='" + getIpCrea() + "'" +
            ", fecModif='" + getFecModif() + "'" +
            ", usuModif='" + getUsuModif() + "'" +
            ", ipModif='" + getIpModif() + "'" +
            "}";
    }
}
