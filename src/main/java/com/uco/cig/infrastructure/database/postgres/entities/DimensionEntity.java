package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dimension")
public class DimensionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "largo", nullable = false, precision = 5, scale = 2)
    private BigDecimal largo;

    @Column(name = "ancho", nullable = false, precision = 5, scale = 2)
    private BigDecimal ancho;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaEntity idCategoriaEntity;

    public DimensionEntity() {
    }

    public DimensionEntity(Integer id, BigDecimal largo, BigDecimal ancho, CategoriaEntity idCategoriaEntity) {
        this.id = id;
        this.largo = largo;
        this.ancho = ancho;
        this.idCategoriaEntity = idCategoriaEntity;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoriaEntity getIdCategoriaEntity() {
        return idCategoriaEntity;
    }

    public void setIdCategoriaEntity(CategoriaEntity idCategoriaEntity) {
        this.idCategoriaEntity = idCategoriaEntity;
    }
}