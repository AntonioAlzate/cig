package com.uco.cig.domain.region;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.region.validator.RegionValidator;

import java.util.Objects;

public class Region {

    private static final String NOMBRE_REGION_REQUERIDO = "El nombre de una Región es requerido";
    private static final String DEPARTAMENTO_REQUERIDO = "El campo departamento en una región es requerido";

    private Integer id;
    private String nombre;
    private Departamento departamento;

    private Region(Integer id, String nombre, Departamento departamento) throws BusinessException {
        this.id = id;
        RegionValidator.validarCadenaNoVacia(nombre, NOMBRE_REGION_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REGION_REQUERIDO);
        this.departamento = Objects.requireNonNull(departamento, DEPARTAMENTO_REQUERIDO);
    }

    public static Region nuevo(String nombre, Departamento departamento) throws BusinessException {
        return new Region(null, nombre, departamento);
    }

    public static Region construir(Integer id, String nombre, Departamento departamento) throws BusinessException {
        return new Region(id,nombre,departamento);
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
