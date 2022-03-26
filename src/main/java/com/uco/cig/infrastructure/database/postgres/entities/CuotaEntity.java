package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Cuota")
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "valorCobro", nullable = false)
    private BigDecimal valorCobro;

    @Column(name = "resta", nullable = false)
    private BigDecimal resta;

    @Column(name = "fechaPropuesta", nullable = false)
    private OffsetDateTime fechaPropuesta;

    @Column(name = "fechaRealizacion", nullable = false)
    private OffsetDateTime fechaRealizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idVenta", nullable = false)
    private VentaEntity idVentaEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private TrabajadorEntity idTrabajadorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTipoCobro", nullable = false)
    private TipoCobroEntity idTipoCobroEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoCuota", nullable = false)
    private EstadoCuotaEntity idEstadoCuotaEntity;

    public EstadoCuotaEntity getIdEstadoCuota() {
        return idEstadoCuotaEntity;
    }

    public void setIdEstadoCuota(EstadoCuotaEntity idEstadoCuotaEntity) {
        this.idEstadoCuotaEntity = idEstadoCuotaEntity;
    }

    public TipoCobroEntity getIdTipoCobro() {
        return idTipoCobroEntity;
    }

    public void setIdTipoCobro(TipoCobroEntity idTipoCobroEntity) {
        this.idTipoCobroEntity = idTipoCobroEntity;
    }

    public TrabajadorEntity getIdTrabajador() {
        return idTrabajadorEntity;
    }

    public void setIdTrabajador(TrabajadorEntity idTrabajadorEntity) {
        this.idTrabajadorEntity = idTrabajadorEntity;
    }

    public VentaEntity getIdVenta() {
        return idVentaEntity;
    }

    public void setIdVenta(VentaEntity idVentaEntity) {
        this.idVentaEntity = idVentaEntity;
    }

    public OffsetDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(OffsetDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public OffsetDateTime getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(OffsetDateTime fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public BigDecimal getResta() {
        return resta;
    }

    public void setResta(BigDecimal resta) {
        this.resta = resta;
    }

    public BigDecimal getValorCobro() {
        return valorCobro;
    }

    public void setValorCobro(BigDecimal valorCobro) {
        this.valorCobro = valorCobro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}