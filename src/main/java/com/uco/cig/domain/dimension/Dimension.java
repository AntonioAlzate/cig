package com.uco.cig.domain.dimension;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class Dimension {

    private static final String VALOR_DE_MEDIDA_NEGATIVO = "El Largo o ancho de un producto debe ser mayor a cero";
    private static final String LARGO_REQUERIDO = "El Largo de un producto es requerido";
    private static final String ANCHO_REQUERIDO = "El Ancho de un producto es requerido";

    private Integer id;
    private BigDecimal largo;
    private BigDecimal ancho;

    private Dimension(Integer id, BigDecimal largo, BigDecimal ancho) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorCero(largo, VALOR_DE_MEDIDA_NEGATIVO);
        this.largo = Objects.requireNonNull(largo, LARGO_REQUERIDO);
        ComunValidator.validarNumeroMayorCero(ancho, VALOR_DE_MEDIDA_NEGATIVO);
        this.ancho = Objects.requireNonNull(ancho, ANCHO_REQUERIDO);
    }

    public static Dimension nuevo(BigDecimal largo, BigDecimal ancho) throws BusinessException {
        return new Dimension(null, largo, ancho);
    }

    public static Dimension construir(Integer id, BigDecimal largo, BigDecimal ancho) throws BusinessException {
        return new Dimension(id, largo, ancho);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }
}
