package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class DepartamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPais", nullable = false)
    private PaisEntity idPais;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer id, String nombre, PaisEntity idPais) {
        this.id = id;
        this.nombre = nombre;
        this.idPais = idPais;
    }

    public PaisEntity getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisEntity idPais) {
        this.idPais = idPais;
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
}