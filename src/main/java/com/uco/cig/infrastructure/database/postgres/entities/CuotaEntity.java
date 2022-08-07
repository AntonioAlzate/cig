package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "cuota")
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
    private LocalDate fechaPropuesta;

    @Column(name = "fechaRealizacion")
    private OffsetDateTime fechaRealizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idVenta", nullable = false)
    private VentaEntity idVentaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador")
    private TrabajadorEntity idTrabajadorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTipoCobro", nullable = false)
    private TipoCobroEntity idTipoCobroEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoCuota", nullable = false)
    private EstadoCuotaEntity idEstadoCuotaEntity;

    public CuotaEntity() {
    }

    public CuotaEntity(Integer id, BigDecimal valorCobro, BigDecimal resta, LocalDate fechaPropuesta, OffsetDateTime fechaRealizacion, VentaEntity idVentaEntity, TrabajadorEntity idTrabajadorEntity, TipoCobroEntity idTipoCobroEntity, EstadoCuotaEntity idEstadoCuotaEntity) {
        this.id = id;
        this.valorCobro = valorCobro;
        this.resta = resta;
        this.fechaPropuesta = fechaPropuesta;
        this.fechaRealizacion = fechaRealizacion;
        this.idVentaEntity = idVentaEntity;
        this.idTrabajadorEntity = idTrabajadorEntity;
        this.idTipoCobroEntity = idTipoCobroEntity;
        this.idEstadoCuotaEntity = idEstadoCuotaEntity;
    }

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

    public LocalDate getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(LocalDate fechaPropuesta) {
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