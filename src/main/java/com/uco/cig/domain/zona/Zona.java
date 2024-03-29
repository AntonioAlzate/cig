package com.uco.cig.domain.zona;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.util.Objects;

public class Zona {

    private static final String NOMBRE_ZONA_REQUERIDO = "El nombre de una Zona es requerido";
    private static final String CIUDAD_REQUERIDO = "El campo ciudad en una zona es requerido";

    private Integer id;
    private String nombre;
    private Ciudad ciudad;

    private Zona(Integer id, String nombre, Ciudad ciudad) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_ZONA_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_ZONA_REQUERIDO);
        this.ciudad = Objects.requireNonNull(ciudad, CIUDAD_REQUERIDO);
    }

    public static Zona nuevo( String nombre, Ciudad ciudad) throws BusinessException {
        return new Zona(null, nombre, ciudad);
    }

    public static Zona construir(Integer id, String nombre, Ciudad ciudad) throws BusinessException {
        return new Zona(id, nombre, ciudad);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}
