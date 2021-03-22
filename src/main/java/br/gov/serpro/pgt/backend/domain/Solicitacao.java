package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Solicitacao.
 */
@Entity
@Table(name = "solicitacao")
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_usuario_incra")
    private Long idUsuarioIncra;

    @Column(name = "cpf_cnpj_solicitante")
    private String cpfCnpjSolicitante;

    @Column(name = "data_solicitacao")
    private Instant dataSolicitacao;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "protocolo")
    private String protocolo;

    @OneToOne
    @JoinColumn(unique = true)
    private ResultadoValidacao idSolicitacao;

    @ManyToOne
    @JsonIgnoreProperties(value = "solicitacaos", allowSetters = true)
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuarioIncra() {
        return idUsuarioIncra;
    }

    public Solicitacao idUsuarioIncra(Long idUsuarioIncra) {
        this.idUsuarioIncra = idUsuarioIncra;
        return this;
    }

    public void setIdUsuarioIncra(Long idUsuarioIncra) {
        this.idUsuarioIncra = idUsuarioIncra;
    }

    public String getCpfCnpjSolicitante() {
        return cpfCnpjSolicitante;
    }

    public Solicitacao cpfCnpjSolicitante(String cpfCnpjSolicitante) {
        this.cpfCnpjSolicitante = cpfCnpjSolicitante;
        return this;
    }

    public void setCpfCnpjSolicitante(String cpfCnpjSolicitante) {
        this.cpfCnpjSolicitante = cpfCnpjSolicitante;
    }

    public Instant getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Solicitacao dataSolicitacao(Instant dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
        return this;
    }

    public void setDataSolicitacao(Instant dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public Solicitacao situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Solicitacao conteudo(String conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public Solicitacao protocolo(String protocolo) {
        this.protocolo = protocolo;
        return this;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public ResultadoValidacao getIdSolicitacao() {
        return idSolicitacao;
    }

    public Solicitacao idSolicitacao(ResultadoValidacao resultadoValidacao) {
        this.idSolicitacao = resultadoValidacao;
        return this;
    }

    public void setIdSolicitacao(ResultadoValidacao resultadoValidacao) {
        this.idSolicitacao = resultadoValidacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Solicitacao usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Solicitacao)) {
            return false;
        }
        return id != null && id.equals(((Solicitacao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Solicitacao{" +
            "id=" + getId() +
            ", idUsuarioIncra=" + getIdUsuarioIncra() +
            ", cpfCnpjSolicitante='" + getCpfCnpjSolicitante() + "'" +
            ", dataSolicitacao='" + getDataSolicitacao() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", conteudo='" + getConteudo() + "'" +
            ", protocolo='" + getProtocolo() + "'" +
            "}";
    }
}
