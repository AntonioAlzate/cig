package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SalidaCuentaFavor")
public class SalidaCuentaFavorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDetalleCuentaFavor", nullable = false)
    private DetalleCuentaFavorEntity idDetalleCuentaFavorEntity;

    public DetalleCuentaFavorEntity getIdDetalleCuentaFavor() {
        return idDetalleCuentaFavorEntity;
    }

    public void setIdDetalleCuentaFavor(DetalleCuentaFavorEntity idDetalleCuentaFavorEntity) {
        this.idDetalleCuentaFavorEntity = idDetalleCuentaFavorEntity;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}