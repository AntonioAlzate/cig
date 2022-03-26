package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Abono")
public class AbonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "descripcion", nullable = false, length = 350)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCuentaTrabajador", nullable = false)
    private CuentaTrabajadorEntity idCuentaTrabajadorEntity;

    public CuentaTrabajadorEntity getIdCuentaTrabajador() {
        return idCuentaTrabajadorEntity;
    }

    public void setIdCuentaTrabajador(CuentaTrabajadorEntity idCuentaTrabajadorEntity) {
        this.idCuentaTrabajadorEntity = idCuentaTrabajadorEntity;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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