package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "RegistroDespacho")
public class RegistroDespachoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajadorRealiza", nullable = false)
    private TrabajadorEntity idTrabajadorRealiza;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajadorRecibe", nullable = false)
    private TrabajadorEntity idTrabajadorRecibe;

    public TrabajadorEntity getIdTrabajadorRecibe() {
        return idTrabajadorRecibe;
    }

    public void setIdTrabajadorRecibe(TrabajadorEntity idTrabajadorRecibe) {
        this.idTrabajadorRecibe = idTrabajadorRecibe;
    }

    public TrabajadorEntity getIdTrabajadorRealiza() {
        return idTrabajadorRealiza;
    }

    public void setIdTrabajadorRealiza(TrabajadorEntity idTrabajadorRealiza) {
        this.idTrabajadorRealiza = idTrabajadorRealiza;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}