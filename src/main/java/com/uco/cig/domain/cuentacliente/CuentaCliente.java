package com.uco.cig.domain.cuentacliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuentacliente.validator.CuentaClienteValidator;
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
        CuentaClienteValidator.validarNumeroMayorCero(cupo, VALOR_NEGATIVO);
        this.cupo = Objects.requireNonNull(cupo, CUPO_REQUERIDO);
        CuentaClienteValidator.validarNumeroMayorCero(saldoDeuda, VALOR_NEGATIVO);
        this.saldoDeuda = Objects.requireNonNull(saldoDeuda, SALDO_REQUERIDO);
        this.estadoCuentaCliente = Objects.requireNonNull(estadoCuentaCliente, ESTADO_CUENTA_REQUERIDO);;
        this.detalleCuentaFavor = Objects.requireNonNull(detalleCuentaFavor, DETALLE_CUENTA_REQUERIDO);;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCupo() {
        return cupo;
    }

    public void setCupo(BigDecimal cupo) {
        this.cupo = cupo;
    }

    public BigDecimal getSaldoDeuda() {
        return saldoDeuda;
    }

    public void setSaldoDeuda(BigDecimal saldoDeuda) {
        this.saldoDeuda = saldoDeuda;
    }

    public EstadoCuentaCliente getEstadoCuentaCliente() {
        return estadoCuentaCliente;
    }

    public void setEstadoCuentaCliente(EstadoCuentaCliente estadoCuentaCliente) {
        this.estadoCuentaCliente = estadoCuentaCliente;
    }

    public DetalleCuentaFavor getDetalleCuentaFavor() {
        return detalleCuentaFavor;
    }

    public void setDetalleCuentaFavor(DetalleCuentaFavor detalleCuentaFavor) {
        this.detalleCuentaFavor = detalleCuentaFavor;
    }
}
