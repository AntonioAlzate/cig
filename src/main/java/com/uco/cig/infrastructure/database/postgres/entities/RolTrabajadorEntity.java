package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Rol_Trabajador")
public class RolTrabajadorEntity {
    @EmbeddedId
    private RolTrabajadorId id;

    public RolTrabajadorId getId() {
        return id;
    }

    public void setId(RolTrabajadorId id) {
        this.id = id;
    }
}