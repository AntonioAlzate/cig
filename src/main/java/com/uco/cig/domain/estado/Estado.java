package com.uco.cig.domain.estado;

import java.util.Objects;

public class Estado {

    private static final String ESTADO_REQUERIDO = "El estado es requerido";
    private Integer id;
    private String nombre;

    public Estado(Integer id, String nombre) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, ESTADO_REQUERIDO);
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
}

