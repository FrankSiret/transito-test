package com.franksiret.transito.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.franksiret.transito.domain.Categorias} entity.
 */
public class CategoriasDTO implements Serializable {

    private Integer codigo;

    private String tipo;

    private String descrip;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(o instanceof CategoriasDTO)) {
            return false;
        }

        CategoriasDTO categoriasDTO = (CategoriasDTO) o;
        if (this.codigo == null) {
            return false;
        }
        return Objects.equals(this.codigo, categoriasDTO.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoriasDTO{" +
            ", codigo=" + getCodigo() +
            ", tipo='" + getTipo() + "'" +
            ", descrip='" + getDescrip() + "'" +
            "}";
    }
}
