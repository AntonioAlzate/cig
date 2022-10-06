package com.uco.cig.domain.cuentacliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;

import java.math.BigDecimal;
import java.util.Objects;

public class CuentaCliente {

    private static final String VALOR_NEGATIVO = "El campo no puede contener un valor negativo";
    private static final String CUPO_REQUERIDO = "El campo cupo en una cuenta de cliente es requerido";
    private static final String SALDO_REQUERIDO = "El campo saldo deuda en una cuenta de cliente es requerido";
    private static final String ESTADO_CUENTA_REQUERIDO = "El campo estado cuenta en una cuenta de cliente es requerido";
    private static final String DETALLE_CUENTA_REQUERIDO = "El campo detalle cuenta en una cuenta de cliente es requerido";

    private Integer id;
    private BigDecimal cupo;
    private BigDecimal saldoDeuda;
    private EstadoCuentaCliente estadoCuentaCliente;
    private DetalleCuentaFavor detalleCuentaFavor;

    private CuentaCliente(Integer id, BigDecimal cupo, BigDecimal saldoDeuda, EstadoCuentaCliente estadoCuentaCliente, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorIgualCero(cupo, VALOR_NEGATIVO);
        this.cupo = Objects.requireNonNull(cupo, CUPO_REQUERIDO);
        ComunValidator.validarNumeroMayorIgualCero(saldoDeuda, VALOR_NEGATIVO);
        this.saldoDeuda = Objects.requireNonNull(saldoDeuda, SALDO_REQUERIDO);
        this.estadoCuentaCliente = Objects.requireNonNull(estadoCuentaCliente, ESTADO_CUENTA_REQUERIDO);
        this.detalleCuentaFavor = Objects.requireNonNull(detalleCuentaFavor, DETALLE_CUENTA_REQUERIDO);
    }

    public static CuentaCliente nuevo(BigDecimal cupo, EstadoCuentaCliente estadoCuentaCliente) throws BusinessException {
        DetalleCuentaFavor detalleCuentaFavor = DetalleCuentaFavor.nuevo();
        return new CuentaCliente(null, cupo, BigDecimal.ZERO, estadoCuentaCliente, detalleCuentaFavor);
    }

    public static CuentaCliente construir(Integer id, BigDecimal cupo, BigDecimal saldoDeuda, EstadoCuentaCliente estadoCuentaCliente, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        return new CuentaCliente(id, cupo, saldoDeuda, estadoCuentaCliente, detalleCuentaFavor);
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getCupo() {
        return cupo;
    }

    public BigDecimal getSaldoDeuda() {
        return saldoDeuda;
    }

    public EstadoCuentaCliente getEstadoCuentaCliente() {
        return estadoCuentaCliente;
    }

    public DetalleCuentaFavor getDetalleCuentaFavor() {
        return detalleCuentaFavor;
    }

    public void setCupo(BigDecimal cupo) throws BusinessException {
        ComunValidator.validarNumeroMayorIgualCero(cupo, VALOR_NEGATIVO);
        this.cupo = Objects.requireNonNull(cupo, CUPO_REQUERIDO);
    }

    public void setSaldoDeuda(BigDecimal saldoDeuda) throws BusinessException {
        ComunValidator.validarNumeroMayorIgualCero(saldoDeuda, VALOR_NEGATIVO);
        this.saldoDeuda = Objects.requireNonNull(saldoDeuda, SALDO_REQUERIDO);
    }
}
