package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;

@Entity
@Table(name = "Referencia")
public class ReferenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteEntity idClienteEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idParentesco", nullable = false)
    private ParentescoEntity idParentescoEntity;

    public ParentescoEntity getIdParentesco() {
        return idParentescoEntity;
    }

    public void setIdParentesco(ParentescoEntity idParentescoEntity) {
        this.idParentescoEntity = idParentescoEntity;
    }

    public ClienteEntity getIdCliente() {
        return idClienteEntity;
    }

    public void setIdCliente(ClienteEntity idClienteEntity) {
        this.idClienteEntity = idClienteEntity;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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