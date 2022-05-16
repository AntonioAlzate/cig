package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "trabajador")
public class TrabajadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPersona", nullable = false)
    private PersonaEntity idPersona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstado", nullable = false)
    private EstadoEntity idEstadoEntity;

    public TrabajadorEntity() {
    }

    public TrabajadorEntity(Integer id, PersonaEntity idPersona, EstadoEntity idEstadoEntity) {
        this.id = id;
        this.idPersona = idPersona;
        this.idEstadoEntity = idEstadoEntity;
    }

    public EstadoEntity getIdEstado() {
        return idEstadoEntity;
    }

    public void setIdEstado(EstadoEntity idEstadoEntity) {
        this.idEstadoEntity = idEstadoEntity;
    }

    public PersonaEntity getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonaEntity idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}