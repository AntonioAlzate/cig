package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "modalidad")
public class ModalidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    public ModalidadEntity() {
    }

    public ModalidadEntity(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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