package com.uco.cig.domain.formapago;


import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class FormaPago {

    private static final String NOMBRE_REQUERIDO = "El nombre de una forma de pago es requerido";
    private static final String NUMERO_DIAS_REQUERIDO = "El número de días de una forma de pago es requerido";
    private static final String VALOR_MINIMO_INCORRECTO = "El valor minimo de una forma de pago no puede ser igual o inferior a cero";

    private Integer id;
    private String nombre;
    private Integer numeroDias;
    private BigDecimal valorMinimo;

    private FormaPago(Integer id, String nombre, Integer numeroDias, BigDecimal valorMinimo) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REQUERIDO);
        this.numeroDias = Objects.requireNonNull(numeroDias, NUMERO_DIAS_REQUERIDO);
        ComunValidator.validarNumeroMayorCero(valorMinimo, VALOR_MINIMO_INCORRECTO);
        this.valorMinimo = Objects.requireNonNull(valorMinimo);
    }

    public static FormaPago nuevo(String nombre, Integer numeroDias, BigDecimal valorMinimo) throws BusinessException {
        return new FormaPago(null, nombre, numeroDias, valorMinimo);
    }

    public static FormaPago construir(Integer id, String nombre, Integer numeroDias, BigDecimal valorMinimo) throws BusinessException {
        return new FormaPago(id, nombre, numeroDias, valorMinimo);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }
}
