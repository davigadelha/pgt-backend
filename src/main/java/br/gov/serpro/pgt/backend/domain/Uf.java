package br.gov.serpro.pgt.backend.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Uf.
 */
@Entity
@Table(name = "uf")
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    @OneToMany(mappedBy = "uf")
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "uf")
    private Set<Municipio> municipios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Uf nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Uf sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Uf usuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public Uf addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.setUf(this);
        return this;
    }

    public Uf removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.setUf(null);
        return this;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Municipio> getMunicipios() {
        return municipios;
    }

    public Uf municipios(Set<Municipio> municipios) {
        this.municipios = municipios;
        return this;
    }

    public Uf addMunicipio(Municipio municipio) {
        this.municipios.add(municipio);
        municipio.setUf(this);
        return this;
    }

    public Uf removeMunicipio(Municipio municipio) {
        this.municipios.remove(municipio);
        municipio.setUf(null);
        return this;
    }

    public void setMunicipios(Set<Municipio> municipios) {
        this.municipios = municipios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Uf)) {
            return false;
        }
        return id != null && id.equals(((Uf) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Uf{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sigla='" + getSigla() + "'" +
            "}";
    }
}
