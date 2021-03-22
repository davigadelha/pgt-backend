package br.gov.serpro.pgt.backend.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CategoriaTipoValidacao.
 */
@Entity
@Table(name = "categoria_tipo_validacao")
public class CategoriaTipoValidacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descricao")
    private String descricao;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    @OneToMany(mappedBy = "categoriaTipoValidacao")
    private Set<TipoValidacao> tipoValidacaos = new HashSet<>();

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

    public CategoriaTipoValidacao codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaTipoValidacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<TipoValidacao> getTipoValidacaos() {
        return tipoValidacaos;
    }

    public CategoriaTipoValidacao tipoValidacaos(Set<TipoValidacao> tipoValidacaos) {
        this.tipoValidacaos = tipoValidacaos;
        return this;
    }

    public CategoriaTipoValidacao addTipoValidacao(TipoValidacao tipoValidacao) {
        this.tipoValidacaos.add(tipoValidacao);
        tipoValidacao.setCategoriaTipoValidacao(this);
        return this;
    }

    public CategoriaTipoValidacao removeTipoValidacao(TipoValidacao tipoValidacao) {
        this.tipoValidacaos.remove(tipoValidacao);
        tipoValidacao.setCategoriaTipoValidacao(null);
        return this;
    }

    public void setTipoValidacaos(Set<TipoValidacao> tipoValidacaos) {
        this.tipoValidacaos = tipoValidacaos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoriaTipoValidacao)) {
            return false;
        }
        return id != null && id.equals(((CategoriaTipoValidacao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoriaTipoValidacao{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
