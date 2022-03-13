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
 * Criteria class for the {@link com.franksiret.transito.domain.Fotos} entity. This class is used
 * in {@link com.franksiret.transito.web.rest.FotosResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fotos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FotosCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private IntegerFilter preguntaId;

    private Boolean distinct;

    public FotosCriteria() {}

    public FotosCriteria(FotosCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.preguntaId = other.preguntaId == null ? null : other.preguntaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FotosCriteria copy() {
        return new FotosCriteria(this);
    }

    public IntegerFilter getNro() {
        return id;
    }

    public IntegerFilter nro() {
        if (id == null) {
            id = new IntegerFilter();
        }
        return id;
    }

    public void setNro(IntegerFilter nro) {
        this.id = nro;
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
        final FotosCriteria that = (FotosCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(preguntaId, that.preguntaId) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preguntaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FotosCriteria{" +
            (id != null ? "nro=" + id + ", " : "") +
            (preguntaId != null ? "preguntaId=" + preguntaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}