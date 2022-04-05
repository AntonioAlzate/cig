package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "estado_cuenta_cliente")
public class EstadoCuentaClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = 25)
    private String estado;

    public EstadoCuentaClienteEntity() {
    }

    public EstadoCuentaClienteEntity(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}