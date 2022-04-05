package com.uco.cig.domain.zona;

import com.uco.cig.domain.ciudad.Ciudad;

public class Zona {

    private Integer id;
    private String nombre;
    private Ciudad ciudad;

    public Zona(Integer id, String nombre, Ciudad ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
