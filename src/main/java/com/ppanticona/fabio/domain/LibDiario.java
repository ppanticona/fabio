package com.ppanticona.fabio.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A LibDiario.
 */
@Document(collection = "lib_diario")
public class LibDiario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("periodo")
    private String periodo;

    @NotNull
    @Field("cuo")
    private String cuo;

    @Field("asient_contab")
    private String asientContab;

    @NotNull
    @Field("cod_cta_contable")
    private Integer codCtaContable;

    @Field("cod_unid_oper")
    private String codUnidOper;

    @Field("cod_centro_cui")
    private String codCentroCui;

    @Field("tip_moneda_ori")
    private String tipMonedaOri;

    @Field("tip_doc_iden_emi")
    private String tipDocIdenEmi;

    @Field("nro_doc_iden_emi")
    private String nroDocIdenEmi;

    @NotNull
    @Field("tip_comp_doc_asoc")
    private String tipCompDocAsoc;

    @Field("nro_ser_comp_doc_asoc")
    private String nroSerCompDocAsoc;

    @NotNull
    @Field("nro_comp_doc_asoc")
    private String nroCompDocAsoc;

    @Field("fec_contable")
    private ZonedDateTime fecContable;

    @Field("fec_venc")
    private ZonedDateTime fecVenc;

    @NotNull
    @Field("fec_ope_emi")
    private ZonedDateTime fecOpeEmi;

    @NotNull
    @Field("desc_operac")
    private String descOperac;

    @Field("glosa_ref")
    private String glosaRef;

    @NotNull
    @Field("debe")
    private Double debe;

    @NotNull
    @Field("haber")
    private Double haber;

    @Field("dato_estruct")
    private String datoEstruct;

    @NotNull
    @Field("ind_est_ope")
    private Integer indEstOpe;

    @Field("campo_libre")
    private String campoLibre;

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

    public LibDiario id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public LibDiario periodo(String periodo) {
        this.setPeriodo(periodo);
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCuo() {
        return this.cuo;
    }

    public LibDiario cuo(String cuo) {
        this.setCuo(cuo);
        return this;
    }

    public void setCuo(String cuo) {
        this.cuo = cuo;
    }

    public String getAsientContab() {
        return this.asientContab;
    }

    public LibDiario asientContab(String asientContab) {
        this.setAsientContab(asientContab);
        return this;
    }

    public void setAsientContab(String asientContab) {
        this.asientContab = asientContab;
    }

    public Integer getCodCtaContable() {
        return this.codCtaContable;
    }

    public LibDiario codCtaContable(Integer codCtaContable) {
        this.setCodCtaContable(codCtaContable);
        return this;
    }

    public void setCodCtaContable(Integer codCtaContable) {
        this.codCtaContable = codCtaContable;
    }

    public String getCodUnidOper() {
        return this.codUnidOper;
    }

    public LibDiario codUnidOper(String codUnidOper) {
        this.setCodUnidOper(codUnidOper);
        return this;
    }

    public void setCodUnidOper(String codUnidOper) {
        this.codUnidOper = codUnidOper;
    }

    public String getCodCentroCui() {
        return this.codCentroCui;
    }

    public LibDiario codCentroCui(String codCentroCui) {
        this.setCodCentroCui(codCentroCui);
        return this;
    }

    public void setCodCentroCui(String codCentroCui) {
        this.codCentroCui = codCentroCui;
    }

    public String getTipMonedaOri() {
        return this.tipMonedaOri;
    }

    public LibDiario tipMonedaOri(String tipMonedaOri) {
        this.setTipMonedaOri(tipMonedaOri);
        return this;
    }

    public void setTipMonedaOri(String tipMonedaOri) {
        this.tipMonedaOri = tipMonedaOri;
    }

    public String getTipDocIdenEmi() {
        return this.tipDocIdenEmi;
    }

    public LibDiario tipDocIdenEmi(String tipDocIdenEmi) {
        this.setTipDocIdenEmi(tipDocIdenEmi);
        return this;
    }

    public void setTipDocIdenEmi(String tipDocIdenEmi) {
        this.tipDocIdenEmi = tipDocIdenEmi;
    }

    public String getNroDocIdenEmi() {
        return this.nroDocIdenEmi;
    }

    public LibDiario nroDocIdenEmi(String nroDocIdenEmi) {
        this.setNroDocIdenEmi(nroDocIdenEmi);
        return this;
    }

    public void setNroDocIdenEmi(String nroDocIdenEmi) {
        this.nroDocIdenEmi = nroDocIdenEmi;
    }

    public String getTipCompDocAsoc() {
        return this.tipCompDocAsoc;
    }

    public LibDiario tipCompDocAsoc(String tipCompDocAsoc) {
        this.setTipCompDocAsoc(tipCompDocAsoc);
        return this;
    }

    public void setTipCompDocAsoc(String tipCompDocAsoc) {
        this.tipCompDocAsoc = tipCompDocAsoc;
    }

    public String getNroSerCompDocAsoc() {
        return this.nroSerCompDocAsoc;
    }

    public LibDiario nroSerCompDocAsoc(String nroSerCompDocAsoc) {
        this.setNroSerCompDocAsoc(nroSerCompDocAsoc);
        return this;
    }

    public void setNroSerCompDocAsoc(String nroSerCompDocAsoc) {
        this.nroSerCompDocAsoc = nroSerCompDocAsoc;
    }

    public String getNroCompDocAsoc() {
        return this.nroCompDocAsoc;
    }

    public LibDiario nroCompDocAsoc(String nroCompDocAsoc) {
        this.setNroCompDocAsoc(nroCompDocAsoc);
        return this;
    }

    public void setNroCompDocAsoc(String nroCompDocAsoc) {
        this.nroCompDocAsoc = nroCompDocAsoc;
    }

    public ZonedDateTime getFecContable() {
        return this.fecContable;
    }

    public LibDiario fecContable(ZonedDateTime fecContable) {
        this.setFecContable(fecContable);
        return this;
    }

    public void setFecContable(ZonedDateTime fecContable) {
        this.fecContable = fecContable;
    }

    public ZonedDateTime getFecVenc() {
        return this.fecVenc;
    }

    public LibDiario fecVenc(ZonedDateTime fecVenc) {
        this.setFecVenc(fecVenc);
        return this;
    }

    public void setFecVenc(ZonedDateTime fecVenc) {
        this.fecVenc = fecVenc;
    }

    public ZonedDateTime getFecOpeEmi() {
        return this.fecOpeEmi;
    }

    public LibDiario fecOpeEmi(ZonedDateTime fecOpeEmi) {
        this.setFecOpeEmi(fecOpeEmi);
        return this;
    }

    public void setFecOpeEmi(ZonedDateTime fecOpeEmi) {
        this.fecOpeEmi = fecOpeEmi;
    }

    public String getDescOperac() {
        return this.descOperac;
    }

    public LibDiario descOperac(String descOperac) {
        this.setDescOperac(descOperac);
        return this;
    }

    public void setDescOperac(String descOperac) {
        this.descOperac = descOperac;
    }

    public String getGlosaRef() {
        return this.glosaRef;
    }

    public LibDiario glosaRef(String glosaRef) {
        this.setGlosaRef(glosaRef);
        return this;
    }

    public void setGlosaRef(String glosaRef) {
        this.glosaRef = glosaRef;
    }

    public Double getDebe() {
        return this.debe;
    }

    public LibDiario debe(Double debe) {
        this.setDebe(debe);
        return this;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return this.haber;
    }

    public LibDiario haber(Double haber) {
        this.setHaber(haber);
        return this;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public String getDatoEstruct() {
        return this.datoEstruct;
    }

    public LibDiario datoEstruct(String datoEstruct) {
        this.setDatoEstruct(datoEstruct);
        return this;
    }

    public void setDatoEstruct(String datoEstruct) {
        this.datoEstruct = datoEstruct;
    }

    public Integer getIndEstOpe() {
        return this.indEstOpe;
    }

    public LibDiario indEstOpe(Integer indEstOpe) {
        this.setIndEstOpe(indEstOpe);
        return this;
    }

    public void setIndEstOpe(Integer indEstOpe) {
        this.indEstOpe = indEstOpe;
    }

    public String getCampoLibre() {
        return this.campoLibre;
    }

    public LibDiario campoLibre(String campoLibre) {
        this.setCampoLibre(campoLibre);
        return this;
    }

    public void setCampoLibre(String campoLibre) {
        this.campoLibre = campoLibre;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public LibDiario fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public LibDiario usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public LibDiario ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public LibDiario fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public LibDiario usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public LibDiario ipModif(String ipModif) {
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
        if (!(o instanceof LibDiario)) {
            return false;
        }
        return id != null && id.equals(((LibDiario) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LibDiario{" +
            "id=" + getId() +
            ", periodo='" + getPeriodo() + "'" +
            ", cuo='" + getCuo() + "'" +
            ", asientContab='" + getAsientContab() + "'" +
            ", codCtaContable=" + getCodCtaContable() +
            ", codUnidOper='" + getCodUnidOper() + "'" +
            ", codCentroCui='" + getCodCentroCui() + "'" +
            ", tipMonedaOri='" + getTipMonedaOri() + "'" +
            ", tipDocIdenEmi='" + getTipDocIdenEmi() + "'" +
            ", nroDocIdenEmi='" + getNroDocIdenEmi() + "'" +
            ", tipCompDocAsoc='" + getTipCompDocAsoc() + "'" +
            ", nroSerCompDocAsoc='" + getNroSerCompDocAsoc() + "'" +
            ", nroCompDocAsoc='" + getNroCompDocAsoc() + "'" +
            ", fecContable='" + getFecContable() + "'" +
            ", fecVenc='" + getFecVenc() + "'" +
            ", fecOpeEmi='" + getFecOpeEmi() + "'" +
            ", descOperac='" + getDescOperac() + "'" +
            ", glosaRef='" + getGlosaRef() + "'" +
            ", debe=" + getDebe() +
            ", haber=" + getHaber() +
            ", datoEstruct='" + getDatoEstruct() + "'" +
            ", indEstOpe=" + getIndEstOpe() +
            ", campoLibre='" + getCampoLibre() + "'" +
            ", fecCrea='" + getFecCrea() + "'" +
            ", usuCrea='" + getUsuCrea() + "'" +
            ", ipCrea='" + getIpCrea() + "'" +
            ", fecModif='" + getFecModif() + "'" +
            ", usuModif='" + getUsuModif() + "'" +
            ", ipModif='" + getIpModif() + "'" +
            "}";
    }
}
