package com.uco.cig.domain.formapago;


import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.util.Objects;

public class FormaPago {

    private static final String NOMBRE_REQUERIDO = "El nombre de una forma de pago es requerido";

    private Integer id;
    private String nombre;

    private FormaPago(Integer id, String nombre) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre);
    }

    public static FormaPago nuevo(String nombre) throws BusinessException {
        return new FormaPago(null, nombre);
    }

    public static FormaPago Construir(Integer id, String nombre) throws BusinessException {
        return new FormaPago(id, nombre);
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
