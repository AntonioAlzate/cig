package com.uco.cig.domain.ciudad;

import com.uco.cig.domain.region.Region;

public class Ciudad {

    private Integer id;
    private String nombre;
    private Region region;

    public Ciudad(Integer id, String nombre, Region region) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
