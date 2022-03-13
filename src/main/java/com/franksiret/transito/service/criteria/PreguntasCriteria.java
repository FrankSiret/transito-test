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
 * Criteria class for the {@link com.franksiret.transito.domain.Preguntas} entity. This class is used
 * in {@link com.franksiret.transito.web.rest.PreguntasResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /preguntas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PreguntasCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter texto;

    private StringFilter resp1;

    private StringFilter resp2;

    private StringFilter resp3;

    private IntegerFilter correcta;

    private BooleanFilter usada;

    private IntegerFilter puesto;

    private IntegerFilter fotoId;
    
    private IntegerFilter tematicaId;

    private IntegerFilter artincId;

    private Boolean distinct;

    public PreguntasCriteria() {}

    public PreguntasCriteria(PreguntasCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.texto = other.texto == null ? null : other.texto.copy();
        this.resp1 = other.resp1 == null ? null : other.resp1.copy();
        this.resp2 = other.resp2 == null ? null : other.resp2.copy();
        this.resp3 = other.resp3 == null ? null : other.resp3.copy();
        this.correcta = other.correcta == null ? null : other.correcta.copy();
        this.usada = other.usada == null ? null : other.usada.copy();
        this.puesto = other.puesto == null ? null : other.puesto.copy();
        this.tematicaId = other.tematicaId == null ? null : other.tematicaId.copy();
        this.artincId = other.artincId == null ? null : other.artincId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PreguntasCriteria copy() {
        return new PreguntasCriteria(this);
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

    public StringFilter getTexto() {
        return texto;
    }

    public StringFilter texto() {
        if (texto == null) {
            texto = new StringFilter();
        }
        return texto;
    }

    public void setTexto(StringFilter texto) {
        this.texto = texto;
    }

    public StringFilter getResp1() {
        return resp1;
    }

    public StringFilter resp1() {
        if (resp1 == null) {
            resp1 = new StringFilter();
        }
        return resp1;
    }

    public void setResp1(StringFilter resp1) {
        this.resp1 = resp1;
    }

    public StringFilter getResp2() {
        return resp2;
    }

    public StringFilter resp2() {
        if (resp2 == null) {
            resp2 = new StringFilter();
        }
        return resp2;
    }

    public void setResp2(StringFilter resp2) {
        this.resp2 = resp2;
    }

    public StringFilter getResp3() {
        return resp3;
    }

    public StringFilter resp3() {
        if (resp3 == null) {
            resp3 = new StringFilter();
        }
        return resp3;
    }

    public void setResp3(StringFilter resp3) {
        this.resp3 = resp3;
    }

    public IntegerFilter getCorrecta() {
        return correcta;
    }

    public IntegerFilter correcta() {
        if (correcta == null) {
            correcta = new IntegerFilter();
        }
        return correcta;
    }

    public void setCorrecta(IntegerFilter correcta) {
        this.correcta = correcta;
    }

    public BooleanFilter getUsada() {
        return usada;
    }

    public BooleanFilter usada() {
        if (usada == null) {
            usada = new BooleanFilter();
        }
        return usada;
    }

    public void setUsada(BooleanFilter usada) {
        this.usada = usada;
    }

    public IntegerFilter getPuesto() {
        return puesto;
    }

    public IntegerFilter puesto() {
        if (puesto == null) {
            puesto = new IntegerFilter();
        }
        return puesto;
    }

    public void setPuesto(IntegerFilter puesto) {
        this.puesto = puesto;
    }

    public IntegerFilter getFotoId() {
        return fotoId;
    }

    public IntegerFilter fotoId() {
        if (fotoId == null) {
            fotoId = new IntegerFilter();
        }
        return fotoId;
    }

    public void setFotoId(IntegerFilter fotoId) {
        this.fotoId = fotoId;
    }
   
    public IntegerFilter getTematicaId() {
        return tematicaId;
    }

    public IntegerFilter tematicaId() {
        if (tematicaId == null) {
            tematicaId = new IntegerFilter();
        }
        return tematicaId;
    }

    public void setTematicaId(IntegerFilter tematicaId) {
        this.tematicaId = tematicaId;
    }

    public IntegerFilter getArtincId() {
        return artincId;
    }

    public IntegerFilter artincId() {
        if (artincId == null) {
            artincId = new IntegerFilter();
        }
        return artincId;
    }

    public void setArtincId(IntegerFilter artincId) {
        this.artincId = artincId;
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
        final PreguntasCriteria that = (PreguntasCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(texto, that.texto) &&
            Objects.equals(resp1, that.resp1) &&
            Objects.equals(resp2, that.resp2) &&
            Objects.equals(resp3, that.resp3) &&
            Objects.equals(correcta, that.correcta) &&
            Objects.equals(usada, that.usada) &&
            Objects.equals(puesto, that.puesto) &&
            Objects.equals(fotoId, that.fotoId) &&
            Objects.equals(fotoId, that.fotoId) &&
            Objects.equals(artincId, that.artincId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, resp1, resp2, resp3, correcta, usada, puesto, fotoId, tematicaId, artincId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreguntasCriteria{" +
            (id != null ? "nro=" + id + ", " : "") +
            (texto != null ? "texto=" + texto + ", " : "") +
            (resp1 != null ? "resp1=" + resp1 + ", " : "") +
            (resp2 != null ? "resp2=" + resp2 + ", " : "") +
            (resp3 != null ? "resp3=" + resp3 + ", " : "") +
            (correcta != null ? "correcta=" + correcta + ", " : "") +
            (usada != null ? "usada=" + usada + ", " : "") +
            (puesto != null ? "puesto=" + puesto + ", " : "") +
            (fotoId != null ? "fotoId=" + fotoId + ", " : "") +
            (tematicaId != null ? "tematicaId=" + tematicaId + ", " : "") +
            (artincId != null ? "artincId=" + artincId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
