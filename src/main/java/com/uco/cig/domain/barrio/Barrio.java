package com.uco.cig.domain.barrio;

import com.uco.cig.domain.zona.Zona;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Barrio {

    private Integer id;
    private String nombre;
    private Zona zona;

    public Barrio(Integer id, String nombre, Zona zona) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}