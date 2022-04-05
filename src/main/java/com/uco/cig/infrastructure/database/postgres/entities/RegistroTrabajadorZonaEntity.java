package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "registro_trabajador_zona")
public class RegistroTrabajadorZonaEntity {
    @EmbeddedId
    private RegistroTrabajadorZonaId id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public RegistroTrabajadorZonaId getId() {
        return id;
    }

    public void setId(RegistroTrabajadorZonaId id) {
        this.id = id;
    }
}