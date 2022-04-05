package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "detalle_despacho")
public class DetalleDespachoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cantidadInicial", nullable = false)
    private Integer cantidadInicial;

    @Column(name = "cantidadEntregar", nullable = false)
    private Integer cantidadEntregar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRegistroDespacho", nullable = false)
    private RegistroDespachoEntity idRegistroDespachoEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoDespacho", nullable = false)
    private EstadoDespachoEntity idEstadoDespachoEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity idProductoEntity;

    public DetalleDespachoEntity() {
    }

    public DetalleDespachoEntity(Integer id, Integer cantidadInicial, Integer cantidadEntregar, RegistroDespachoEntity idRegistroDespachoEntity, EstadoDespachoEntity idEstadoDespachoEntity, ProductoEntity idProductoEntity) {
        this.id = id;
        this.cantidadInicial = cantidadInicial;
        this.cantidadEntregar = cantidadEntregar;
        this.idRegistroDespachoEntity = idRegistroDespachoEntity;
        this.idEstadoDespachoEntity = idEstadoDespachoEntity;
        this.idProductoEntity = idProductoEntity;
    }

    public ProductoEntity getIdProducto() {
        return idProductoEntity;
    }

    public void setIdProducto(ProductoEntity idProductoEntity) {
        this.idProductoEntity = idProductoEntity;
    }

    public EstadoDespachoEntity getIdEstadoDespacho() {
        return idEstadoDespachoEntity;
    }

    public void setIdEstadoDespacho(EstadoDespachoEntity idEstadoDespachoEntity) {
        this.idEstadoDespachoEntity = idEstadoDespachoEntity;
    }

    public RegistroDespachoEntity getIdRegistroDespacho() {
        return idRegistroDespachoEntity;
    }

    public void setIdRegistroDespacho(RegistroDespachoEntity idRegistroDespachoEntity) {
        this.idRegistroDespachoEntity = idRegistroDespachoEntity;
    }

    public Integer getCantidadEntregar() {
        return cantidadEntregar;
    }

    public void setCantidadEntregar(Integer cantidadEntregar) {
        this.cantidadEntregar = cantidadEntregar;
    }

    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}