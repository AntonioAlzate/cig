package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;

    @Column(name = "referencia", nullable = false, length = 25)
    private String referencia;

    @Column(name = "descripcion", nullable = false, length = 350)
    private String descripcion;

    @Column(name = "cantidadExistente", nullable = false)
    private Integer cantidadExistente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstado", nullable = false)
    private EstadoEntity idEstado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDimension", nullable = false)
    private DimensionEntity idDimension;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaEntity idCategoria;

    public ProductoEntity() {
    }

    public ProductoEntity(Integer id, String nombre, String referencia, String descripcion, EstadoEntity idEstado, DimensionEntity idDimension, CategoriaEntity idCategoria, Integer cantidadExistente) {
        this.id = id;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.idDimension = idDimension;
        this.idCategoria = idCategoria;
        this.cantidadExistente = cantidadExistente;
    }

    public CategoriaEntity getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaEntity idCategoria) {
        this.idCategoria = idCategoria;
    }

    public DimensionEntity getIdDimension() {
        return idDimension;
    }

    public void setIdDimension(DimensionEntity idDimension) {
        this.idDimension = idDimension;
    }

    public EstadoEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoEntity idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(Integer cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }
}