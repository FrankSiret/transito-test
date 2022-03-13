package com.franksiret.transito.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Preguntas.
 */
@Entity
@Table(name = "preguntas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Preguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "nro")
    private Integer id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "resp_1")
    private String resp1;

    @Column(name = "resp_2")
    private String resp2;

    @Column(name = "resp_3")
    private String resp3;

    @Column(name = "correcta")
    private Integer correcta;

    @Column(name = "usada")
    private Boolean usada;

    @Column(name = "puesto")
    private Integer puesto;

    @ManyToOne
    @JsonIgnoreProperties(value = { "preguntas" }, allowSetters = true)
    private Fotos foto;
    
    @ManyToOne
    @JsonIgnoreProperties(value = { "preguntas" }, allowSetters = true)
    private Tematicas tematica;

    @ManyToOne
    @JsonIgnoreProperties(value = { "preguntas" }, allowSetters = true)
    private Artinc artinc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getNro() {
        return this.id;
    }

    public Preguntas nro(Integer nro) {
        this.setNro(nro);
        return this;
    }

    public void setNro(Integer nro) {
        this.id = nro;
    }

    public String getTexto() {
        return this.texto;
    }

    public Preguntas texto(String texto) {
        this.setTexto(texto);
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getResp1() {
        return this.resp1;
    }

    public Preguntas resp1(String resp1) {
        this.setResp1(resp1);
        return this;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return this.resp2;
    }

    public Preguntas resp2(String resp2) {
        this.setResp2(resp2);
        return this;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return this.resp3;
    }

    public Preguntas resp3(String resp3) {
        this.setResp3(resp3);
        return this;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public Integer getCorrecta() {
        return this.correcta;
    }

    public Preguntas correcta(Integer correcta) {
        this.setCorrecta(correcta);
        return this;
    }

    public void setCorrecta(Integer correcta) {
        this.correcta = correcta;
    }

    public Boolean getUsada() {
        return this.usada;
    }

    public Preguntas usada(Boolean usada) {
        this.setUsada(usada);
        return this;
    }

    public void setUsada(Boolean usada) {
        this.usada = usada;
    }

    public Integer getPuesto() {
        return this.puesto;
    }

    public Preguntas puesto(Integer puesto) {
        this.setPuesto(puesto);
        return this;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }

    public Fotos getFoto() {
        return this.foto;
    }

    public void setFoto(Fotos foto) {
        this.foto = foto;
    }

    public Preguntas foto(Fotos foto) {
        this.setFoto(foto);
        return this;
    }
    
    public Tematicas getTematica() {
        return this.tematica;
    }

    public void setTematica(Tematicas tematicas) {
        this.tematica = tematicas;
    }

    public Preguntas tematica(Tematicas tematicas) {
        this.setTematica(tematicas);
        return this;
    }

    public Artinc getArtinc() {
        return this.artinc;
    }

    public void setArtinc(Artinc artinc) {
        this.artinc = artinc;
    }

    public Preguntas artinc(Artinc artinc) {
        this.setArtinc(artinc);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Preguntas)) {
            return false;
        }
        return id != null && id.equals(((Preguntas) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Preguntas{" +
            ", nro=" + getNro() +
            ", texto='" + getTexto() + "'" +
            ", resp1='" + getResp1() + "'" +
            ", resp2='" + getResp2() + "'" +
            ", resp3='" + getResp3() + "'" +
            ", correcta=" + getCorrecta() +
            ", usada='" + getUsada() + "'" +
            ", puesto=" + getPuesto() +
            "}";
    }
}
