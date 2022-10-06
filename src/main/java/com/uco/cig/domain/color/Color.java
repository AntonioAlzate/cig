package com.uco.cig.domain.color;

import java.util.Objects;

public class Color {

    private Integer id;
    private String nombre;

    public Color(Integer id, String nombre) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
