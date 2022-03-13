package com.franksiret.transito.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.franksiret.transito.domain.Tematicas} entity.
 */
public class TematicasDTO implements Serializable {

    private Integer id;

    private String descrip;

    private Integer cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TematicasDTO)) {
            return false;
        }

        TematicasDTO tematicasDTO = (TematicasDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tematicasDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TematicasDTO{" +
            "id='" + getId() + "'" +
            ", descrip='" + getDescrip() + "'" +
            ", cantidad=" + getCantidad() +
            "}";
    }
}
