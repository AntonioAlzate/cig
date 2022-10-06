package com.uco.cig.domain.estado.ventas;

public class EstadoVenta {

    private Integer id;
    private String nombre;

    public EstadoVenta(Integer id, String nombre) {
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
