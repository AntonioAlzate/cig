package com.uco.cig.domain.estado.cuota;

public class EstadoCuota {

    private Integer id;
    private String nombre;

    public EstadoCuota(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
