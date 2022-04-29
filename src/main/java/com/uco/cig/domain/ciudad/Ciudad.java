package com.uco.cig.domain.ciudad;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.validator.CiudadValidator;
import com.uco.cig.domain.region.Region;

import java.util.Objects;

public class Ciudad {

    private static final String NOMBRE_CIUDAD_REQUERIDO = "El nombre de una Ciudad es requerido";
    private static final String REGION_REQUERIDO = "El campo región es requerido en una ciudad";

    private Integer id;
    private String nombre;
    private Region region;

    private Ciudad(Integer id, String nombre, Region region) throws BusinessException {
        this.id = id;
        CiudadValidator.validarCadenaNoVacia(nombre, NOMBRE_CIUDAD_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_CIUDAD_REQUERIDO);
        this.region = Objects.requireNonNull(region, REGION_REQUERIDO);
    }

    public static Ciudad nuevo(String nombre, Region region) throws BusinessException {
        return new Ciudad(null, nombre, region);
    }

    public static Ciudad construir(Integer id, String nombre, Region region) throws BusinessException {
        return new Ciudad(id, nombre, region);
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
