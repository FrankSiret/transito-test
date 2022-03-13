package com.franksiret.transito.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.franksiret.transito.domain.Artinc} entity.
 */
public class ArtincDTO implements Serializable {

    private Integer artinc;

    private String pelig;

    private String descrip;

    public Integer getArtinc() {
        return artinc;
    }

    public void setArtinc(Integer artinc) {
        this.artinc = artinc;
    }

    public String getPelig() {
        return pelig;
    }

    public void setPelig(String pelig) {
        this.pelig = pelig;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtincDTO)) {
            return false;
        }

        ArtincDTO artincDTO = (ArtincDTO) o;
        if (this.artinc == null) {
            return false;
        }
        return Objects.equals(this.artinc, artincDTO.artinc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.artinc);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArtincDTO{" +
            ", artinc=" + getArtinc() +
            ", pelig='" + getPelig() + "'" +
            ", descrip='" + getDescrip() + "'" +
            "}";
    }
}
