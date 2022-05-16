package com.uco.cig.domain.modalidad;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.util.Objects;

public class Modalidad {

    private static final String NOMBRE_REQUERIDO = "El nombre de una modalidad de pago es requerido";

    private Integer id;
    private String nombre;

    private Modalidad(Integer id, String nombre) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre);
    }

    public static Modalidad nuevo(String nombre) throws BusinessException {
        return new Modalidad(null, nombre);
    }

    public static Modalidad Construir(Integer id, String nombre) throws BusinessException {
        return new Modalidad(id, nombre);
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
