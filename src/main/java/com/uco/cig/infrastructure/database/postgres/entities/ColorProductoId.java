package com.uco.cig.infrastructure.database.postgres.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ColorProductoId implements Serializable {
    private static final long serialVersionUID = 7466149823180098664L;
    @Column(name = "idColor", nullable = false)
    private Integer idColor;
    @Column(name = "idProducto", nullable = false)
    private Integer idProducto;

    public ColorProductoId() {
    }

    public ColorProductoId(Integer idColor, Integer idProducto) {
        this.idColor = idColor;
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ColorProductoId entity = (ColorProductoId) o;
        return Objects.equals(this.idProducto, entity.idProducto) &&
                Objects.equals(this.idColor, entity.idColor);
    }
}