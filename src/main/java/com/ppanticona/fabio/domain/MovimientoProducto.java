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
 * A MovimientoProducto.
 */
@Document(collection = "movimiento_producto")
public class MovimientoProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tip_movimiento")
    private String tipMovimiento;

    @Field("tip_2_movimiento")
    private String tip2Movimiento;

    @Field("cnt")
    private Double cnt;

    @Field("lote")
    private String lote;

    @Field("fec_movimiento")
    private ZonedDateTime fecMovimiento;

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
    @Field("producto")
    private Producto producto;

    @DBRef
    @Field("regVenta")
    @JsonIgnoreProperties(value = { "orden" }, allowSetters = true)
    private RegVenta regVenta;

    @DBRef
    @Field("regCompras")
    @JsonIgnoreProperties(value = { "proveedor" }, allowSetters = true)
    private RegCompras regCompras;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public MovimientoProducto id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipMovimiento() {
        return this.tipMovimiento;
    }

    public MovimientoProducto tipMovimiento(String tipMovimiento) {
        this.setTipMovimiento(tipMovimiento);
        return this;
    }

    public void setTipMovimiento(String tipMovimiento) {
        this.tipMovimiento = tipMovimiento;
    }

    public String getTip2Movimiento() {
        return this.tip2Movimiento;
    }

    public MovimientoProducto tip2Movimiento(String tip2Movimiento) {
        this.setTip2Movimiento(tip2Movimiento);
        return this;
    }

    public void setTip2Movimiento(String tip2Movimiento) {
        this.tip2Movimiento = tip2Movimiento;
    }

    public Double getCnt() {
        return this.cnt;
    }

    public MovimientoProducto cnt(Double cnt) {
        this.setCnt(cnt);
        return this;
    }

    public void setCnt(Double cnt) {
        this.cnt = cnt;
    }

    public String getLote() {
        return this.lote;
    }

    public MovimientoProducto lote(String lote) {
        this.setLote(lote);
        return this;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public ZonedDateTime getFecMovimiento() {
        return this.fecMovimiento;
    }

    public MovimientoProducto fecMovimiento(ZonedDateTime fecMovimiento) {
        this.setFecMovimiento(fecMovimiento);
        return this;
    }

    public void setFecMovimiento(ZonedDateTime fecMovimiento) {
        this.fecMovimiento = fecMovimiento;
    }

    public Integer getVersion() {
        return this.version;
    }

    public MovimientoProducto version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIndDel() {
        return this.indDel;
    }

    public MovimientoProducto indDel(Boolean indDel) {
        this.setIndDel(indDel);
        return this;
    }

    public void setIndDel(Boolean indDel) {
        this.indDel = indDel;
    }

    public ZonedDateTime getFecCrea() {
        return this.fecCrea;
    }

    public MovimientoProducto fecCrea(ZonedDateTime fecCrea) {
        this.setFecCrea(fecCrea);
        return this;
    }

    public void setFecCrea(ZonedDateTime fecCrea) {
        this.fecCrea = fecCrea;
    }

    public String getUsuCrea() {
        return this.usuCrea;
    }

    public MovimientoProducto usuCrea(String usuCrea) {
        this.setUsuCrea(usuCrea);
        return this;
    }

    public void setUsuCrea(String usuCrea) {
        this.usuCrea = usuCrea;
    }

    public String getIpCrea() {
        return this.ipCrea;
    }

    public MovimientoProducto ipCrea(String ipCrea) {
        this.setIpCrea(ipCrea);
        return this;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public ZonedDateTime getFecModif() {
        return this.fecModif;
    }

    public MovimientoProducto fecModif(ZonedDateTime fecModif) {
        this.setFecModif(fecModif);
        return this;
    }

    public void setFecModif(ZonedDateTime fecModif) {
        this.fecModif = fecModif;
    }

    public String getUsuModif() {
        return this.usuModif;
    }

    public MovimientoProducto usuModif(String usuModif) {
        this.setUsuModif(usuModif);
        return this;
    }

    public void setUsuModif(String usuModif) {
        this.usuModif = usuModif;
    }

    public String getIpModif() {
        return this.ipModif;
    }

    public MovimientoProducto ipModif(String ipModif) {
        this.setIpModif(ipModif);
        return this;
    }

    public void setIpModif(String ipModif) {
        this.ipModif = ipModif;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public MovimientoProducto producto(Producto producto) {
        this.setProducto(producto);
        return this;
    }

    public RegVenta getRegVenta() {
        return this.regVenta;
    }

    public void setRegVenta(RegVenta regVenta) {
        this.regVenta = regVenta;
    }

    public MovimientoProducto regVenta(RegVenta regVenta) {
        this.setRegVenta(regVenta);
        return this;
    }

    public RegCompras getRegCompras() {
        return this.regCompras;
    }

    public void setRegCompras(RegCompras regCompras) {
        this.regCompras = regCompras;
    }

    public MovimientoProducto regCompras(RegCompras regCompras) {
        this.setRegCompras(regCompras);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovimientoProducto)) {
            return false;
        }
        return id != null && id.equals(((MovimientoProducto) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovimientoProducto{" +
            "id=" + getId() +
            ", tipMovimiento='" + getTipMovimiento() + "'" +
            ", tip2Movimiento='" + getTip2Movimiento() + "'" +
            ", cnt=" + getCnt() +
            ", lote='" + getLote() + "'" +
            ", fecMovimiento='" + getFecMovimiento() + "'" +
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
