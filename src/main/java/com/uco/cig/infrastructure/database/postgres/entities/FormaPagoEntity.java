package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "forma_pago")
public class FormaPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "numeroDias", nullable = false)
    private Integer numeroDias;

    @Column(name = "valorMinimo", nullable = false)
    private BigDecimal valorMinimo;

    public FormaPagoEntity() {
    }

    public FormaPagoEntity(Integer id, String nombre, Integer numeroDias, BigDecimal valorMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.numeroDias = numeroDias;
        this.valorMinimo = valorMinimo;
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

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
}