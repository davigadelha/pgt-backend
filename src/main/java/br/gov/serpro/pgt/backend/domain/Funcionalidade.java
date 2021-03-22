package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Funcionalidade.
 */
@Entity
@Table(name = "funcionalidade")
public class Funcionalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "funcionalidades")
    @JsonIgnore
    private Set<Perfil> perfils = new HashSet<>();

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

    public Funcionalidade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Perfil> getPerfils() {
        return perfils;
    }

    public Funcionalidade perfils(Set<Perfil> perfils) {
        this.perfils = perfils;
        return this;
    }

    public Funcionalidade addPerfil(Perfil perfil) {
        this.perfils.add(perfil);
        perfil.getFuncionalidades().add(this);
        return this;
    }

    public Funcionalidade removePerfil(Perfil perfil) {
        this.perfils.remove(perfil);
        perfil.getFuncionalidades().remove(this);
        return this;
    }

    public void setPerfils(Set<Perfil> perfils) {
        this.perfils = perfils;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Funcionalidade)) {
            return false;
        }
        return id != null && id.equals(((Funcionalidade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Funcionalidade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
