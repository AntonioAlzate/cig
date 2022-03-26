package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CuentaCliente")
public class CuentaClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cupo", nullable = false)
    private BigDecimal cupo;

    @Column(name = "saldoDeuda", nullable = false)
    private BigDecimal saldoDeuda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoCuentaCliente", nullable = false)
    private EstadoCuentaClienteEntity idEstadoCuentaClienteEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDetalleCuentaFavor", nullable = false)
    private DetalleCuentaFavorEntity idDetalleCuentaFavor;

    public DetalleCuentaFavorEntity getIdDetalleCuentaFavor() {
        return idDetalleCuentaFavor;
    }

    public void setIdDetalleCuentaFavor(DetalleCuentaFavorEntity idDetalleCuentaFavor) {
        this.idDetalleCuentaFavor = idDetalleCuentaFavor;
    }

    public EstadoCuentaClienteEntity getIdEstadoCuentaCliente() {
        return idEstadoCuentaClienteEntity;
    }

    public void setIdEstadoCuentaCliente(EstadoCuentaClienteEntity idEstadoCuentaClienteEntity) {
        this.idEstadoCuentaClienteEntity = idEstadoCuentaClienteEntity;
    }

    public BigDecimal getSaldoDeuda() {
        return saldoDeuda;
    }

    public void setSaldoDeuda(BigDecimal saldoDeuda) {
        this.saldoDeuda = saldoDeuda;
    }

    public BigDecimal getCupo() {
        return cupo;
    }

    public void setCupo(BigDecimal cupo) {
        this.cupo = cupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}