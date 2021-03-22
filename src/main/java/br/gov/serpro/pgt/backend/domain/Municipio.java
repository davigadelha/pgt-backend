package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Municipio.
 */
@Entity
@Table(name = "municipio")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo_ibge")
    private Long codigoIbge;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "id_uf")
    private Long idUf;

    @Column(name = "codigo_tom")
    private Long codigoTom;

    @Column(name = "area", precision = 21, scale = 2)
    private BigDecimal area;

    @Column(name = "area_modulo_fiscal", precision = 21, scale = 2)
    private BigDecimal areaModuloFiscal;

    @ManyToOne
    @JsonIgnoreProperties(value = "municipios", allowSetters = true)
    private Uf uf;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public Municipio codigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public void setCodigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public Municipio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Municipio sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getIdUf() {
        return idUf;
    }

    public Municipio idUf(Long idUf) {
        this.idUf = idUf;
        return this;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public Long getCodigoTom() {
        return codigoTom;
    }

    public Municipio codigoTom(Long codigoTom) {
        this.codigoTom = codigoTom;
        return this;
    }

    public void setCodigoTom(Long codigoTom) {
        this.codigoTom = codigoTom;
    }

    public BigDecimal getArea() {
        return area;
    }

    public Municipio area(BigDecimal area) {
        this.area = area;
        return this;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getAreaModuloFiscal() {
        return areaModuloFiscal;
    }

    public Municipio areaModuloFiscal(BigDecimal areaModuloFiscal) {
        this.areaModuloFiscal = areaModuloFiscal;
        return this;
    }

    public void setAreaModuloFiscal(BigDecimal areaModuloFiscal) {
        this.areaModuloFiscal = areaModuloFiscal;
    }

    public Uf getUf() {
        return uf;
    }

    public Municipio uf(Uf uf) {
        this.uf = uf;
        return this;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Municipio)) {
            return false;
        }
        return id != null && id.equals(((Municipio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Municipio{" +
            "id=" + getId() +
            ", codigoIbge=" + getCodigoIbge() +
            ", nome='" + getNome() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", idUf=" + getIdUf() +
            ", codigoTom=" + getCodigoTom() +
            ", area=" + getArea() +
            ", areaModuloFiscal=" + getAreaModuloFiscal() +
            "}";
    }
}
