package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_cuenta_favor")
public class DetalleCuentaFavorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    public DetalleCuentaFavorEntity() {
    }

    public DetalleCuentaFavorEntity(Integer id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}