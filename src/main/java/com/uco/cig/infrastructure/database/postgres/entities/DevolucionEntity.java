package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "devolucion")
public class DevolucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "justificacion", nullable = false, length = 350)
    private String justificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private TrabajadorEntity idTrabajadorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDetalleVenta", nullable = false)
    private DetalleVentaEntity idDetalleVentaEntity;

    public DevolucionEntity() {
    }

    public DevolucionEntity(Integer id, OffsetDateTime fecha, BigDecimal valor, Integer cantidad, String justificacion, TrabajadorEntity idTrabajadorEntity, DetalleVentaEntity idDetalleVentaEntity) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
        this.cantidad = cantidad;
        this.justificacion = justificacion;
        this.idTrabajadorEntity = idTrabajadorEntity;
        this.idDetalleVentaEntity = idDetalleVentaEntity;
    }

    public DetalleVentaEntity getIdDetalleVenta() {
        return idDetalleVentaEntity;
    }

    public void setIdDetalleVenta(DetalleVentaEntity idDetalleVentaEntity) {
        this.idDetalleVentaEntity = idDetalleVentaEntity;
    }

    public TrabajadorEntity getIdTrabajador() {
        return idTrabajadorEntity;
    }

    public void setIdTrabajador(TrabajadorEntity idTrabajadorEntity) {
        this.idTrabajadorEntity = idTrabajadorEntity;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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