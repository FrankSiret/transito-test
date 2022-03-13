package com.franksiret.transito.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fotos.
 */
@Entity
@Table(name = "fotos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fotos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nro")
    private Integer id;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Lob
    @Column(name = "foto1")
    private byte[] foto1;

    @Lob
    @Column(name = "foto2")
    private byte[] foto2;

    @Lob
    @Column(name = "foto3")
    private byte[] foto3;

    @OneToMany(mappedBy = "foto")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tematica", "artinc", "foto" }, allowSetters = true)
    private Set<Preguntas> preguntas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getNro() {
        return this.id;
    }

    public Fotos nro(Integer nro) {
        this.setNro(nro);
        return this;
    }

    public void setNro(Integer nro) {
        this.id = nro;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public Fotos foto(byte[] foto) {
        this.setFoto(foto);
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto1() {
        return this.foto1;
    }

    public Fotos foto1(byte[] foto1) {
        this.setFoto1(foto1);
        return this;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public byte[] getFoto2() {
        return this.foto2;
    }

    public Fotos foto2(byte[] foto2) {
        this.setFoto2(foto2);
        return this;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public byte[] getFoto3() {
        return this.foto3;
    }

    public Fotos foto3(byte[] foto3) {
        this.setFoto3(foto3);
        return this;
    }

    public void setFoto3(byte[] foto3) {
        this.foto3 = foto3;
    }

    public Set<Preguntas> getPreguntas() {
        return this.preguntas;
    }

    public void setPreguntas(Set<Preguntas> preguntas) {
        if (this.preguntas != null) {
            this.preguntas.forEach(i -> i.setFoto(null));
        }
        if (preguntas != null) {
            preguntas.forEach(i -> i.setFoto(this));
        }
        this.preguntas = preguntas;
    }

    public Fotos preguntas(Set<Preguntas> preguntas) {
        this.setPreguntas(preguntas);
        return this;
    }

    public Fotos addPregunta(Preguntas preguntas) {
        this.preguntas.add(preguntas);
        preguntas.setFoto(this);
        return this;
    }

    public Fotos removePregunta(Preguntas preguntas) {
        this.preguntas.remove(preguntas);
        preguntas.setFoto(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fotos)) {
            return false;
        }
        return id != null && id.equals(((Fotos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fotos{" +
            ", nro=" + getNro() +
            ", foto='" + getFoto() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto3='" + getFoto3() + "'" +
            "}";
    }
}
