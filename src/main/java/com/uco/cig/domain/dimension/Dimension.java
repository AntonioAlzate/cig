package com.uco.cig.domain.dimension;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;
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

    private Categoria categoria;

    private Dimension(Integer id, BigDecimal largo, BigDecimal ancho, Categoria categoria) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorCero(largo, VALOR_DE_MEDIDA_NEGATIVO);
        this.largo = Objects.requireNonNull(largo, LARGO_REQUERIDO);
        ComunValidator.validarNumeroMayorCero(ancho, VALOR_DE_MEDIDA_NEGATIVO);
        this.ancho = Objects.requireNonNull(ancho, ANCHO_REQUERIDO);
        this.categoria = Objects.requireNonNull(categoria);
    }

    public static Dimension nuevo(BigDecimal largo, BigDecimal ancho, Categoria categoria) throws BusinessException {
        return new Dimension(null, largo, ancho, categoria);
    }

    public static Dimension construir(Integer id, BigDecimal largo, BigDecimal ancho, Categoria categoria) throws BusinessException {
        return new Dimension(id, largo, ancho, categoria);
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
