package com.franksiret.transito.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.franksiret.transito.domain.Categorias} entity. This class is used
 * in {@link com.franksiret.transito.web.rest.CategoriasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /categorias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CategoriasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter tipo;

    private StringFilter descrip;

    private Boolean distinct;

    public CategoriasCriteria() {}

    public CategoriasCriteria(CategoriasCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tipo = other.tipo == null ? null : other.tipo.copy();
        this.descrip = other.descrip == null ? null : other.descrip.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CategoriasCriteria copy() {
        return new CategoriasCriteria(this);
    }

    public IntegerFilter getCodigo() {
        return id;
    }

    public IntegerFilter codigo() {
        if (id == null) {
            id = new IntegerFilter();
        }
        return id;
    }

    public void setCodigo(IntegerFilter codigo) {
        this.id = codigo;
    }

    public StringFilter getTipo() {
        return tipo;
    }

    public StringFilter tipo() {
        if (tipo == null) {
            tipo = new StringFilter();
        }
        return tipo;
    }

    public void setTipo(StringFilter tipo) {
        this.tipo = tipo;
    }

    public StringFilter getDescrip() {
        return descrip;
    }

    public StringFilter descrip() {
        if (descrip == null) {
            descrip = new StringFilter();
        }
        return descrip;
    }

    public void setDescrip(StringFilter descrip) {
        this.descrip = descrip;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CategoriasCriteria that = (CategoriasCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(tipo, that.tipo) &&
            Objects.equals(descrip, that.descrip) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, descrip, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoriasCriteria{" +
            (id != null ? "codigo=" + id + ", " : "") +
            (tipo != null ? "tipo=" + tipo + ", " : "") +
            (descrip != null ? "descrip=" + descrip + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
