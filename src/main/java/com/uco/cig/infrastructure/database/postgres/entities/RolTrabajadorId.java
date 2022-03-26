package com.uco.cig.infrastructure.database.postgres.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolTrabajadorId implements Serializable {
    private static final long serialVersionUID = 2040930836799132825L;
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @Column(name = "idTrabajador", nullable = false)
    private Integer idTrabajador;

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idTrabajador);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolTrabajadorId entity = (RolTrabajadorId) o;
        return Objects.equals(this.idRol, entity.idRol) &&
                Objects.equals(this.idTrabajador, entity.idTrabajador);
    }
}