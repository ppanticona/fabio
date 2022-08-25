package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A PlanContable.
 */
@Document(collection = "plan_contable")
public class PlanContable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tip_plan")
    private String tipPlan;

    @NotNull
    @Field("niv_plan")
    private Integer nivPlan;

    @NotNull
    @Field("cod_plan")
    private String codPlan;

    @NotNull
    @Field("desc_cuenta")
    private String descCuenta;

    @Field("anho_plan")
    private String anhoPlan;

    @Field("reso_plan")
    private String resoPlan;

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

    public PlanContable id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipPlan() {
        return this.tipPlan;
    }

    public PlanContable tipPlan(String tipPlan) {
        this.setTipPlan(tipPlan);
        return this;
    }

    public void setTipPlan(String tipPlan) {
        this.tipPlan = tipPlan;
    }

    public Integer getNivPlan() {
        return this.nivPlan;
    }

    public PlanContable nivPlan(Integer nivPlan) {
        this.setNivPlan(nivPlan);
        return this;
    }

    public void setNivPlan(Integer nivPlan) {
        this.nivPlan = nivPlan;
    }

    public String getCodPlan() {
        return this.codPlan;
    }

    public PlanContable codPlan(String codPlan) {
        this.setCodPlan(codPlan);
        return this;
    }

    public void setCodPlan(String codPlan) {
        this.codPlan = codPlan;
    }

    public String getDescCuenta() {
        return this.descCuenta;
    }

    public PlanContable descCuenta(String descCuenta) {
        this.setDescCuenta(descCuenta);
        return this;
    }

    public void setDescCuenta(String descCuenta) {
        this.descCuenta = descCuenta;
    }

    public String getAnhoPlan() {
        return this.anhoPlan;
    }

    public PlanContable anhoPlan(String anhoPlan) {
        this.setAnhoPlan(anhoPlan);
        return this;
    }

    public void setAnhoPlan(String anhoPlan) {
        this.anhoPlan = anhoPlan;
    }

    public String getResoPlan() {
        return this.resoPlan;
    }

    public PlanContable resoPlan(String resoPlan) {
        this.setResoPlan(resoPlan);
        return this;
    }

    public void setResoPlan(String resoPlan) {
        this.resoPlan = resoPlan;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public PlanContable fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public PlanContable usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public PlanContable ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public PlanContable fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public PlanContable usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public PlanContable ipModif(String ipModif) {
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
        if (!(o instanceof PlanContable)) {
            return false;
        }
        return id != null && id.equals(((PlanContable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlanContable{" +
            "id=" + getId() +
            ", tipPlan='" + getTipPlan() + "'" +
            ", nivPlan=" + getNivPlan() +
            ", codPlan='" + getCodPlan() + "'" +
            ", descCuenta='" + getDescCuenta() + "'" +
            ", anhoPlan='" + getAnhoPlan() + "'" +
            ", resoPlan='" + getResoPlan() + "'" +
            ", fecCrea='" + getFecCrea() + "'" +
            ", usuCrea='" + getUsuCrea() + "'" +
            ", ipCrea='" + getIpCrea() + "'" +
            ", fecModif='" + getFecModif() + "'" +
            ", usuModif='" + getUsuModif() + "'" +
            ", ipModif='" + getIpModif() + "'" +
            "}";
    }
}
