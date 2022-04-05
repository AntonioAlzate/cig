package com.uco.cig.domain.cuentacliente;

import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;

import java.math.BigDecimal;

public class CuentaCliente {

    private Integer id;
    private BigDecimal cupo;
    private BigDecimal saldoDeuda;
    private EstadoCuentaCliente estadoCuentaCliente;
    private DetalleCuentaFavor detalleCuentaFavor;

    public CuentaCliente(Integer id, BigDecimal cupo, BigDecimal saldoDeuda, EstadoCuentaCliente estadoCuentaCliente, DetalleCuentaFavor detalleCuentaFavor) {
        this.id = id;
        this.cupo = cupo;
        this.saldoDeuda = saldoDeuda;
        this.estadoCuentaCliente = estadoCuentaCliente;
        this.detalleCuentaFavor = detalleCuentaFavor;
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
