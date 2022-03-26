package com.uco.cig.infrastructure.database.postgres.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegistroTrabajadorZonaId implements Serializable {
    private static final long serialVersionUID = 5425328855306974742L;
    @Column(name = "idTrabajador", nullable = false)
    private Integer idTrabajador;
    @Column(name = "idZona", nullable = false)
    private Integer idZona;

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrabajador, idZona);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegistroTrabajadorZonaId entity = (RegistroTrabajadorZonaId) o;
        return Objects.equals(this.idTrabajador, entity.idTrabajador) &&
                Objects.equals(this.idZona, entity.idZona);
    }
}