package com.franksiret.transito.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Categorias.
 */
@Entity
@Table(name = "categorias")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo")
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "descrip")
    private String descrip;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getCodigo() {
        return this.id;
    }

    public Categorias codigo(Integer codigo) {
        this.setCodigo(codigo);
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.id = codigo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Categorias tipo(String tipo) {
        this.setTipo(tipo);
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrip() {
        return this.descrip;
    }

    public Categorias descrip(String descrip) {
        this.setDescrip(descrip);
        return this;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorias)) {
            return false;
        }
        return id != null && id.equals(((Categorias) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Categorias{" +
            ", codigo=" + getCodigo() +
            ", tipo='" + getTipo() + "'" +
            ", descrip='" + getDescrip() + "'" +
            "}";
    }
}
