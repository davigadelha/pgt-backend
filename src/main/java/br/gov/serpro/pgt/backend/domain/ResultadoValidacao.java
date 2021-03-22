package br.gov.serpro.pgt.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ResultadoValidacao.
 */
@Entity
@Table(name = "resultado_validacao")
public class ResultadoValidacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_tipo_validacao")
    private Long idTipoValidacao;

    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @Column(name = "data_validacao")
    private Instant dataValidacao;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "resultado_parecer")
    private String resultadoParecer;

    @Column(name = "id_usuario_incra")
    private Long idUsuarioIncra;

    @Column(name = "data_parecer")
    private Instant dataParecer;

    @ManyToOne
    @JsonIgnoreProperties(value = "resultadoValidacaos", allowSetters = true)
    private TipoValidacao tipoValidacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoValidacao() {
        return idTipoValidacao;
    }

    public ResultadoValidacao idTipoValidacao(Long idTipoValidacao) {
        this.idTipoValidacao = idTipoValidacao;
        return this;
    }

    public void setIdTipoValidacao(Long idTipoValidacao) {
        this.idTipoValidacao = idTipoValidacao;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public ResultadoValidacao idSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
        return this;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Instant getDataValidacao() {
        return dataValidacao;
    }

    public ResultadoValidacao dataValidacao(Instant dataValidacao) {
        this.dataValidacao = dataValidacao;
        return this;
    }

    public void setDataValidacao(Instant dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public String getResultado() {
        return resultado;
    }

    public ResultadoValidacao resultado(String resultado) {
        this.resultado = resultado;
        return this;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getSituacao() {
        return situacao;
    }

    public ResultadoValidacao situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getResultadoParecer() {
        return resultadoParecer;
    }

    public ResultadoValidacao resultadoParecer(String resultadoParecer) {
        this.resultadoParecer = resultadoParecer;
        return this;
    }

    public void setResultadoParecer(String resultadoParecer) {
        this.resultadoParecer = resultadoParecer;
    }

    public Long getIdUsuarioIncra() {
        return idUsuarioIncra;
    }

    public ResultadoValidacao idUsuarioIncra(Long idUsuarioIncra) {
        this.idUsuarioIncra = idUsuarioIncra;
        return this;
    }

    public void setIdUsuarioIncra(Long idUsuarioIncra) {
        this.idUsuarioIncra = idUsuarioIncra;
    }

    public Instant getDataParecer() {
        return dataParecer;
    }

    public ResultadoValidacao dataParecer(Instant dataParecer) {
        this.dataParecer = dataParecer;
        return this;
    }

    public void setDataParecer(Instant dataParecer) {
        this.dataParecer = dataParecer;
    }

    public TipoValidacao getTipoValidacao() {
        return tipoValidacao;
    }

    public ResultadoValidacao tipoValidacao(TipoValidacao tipoValidacao) {
        this.tipoValidacao = tipoValidacao;
        return this;
    }

    public void setTipoValidacao(TipoValidacao tipoValidacao) {
        this.tipoValidacao = tipoValidacao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultadoValidacao)) {
            return false;
        }
        return id != null && id.equals(((ResultadoValidacao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResultadoValidacao{" +
            "id=" + getId() +
            ", idTipoValidacao=" + getIdTipoValidacao() +
            ", idSolicitacao=" + getIdSolicitacao() +
            ", dataValidacao='" + getDataValidacao() + "'" +
            ", resultado='" + getResultado() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", resultadoParecer='" + getResultadoParecer() + "'" +
            ", idUsuarioIncra=" + getIdUsuarioIncra() +
            ", dataParecer='" + getDataParecer() + "'" +
            "}";
    }
}
