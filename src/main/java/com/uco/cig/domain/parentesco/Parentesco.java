package com.uco.cig.domain.parentesco;

import java.util.Objects;

public class Parentesco {

    private static final String NOMBRE_REFERENCIA_REQUERIDO = "El nombre de una referencia es requerido";
    private Integer id;
    private String nombre;

    public Parentesco(Integer id, String nombre) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REFERENCIA_REQUERIDO);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
