package com.franksiret.transito.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Tematicas.
 */
@Entity
@Table(name = "tematicas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tematicas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "descrip")
    private String descrip;

    @Column(name = "cantidad")
    private Integer cantidad;

    @OneToMany(mappedBy = "tematica")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tematica", "artinc", "foto" }, allowSetters = true)
    private Set<Preguntas> preguntas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Tematicas id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return this.descrip;
    }

    public Tematicas descrip(String descrip) {
        this.setDescrip(descrip);
        return this;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public Tematicas cantidad(Integer cantidad) {
        this.setCantidad(cantidad);
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Set<Preguntas> getPreguntas() {
        return this.preguntas;
    }

    public void setPreguntas(Set<Preguntas> preguntas) {
        if (this.preguntas != null) {
            this.preguntas.forEach(i -> i.setTematica(null));
        }
        if (preguntas != null) {
            preguntas.forEach(i -> i.setTematica(this));
        }
        this.preguntas = preguntas;
    }

    public Tematicas preguntas(Set<Preguntas> preguntas) {
        this.setPreguntas(preguntas);
        return this;
    }

    public Tematicas addPregunta(Preguntas preguntas) {
        this.preguntas.add(preguntas);
        preguntas.setTematica(this);
        return this;
    }

    public Tematicas removePregunta(Preguntas preguntas) {
        this.preguntas.remove(preguntas);
        preguntas.setTematica(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tematicas)) {
            return false;
        }
        return id != null && id.equals(((Tematicas) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tematicas{" +
            "id=" + getId() +
            ", descrip='" + getDescrip() + "'" +
            ", cantidad=" + getCantidad() +
            "}";
    }
}
