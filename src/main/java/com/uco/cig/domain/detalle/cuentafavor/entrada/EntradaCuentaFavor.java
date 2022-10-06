package com.uco.cig.domain.detalle.cuentafavor.entrada;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;

import java.math.BigDecimal;
import java.util.Objects;

public class EntradaCuentaFavor {

    private static final String DESCRIPCION_REQUERIDA = "La descripción de una entrada a cuenta a favor es requerido";
    private static final String VALOR_REQUERIDA = "El valor de una entrada a cuenta a favor es requerido";
    private static final String VALOR_INVALIDO = "El valor de una entrada a cuenta a favor no puede ser menor a cero";
    private static final String DETALLE_CUENTA_FAVOR_REQUERIDA = "El detalle de cuenta a favor de una entrada a cuenta a favor es requerido";

    private Integer id;
    private String descripcion;
    private BigDecimal valor;
    private DetalleCuentaFavor detalleCuentaFavor;

    private EntradaCuentaFavor(Integer id, String descripcion, BigDecimal valor, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        this.id = id;
        this.descripcion = Objects.requireNonNull(descripcion, DESCRIPCION_REQUERIDA);
        ComunValidator.validarNumeroMayorIgualCero(valor, VALOR_INVALIDO);
        this.valor = Objects.requireNonNull(valor, VALOR_REQUERIDA);
        this.detalleCuentaFavor = Objects.requireNonNull(detalleCuentaFavor, DETALLE_CUENTA_FAVOR_REQUERIDA);
    }

    public static EntradaCuentaFavor nuevo(String descripcion, BigDecimal valor, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        return new EntradaCuentaFavor(null, descripcion, valor, detalleCuentaFavor);
    }

    public static EntradaCuentaFavor construir(Integer id, String descripcion, BigDecimal valor, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        return new EntradaCuentaFavor(id, descripcion, valor, detalleCuentaFavor);
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public DetalleCuentaFavor getDetalleCuentaFavor() {
        return detalleCuentaFavor;
    }
}
