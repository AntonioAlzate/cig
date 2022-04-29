package com.uco.cig.domain.pais;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.validator.PaisValidator;

import java.util.Objects;

public class Pais {

    private static final String NOMBRE_PAIS_REQUERIDO = "El nombre de un País es requerido";

    private Integer id;
    private String nombre;

    private Pais(Integer id, String nombre) throws BusinessException {
        this.id = id;
        PaisValidator.validarCadenaNoVacia(nombre, NOMBRE_PAIS_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_PAIS_REQUERIDO);
    }

    public static Pais nuevo(String nombre) throws BusinessException {
        return new Pais(null, nombre);
    }

    public static Pais construir(Integer id, String nombre) throws BusinessException {
        return new Pais(id, nombre);
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
