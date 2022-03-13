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
 * Criteria class for the {@link com.franksiret.transito.domain.Artinc} entity. This class is used
 * in {@link com.franksiret.transito.web.rest.ArtincResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /artincs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ArtincCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter pelig;

    private StringFilter descrip;

    private IntegerFilter preguntaId;

    private Boolean distinct;

    public ArtincCriteria() {}

    public ArtincCriteria(ArtincCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.pelig = other.pelig == null ? null : other.pelig.copy();
        this.descrip = other.descrip == null ? null : other.descrip.copy();
        this.preguntaId = other.preguntaId == null ? null : other.preguntaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ArtincCriteria copy() {
        return new ArtincCriteria(this);
    }

    public IntegerFilter getArtinc() {
        return id;
    }

    public IntegerFilter artinc() {
        if (id == null) {
            id = new IntegerFilter();
        }
        return id;
    }

    public void setArtinc(IntegerFilter artinc) {
        this.id = artinc;
    }

    public StringFilter getPelig() {
        return pelig;
    }

    public StringFilter pelig() {
        if (pelig == null) {
            pelig = new StringFilter();
        }
        return pelig;
    }

    public void setPelig(StringFilter pelig) {
        this.pelig = pelig;
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
        final ArtincCriteria that = (ArtincCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(pelig, that.pelig) &&
            Objects.equals(descrip, that.descrip) &&
            Objects.equals(preguntaId, that.preguntaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pelig, descrip, preguntaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArtincCriteria{" +
            (id != null ? "artinc=" + id + ", " : "") +
            (pelig != null ? "pelig=" + pelig + ", " : "") +
            (descrip != null ? "descrip=" + descrip + ", " : "") +
            (preguntaId != null ? "preguntaId=" + preguntaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
