package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoValidacao.
 */
@Entity
@Table(name = "tipo_validacao")
public class TipoValidacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "id_uf")
    private Long idUf;

    @Column(name = "ic_impeditivo")
    private Boolean icImpeditivo;

    @Column(name = "ic_ativo")
    private Boolean icAtivo;

    @OneToMany(mappedBy = "tipoValidacao")
    private Set<ResultadoValidacao> resultadoValidacaos = new HashSet<>();

    /**
     * Another side of the same relationship
     */
    @ApiModelProperty(value = "Another side of the same relationship")
    @ManyToOne
    @JsonIgnoreProperties(value = "tipoValidacaos", allowSetters = true)
    private CategoriaTipoValidacao categoriaTipoValidacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoValidacao codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoValidacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public TipoValidacao categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getIdUf() {
        return idUf;
    }

    public TipoValidacao idUf(Long idUf) {
        this.idUf = idUf;
        return this;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public Boolean isIcImpeditivo() {
        return icImpeditivo;
    }

    public TipoValidacao icImpeditivo(Boolean icImpeditivo) {
        this.icImpeditivo = icImpeditivo;
        return this;
    }

    public void setIcImpeditivo(Boolean icImpeditivo) {
        this.icImpeditivo = icImpeditivo;
    }

    public Boolean isIcAtivo() {
        return icAtivo;
    }

    public TipoValidacao icAtivo(Boolean icAtivo) {
        this.icAtivo = icAtivo;
        return this;
    }

    public void setIcAtivo(Boolean icAtivo) {
        this.icAtivo = icAtivo;
    }

    public Set<ResultadoValidacao> getResultadoValidacaos() {
        return resultadoValidacaos;
    }

    public TipoValidacao resultadoValidacaos(Set<ResultadoValidacao> resultadoValidacaos) {
        this.resultadoValidacaos = resultadoValidacaos;
        return this;
    }

    public TipoValidacao addResultadoValidacao(ResultadoValidacao resultadoValidacao) {
        this.resultadoValidacaos.add(resultadoValidacao);
        resultadoValidacao.setTipoValidacao(this);
        return this;
    }

    public TipoValidacao removeResultadoValidacao(ResultadoValidacao resultadoValidacao) {
        this.resultadoValidacaos.remove(resultadoValidacao);
        resultadoValidacao.setTipoValidacao(null);
        return this;
    }

    public void setResultadoValidacaos(Set<ResultadoValidacao> resultadoValidacaos) {
        this.resultadoValidacaos = resultadoValidacaos;
    }

    public CategoriaTipoValidacao getCategoriaTipoValidacao() {
        return categoriaTipoValidacao;
    }

    public TipoValidacao categoriaTipoValidacao(CategoriaTipoValidacao categoriaTipoValidacao) {
        this.categoriaTipoValidacao = categoriaTipoValidacao;
        return this;
    }

    public void setCategoriaTipoValidacao(CategoriaTipoValidacao categoriaTipoValidacao) {
        this.categoriaTipoValidacao = categoriaTipoValidacao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoValidacao)) {
            return false;
        }
        return id != null && id.equals(((TipoValidacao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoValidacao{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", idUf=" + getIdUf() +
            ", icImpeditivo='" + isIcImpeditivo() + "'" +
            ", icAtivo='" + isIcAtivo() + "'" +
            "}";
    }
}
