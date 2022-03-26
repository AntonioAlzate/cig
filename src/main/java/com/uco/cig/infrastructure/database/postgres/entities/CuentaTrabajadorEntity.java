package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CuentaTrabajador")
public class CuentaTrabajadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private TrabajadorEntity idTrabajador;

    public TrabajadorEntity getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(TrabajadorEntity idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}