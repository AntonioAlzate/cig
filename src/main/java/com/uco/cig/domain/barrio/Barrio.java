package com.uco.cig.domain.barrio;

import com.uco.cig.domain.barrio.validator.BarrioValidator;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.zona.Zona;

import java.util.Objects;

public class Barrio {

    private static final String NOMBRE_BARRIO_REQUERIDO = "El nombre de un Barrio es requerido";
    private static final String ZONA_REQUERIDO = "El campo Zona es requerido en un barrio";

    private Integer id;
    private String nombre;
    private Zona zona;

    private Barrio(Integer id, String nombre, Zona zona) throws BusinessException {
        this.id = id;
        BarrioValidator.validarCadenaNoVacia(nombre, NOMBRE_BARRIO_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_BARRIO_REQUERIDO);
        this.zona = Objects.requireNonNull(zona, ZONA_REQUERIDO);
    }

    public static Barrio nuevo(String nombre, Zona zona) throws BusinessException {
        return new Barrio(null, nombre, zona);
    }

    public static Barrio construir(Integer id, String nombre, Zona zona) throws BusinessException {
        return new Barrio(id, nombre,zona);
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
