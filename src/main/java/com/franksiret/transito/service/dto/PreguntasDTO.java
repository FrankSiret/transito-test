package com.franksiret.transito.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.franksiret.transito.domain.Preguntas} entity.
 */
public class PreguntasDTO implements Serializable {

    private Integer nro;

    private String texto;

    private String resp1;

    private String resp2;

    private String resp3;

    private Integer correcta;

    private Boolean usada;

    private Integer puesto;

    private FotosDTO foto;

    private TematicasDTO tematica;

    private ArtincDTO artinc;

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public Integer getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Integer correcta) {
        this.correcta = correcta;
    }

    public Boolean getUsada() {
        return usada;
    }

    public void setUsada(Boolean usada) {
        this.usada = usada;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }

    public FotosDTO getFoto() {
        return foto;
    }

    public void setFoto(FotosDTO foto) {
        this.foto = foto;
    }

    public TematicasDTO getTematica() {
        return tematica;
    }

    public void setTematica(TematicasDTO tematica) {
        this.tematica = tematica;
    }

    public ArtincDTO getArtinc() {
        return artinc;
    }

    public void setArtinc(ArtincDTO artinc) {
        this.artinc = artinc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreguntasDTO)) {
            return false;
        }

        PreguntasDTO preguntasDTO = (PreguntasDTO) o;
        if (this.nro == null) {
            return false;
        }
        return Objects.equals(this.nro, preguntasDTO.nro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nro);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreguntasDTO{" +
            ", nro=" + getNro() +
            ", texto='" + getTexto() + "'" +
            ", resp1='" + getResp1() + "'" +
            ", resp2='" + getResp2() + "'" +
            ", resp3='" + getResp3() + "'" +
            ", correcta=" + getCorrecta() +
            ", usada='" + getUsada() + "'" +
            ", puesto=" + getPuesto() +
            ", tematica=" + getTematica() +
            ", artinc=" + getArtinc() +
            "}";
    }
}
