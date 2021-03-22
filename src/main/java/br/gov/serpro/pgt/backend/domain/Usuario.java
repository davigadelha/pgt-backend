package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "id_perfil")
    private Long idPerfil;

    @Column(name = "id_uf")
    private Long idUf;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "ic_ativo")
    private Boolean icAtivo;

    @OneToMany(mappedBy = "usuario")
    private Set<Solicitacao> solicitacaos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "usuarios", allowSetters = true)
    private Perfil perfil;

    /**
     * Another side of the same relationship
     */
    @ApiModelProperty(value = "Another side of the same relationship")
    @ManyToOne
    @JsonIgnoreProperties(value = "usuarios", allowSetters = true)
    private Uf uf;

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

    public Usuario nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public Usuario idPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
        return this;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdUf() {
        return idUf;
    }

    public Usuario idUf(Long idUf) {
        this.idUf = idUf;
        return this;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Usuario cpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Boolean isIcAtivo() {
        return icAtivo;
    }

    public Usuario icAtivo(Boolean icAtivo) {
        this.icAtivo = icAtivo;
        return this;
    }

    public void setIcAtivo(Boolean icAtivo) {
        this.icAtivo = icAtivo;
    }

    public Set<Solicitacao> getSolicitacaos() {
        return solicitacaos;
    }

    public Usuario solicitacaos(Set<Solicitacao> solicitacaos) {
        this.solicitacaos = solicitacaos;
        return this;
    }

    public Usuario addSolicitacao(Solicitacao solicitacao) {
        this.solicitacaos.add(solicitacao);
        solicitacao.setUsuario(this);
        return this;
    }

    public Usuario removeSolicitacao(Solicitacao solicitacao) {
        this.solicitacaos.remove(solicitacao);
        solicitacao.setUsuario(null);
        return this;
    }

    public void setSolicitacaos(Set<Solicitacao> solicitacaos) {
        this.solicitacaos = solicitacaos;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Usuario perfil(Perfil perfil) {
        this.perfil = perfil;
        return this;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Uf getUf() {
        return uf;
    }

    public Usuario uf(Uf uf) {
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
        if (!(o instanceof Usuario)) {
            return false;
        }
        return id != null && id.equals(((Usuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", idPerfil=" + getIdPerfil() +
            ", idUf=" + getIdUf() +
            ", cpfCnpj='" + getCpfCnpj() + "'" +
            ", icAtivo='" + isIcAtivo() + "'" +
            "}";
    }
}
