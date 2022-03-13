package com.franksiret.transito.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.franksiret.transito.domain.Fotos} entity.
 */
public class FotosDTO implements Serializable {

    private Integer nro;

    @Lob
    private byte[] foto;

    @Lob
    private byte[] foto1;

    @Lob
    private byte[] foto2;

    @Lob
    private byte[] foto3;

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public byte[] getFoto2() {
        return foto2;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    public byte[] getFoto3() {
        return foto3;
    }

    public void setFoto3(byte[] foto3) {
        this.foto3 = foto3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FotosDTO)) {
            return false;
        }

        FotosDTO fotosDTO = (FotosDTO) o;
        if (this.nro == null) {
            return false;
        }
        return Objects.equals(this.nro, fotosDTO.nro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nro);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FotosDTO{" +
            ", nro=" + getNro() +
            ", foto='" + getFoto() + "'" +
            ", foto1='" + getFoto1() + "'" +
            ", foto2='" + getFoto2() + "'" +
            ", foto3='" + getFoto3() + "'" +
            "}";
    }
}
