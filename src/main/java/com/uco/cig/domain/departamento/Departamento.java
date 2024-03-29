package com.uco.cig.domain.departamento;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.pais.Pais;

import java.util.Objects;

public class Departamento {

    private static final String NOMBRE_DEPARTAMENTO_REQUERIDO = "El nombre de un Departamento es requerido";
    private static final String PAIS_REQUERIDO = "El campo País es requerido en un departamento";


    private Integer id;
    private String nombre;
    private Pais pais;

    private Departamento(Integer id, String nombre, Pais pais) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_DEPARTAMENTO_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre,NOMBRE_DEPARTAMENTO_REQUERIDO);
        this.pais = Objects.requireNonNull(pais, PAIS_REQUERIDO);
    }

    public static Departamento nuevo(String nombre, Pais pais) throws BusinessException {
        return new Departamento(null, nombre, pais);
    }

    public static Departamento construir(Integer id, String nombre, Pais pais) throws BusinessException {
        return new Departamento(id, nombre, pais);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Pais getPais() {
        return pais;
    }
}
