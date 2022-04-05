package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "barrio")
public class BarrioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "idZona", nullable = false)
    private ZonaEntity idZonaEntity;

    public BarrioEntity() {
    }

    public BarrioEntity(Integer id, String nombre, ZonaEntity idZonaEntity) {
        this.id = id;
        this.nombre = nombre;
        this.idZonaEntity = idZonaEntity;
    }

    public ZonaEntity getIdZona() {
        return idZonaEntity;
    }

    public void setIdZona(ZonaEntity idZonaEntity) {
        this.idZonaEntity = idZonaEntity;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}