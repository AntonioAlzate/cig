package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "valorTotal", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "descuentoAdicional", nullable = false)
    private BigDecimal descuentoAdicional;

    @Column(name = "justificacion", nullable = false, length = 350)
    private String justificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idVenta", nullable = false)
    private VentaEntity idVentaEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity idProductoEntity;

    public DetalleVentaEntity() {
    }

    public DetalleVentaEntity(Integer id, Integer cantidad, BigDecimal valorTotal, BigDecimal descuentoAdicional, String justificacion, VentaEntity idVentaEntity, ProductoEntity idProductoEntity) {
        this.id = id;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.descuentoAdicional = descuentoAdicional;
        this.justificacion = justificacion;
        this.idVentaEntity = idVentaEntity;
        this.idProductoEntity = idProductoEntity;
    }

    public ProductoEntity getIdProducto() {
        return idProductoEntity;
    }

    public void setIdProducto(ProductoEntity idProductoEntity) {
        this.idProductoEntity = idProductoEntity;
    }

    public VentaEntity getIdVenta() {
        return idVentaEntity;
    }

    public void setIdVenta(VentaEntity idVentaEntity) {
        this.idVentaEntity = idVentaEntity;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public BigDecimal getDescuentoAdicional() {
        return descuentoAdicional;
    }

    public void setDescuentoAdicional(BigDecimal descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}