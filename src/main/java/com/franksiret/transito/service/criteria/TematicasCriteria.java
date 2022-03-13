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
 * Criteria class for the {@link com.franksiret.transito.domain.Tematicas} entity. This class is used
 * in {@link com.franksiret.transito.web.rest.TematicasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tematicas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TematicasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter descrip;

    private IntegerFilter cantidad;

    private IntegerFilter preguntaId;

    private Boolean distinct;

    public TematicasCriteria() {}

    public TematicasCriteria(TematicasCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.descrip = other.descrip == null ? null : other.descrip.copy();
        this.cantidad = other.cantidad == null ? null : other.cantidad.copy();
        this.preguntaId = other.preguntaId == null ? null : other.preguntaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TematicasCriteria copy() {
        return new TematicasCriteria(this);
    }

    public IntegerFilter getId() {
        return id;
    }

    public IntegerFilter id() {
        if (id == null) {
            id = new IntegerFilter();
        }
        return id;
    }

    public void setId(IntegerFilter id) {
        this.id = id;
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

    public IntegerFilter getCantidad() {
        return cantidad;
    }

    public IntegerFilter cantidad() {
        if (cantidad == null) {
            cantidad = new IntegerFilter();
        }
        return cantidad;
    }

    public void setCantidad(IntegerFilter cantidad) {
        this.cantidad = cantidad;
    }

    public IntegerFilter getPreguntaId() {
        return preguntaId;
    }

    public IntegerFilter preguntaId() {
        if (preguntaId == null) {
            preguntaId = new IntegerFilter();
        }
        return preguntaId;
    }

    public void setPreguntaId(IntegerFilter preguntaId) {
        this.preguntaId = preguntaId;
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
        final TematicasCriteria that = (TematicasCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(descrip, that.descrip) &&
            Objects.equals(cantidad, that.cantidad) &&
            Objects.equals(preguntaId, that.preguntaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrip, cantidad, preguntaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TematicasCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (descrip != null ? "descrip=" + descrip + ", " : "") +
            (cantidad != null ? "cantidad=" + cantidad + ", " : "") +
            (preguntaId != null ? "preguntaId=" + preguntaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
