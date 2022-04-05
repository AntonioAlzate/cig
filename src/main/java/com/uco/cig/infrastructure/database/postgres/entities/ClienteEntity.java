package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPersona", nullable = false)
    private PersonaEntity idPersonaEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCuentaCliente", nullable = false)
    private CuentaClienteEntity idCuentaClienteEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdEstado", nullable = false)
    private EstadoEntity idEstadoEntity;

    public ClienteEntity() {
    }

    public ClienteEntity(Integer id, PersonaEntity idPersonaEntity, CuentaClienteEntity idCuentaClienteEntity, EstadoEntity idEstadoEntity) {
        this.id = id;
        this.idPersonaEntity = idPersonaEntity;
        this.idCuentaClienteEntity = idCuentaClienteEntity;
        this.idEstadoEntity = idEstadoEntity;
    }

    public EstadoEntity getIdEstado() {
        return idEstadoEntity;
    }

    public void setIdEstado(EstadoEntity idEstadoEntity) {
        this.idEstadoEntity = idEstadoEntity;
    }

    public CuentaClienteEntity getIdCuentaCliente() {
        return idCuentaClienteEntity;
    }

    public void setIdCuentaCliente(CuentaClienteEntity idCuentaClienteEntity) {
        this.idCuentaClienteEntity = idCuentaClienteEntity;
    }

    public PersonaEntity getIdPersona() {
        return idPersonaEntity;
    }

    public void setIdPersona(PersonaEntity idPersonaEntity) {
        this.idPersonaEntity = idPersonaEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}