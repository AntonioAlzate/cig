package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "inventario")
public class InventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity idProductoEntity;

    public InventarioEntity() {
    }

    public InventarioEntity(Integer id, Integer cantidad, ProductoEntity idProductoEntity) {
        this.id = id;
        this.cantidad = cantidad;
        this.idProductoEntity = idProductoEntity;
    }

    public ProductoEntity getIdProducto() {
        return idProductoEntity;
    }

    public void setIdProducto(ProductoEntity idProductoEntity) {
        this.idProductoEntity = idProductoEntity;
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