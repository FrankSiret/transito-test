package com.franksiret.transito.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Artinc.
 */
@Entity
@Table(name = "artinc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Artinc implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "artinc")
    private Integer id;

    @Column(name = "pelig")
    private String pelig;

    @Column(name = "descrip")
    private String descrip;

    @OneToMany(mappedBy = "artinc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tematica", "artinc", "foto" }, allowSetters = true)
    private Set<Preguntas> preguntas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getArtinc() {
        return this.id;
    }

    public Artinc artinc(Integer artinc) {
        this.setArtinc(artinc);
        return this;
    }

    public void setArtinc(Integer artinc) {
        this.id = artinc;
    }

    public String getPelig() {
        return this.pelig;
    }

    public Artinc pelig(String pelig) {
        this.setPelig(pelig);
        return this;
    }

    public void setPelig(String pelig) {
        this.pelig = pelig;
    }

    public String getDescrip() {
        return this.descrip;
    }

    public Artinc descrip(String descrip) {
        this.setDescrip(descrip);
        return this;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Set<Preguntas> getPreguntas() {
        return this.preguntas;
    }

    public void setPreguntas(Set<Preguntas> preguntas) {
        if (this.preguntas != null) {
            this.preguntas.forEach(i -> i.setArtinc(null));
        }
        if (preguntas != null) {
            preguntas.forEach(i -> i.setArtinc(this));
        }
        this.preguntas = preguntas;
    }

    public Artinc preguntas(Set<Preguntas> preguntas) {
        this.setPreguntas(preguntas);
        return this;
    }

    public Artinc addPregunta(Preguntas preguntas) {
        this.preguntas.add(preguntas);
        preguntas.setArtinc(this);
        return this;
    }

    public Artinc removePregunta(Preguntas preguntas) {
        this.preguntas.remove(preguntas);
        preguntas.setArtinc(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artinc)) {
            return false;
        }
        return id != null && id.equals(((Artinc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Artinc{" +
            ", artinc=" + getArtinc() +
            ", pelig='" + getPelig() + "'" +
            ", descrip='" + getDescrip() + "'" +
            "}";
    }
}
