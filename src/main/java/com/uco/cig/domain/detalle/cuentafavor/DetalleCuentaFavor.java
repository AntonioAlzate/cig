package com.uco.cig.domain.detalle.cuentafavor;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class DetalleCuentaFavor {

    private static final String VALOR_NEGATIVO = "El valor de una cuenta a favor no puede ser negativo";
    private static final String VALOR_REQUERIDO = "El valor de un detalle es requerido";

    private Integer id;
    private BigDecimal valor;

    private DetalleCuentaFavor(Integer id, BigDecimal valor) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorIgualCero(valor, VALOR_NEGATIVO);
        this.valor = Objects.requireNonNull(valor, VALOR_REQUERIDO);
    }

    public static DetalleCuentaFavor nuevo() throws BusinessException {
        return new DetalleCuentaFavor(null, BigDecimal.ZERO);
    }

    public static DetalleCuentaFavor construir(Integer id, BigDecimal valor) throws BusinessException {
        return new DetalleCuentaFavor(id, valor);
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) throws BusinessException {
        ComunValidator.validarNumeroMayorIgualCero(valor, VALOR_NEGATIVO);
        this.valor = Objects.requireNonNull(valor, VALOR_REQUERIDO);
    }
}
