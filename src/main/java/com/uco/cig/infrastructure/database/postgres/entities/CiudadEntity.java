package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class CiudadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRegion", nullable = false)
    private RegionEntity idRegionEntity;

    public CiudadEntity() {
    }

    public CiudadEntity(Integer id, String nombre, RegionEntity idRegionEntity) {
        this.id = id;
        this.nombre = nombre;
        this.idRegionEntity = idRegionEntity;
    }

    public RegionEntity getIdRegion() {
        return idRegionEntity;
    }

    public void setIdRegion(RegionEntity idRegionEntity) {
        this.idRegionEntity = idRegionEntity;
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