package br.gov.serpro.pgt.backend.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Perfil.
 */
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "perfil")
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "perfil_funcionalidade",
               joinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "funcionalidade_id", referencedColumnName = "id"))
    private Set<Funcionalidade> funcionalidades = new HashSet<>();

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

    public Perfil nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Perfil usuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public Perfil addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.setPerfil(this);
        return this;
    }

    public Perfil removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.setPerfil(null);
        return this;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Funcionalidade> getFuncionalidades() {
        return funcionalidades;
    }

    public Perfil funcionalidades(Set<Funcionalidade> funcionalidades) {
        this.funcionalidades = funcionalidades;
        return this;
    }

    public Perfil addFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidades.add(funcionalidade);
        funcionalidade.getPerfils().add(this);
        return this;
    }

    public Perfil removeFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidades.remove(funcionalidade);
        funcionalidade.getPerfils().remove(this);
        return this;
    }

    public void setFuncionalidades(Set<Funcionalidade> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Perfil)) {
            return false;
        }
        return id != null && id.equals(((Perfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Perfil{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
