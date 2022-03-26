package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Liquidacion")
public class LiquidacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private TrabajadorEntity idTrabajadorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoLiquidacion", nullable = false)
    private EstadoLiquidacionEntity idEstadoLiquidacionEntity;

    public EstadoLiquidacionEntity getIdEstadoLiquidacion() {
        return idEstadoLiquidacionEntity;
    }

    public void setIdEstadoLiquidacion(EstadoLiquidacionEntity idEstadoLiquidacionEntity) {
        this.idEstadoLiquidacionEntity = idEstadoLiquidacionEntity;
    }

    public TrabajadorEntity getIdTrabajador() {
        return idTrabajadorEntity;
    }

    public void setIdTrabajador(TrabajadorEntity idTrabajadorEntity) {
        this.idTrabajadorEntity = idTrabajadorEntity;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}