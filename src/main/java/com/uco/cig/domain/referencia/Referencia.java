package com.uco.cig.domain.referencia;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.persona.validator.PersonaValidator;

import java.util.Objects;

public class Referencia {

    private static final String NOMBRE_REQUERIDO = "El nombre de una referencia es requerido";
    private static final String TELEFONO_REQUERIDO = "El telefono de una referencia es requerido";
    private static final String CLIENTE_REQUERIDO = "El cliente paras una referencia es requerido";
    private static final String PARENTESCO_REQUERIDO = "El parentesco de una referencia con el cliente es requerida";
    private static final String TELEFONO_INCORRECTO = "El formato o la longitud del telefono de la referencia es incorrecto.";
    private Integer id;
    private String nombre;
    private String telefono;
    private Cliente cliente;
    private Parentesco parentesco;


    private Referencia(Integer id, String nombre, String telefono, Cliente cliente, Parentesco parentesco) throws BusinessException {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REQUERIDO);
        PersonaValidator.validarLongitudTelefono(telefono, TELEFONO_INCORRECTO);
        this.telefono = Objects.requireNonNull(telefono, TELEFONO_REQUERIDO);
        this.cliente = Objects.requireNonNull(cliente, CLIENTE_REQUERIDO);
        this.parentesco = Objects.requireNonNull(parentesco, PARENTESCO_REQUERIDO);
    }

    public static Referencia nuevo(String nombre, String telefono, Cliente cliente, Parentesco parentesco) throws BusinessException {
        return new Referencia(null, nombre, telefono, cliente, parentesco);
    }

    public static Referencia construir(Integer id, String nombre, String telefono, Cliente cliente, Parentesco parentesco) throws BusinessException {
        return new Referencia(id, nombre, telefono, cliente, parentesco);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
}
