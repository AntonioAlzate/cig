package com.uco.cig.domain.categoria;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.util.Objects;

public class Categoria {

    private static final String NOMBRE_CATEGORIA_VACIO = "El nombre de una Categoría no puede estar vacio";
    private Integer id;
    private String nombre;

    private Categoria(Integer id, String nombre) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_CATEGORIA_VACIO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_CATEGORIA_VACIO);
    }

    public static Categoria nuevo(String nombre) throws BusinessException {
        return new Categoria(null, nombre);
    }

    public static Categoria construir(Integer id, String nombre) throws BusinessException {
        return new Categoria(id, nombre);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
