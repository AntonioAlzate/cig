package com.uco.cig.domain.cliente;

import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.persona.Persona;

public class Cliente {

    private Integer id;
    private Persona persona;
    private CuentaCliente cuentaCliente;
    private Estado estado;

    public Cliente(Integer id, Persona persona, CuentaCliente cuentaCliente, Estado estado) {
        this.id = id;
        this.persona = persona;
        this.cuentaCliente = cuentaCliente;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public CuentaCliente getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
