package com.uco.cig.domain.detalle.cuentafavor;

import javax.persistence.Column;
import java.math.BigDecimal;

public class DetalleCuentaFavor {

    private Integer id;
    private BigDecimal valor;

    public DetalleCuentaFavor(Integer id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
