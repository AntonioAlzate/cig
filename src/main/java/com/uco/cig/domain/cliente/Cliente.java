package com.uco.cig.domain.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.persona.Persona;

import java.math.BigDecimal;
import java.util.Objects;

public class Cliente {

    private Integer id;
    private Persona persona;
    private CuentaCliente cuentaCliente;
    private Estado estado;

    private static final String CAMPO_REQUERIDO = "El campo es requerido para poder crear un cliente";

    public Cliente(){

    }

    private Cliente(Integer id, Persona persona, CuentaCliente cuentaCliente, Estado estado) {
        this.id = id;
        this.persona = Objects.requireNonNull(persona, CAMPO_REQUERIDO);
        this.cuentaCliente = Objects.requireNonNull(cuentaCliente, CAMPO_REQUERIDO);
        this.estado = Objects.requireNonNull(estado, CAMPO_REQUERIDO);
    }

    public static Cliente nuevo(Persona persona, CuentaCliente cuentaCliente, Estado estado) {
        return new Cliente(null, persona, cuentaCliente, estado);
    }

    public static Cliente construir(Integer id, Persona persona, CuentaCliente cuentaCliente, Estado estado) {
        return new Cliente(id, persona, cuentaCliente, estado);
    }

    public static Cliente nuevo(String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio, BigDecimal cupo, EstadoCuentaCliente estadoCuentaCliente, Estado estado) throws BusinessException {
        Persona persona = Persona.nuevo(identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, barrio);
        CuentaCliente cuentaCliente = CuentaCliente.nuevo(cupo, estadoCuentaCliente);
        return new Cliente(null, persona, cuentaCliente, estado);
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
