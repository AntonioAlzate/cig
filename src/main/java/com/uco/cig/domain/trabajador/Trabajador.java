package com.uco.cig.domain.trabajador;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.persona.Persona;

import java.util.Objects;

public class Trabajador {

    private static final String DATOS_PERSONA_REQUERIDOS = "Los datos de una persona son requeridos para crear un trabajador";
    private static final String ESTADO_REQUERODO = "El estado es requerido para crear un trabajador";
    private static final String FORMATO_CORREO_NO_VALIDO = "El formato de correo electronico ingresado no es valido";
    private static final String CORREO_REQUERIDO = "El correo de un trabajador es requerido";

    private Integer id;
    private Persona persona;
    private Estado estado;
    private String correo;

    public Trabajador(){

    }

    private Trabajador(Integer id, Persona persona, Estado estado, String correo) throws BusinessException {
        this.id = id;
        this.persona = Objects.requireNonNull(persona, DATOS_PERSONA_REQUERIDOS);
        this.estado = Objects.requireNonNull(estado, ESTADO_REQUERODO);
        ComunValidator.validarFormatoCorreo(correo, FORMATO_CORREO_NO_VALIDO);
        this.correo = Objects.requireNonNull(correo, CORREO_REQUERIDO);
    }

    public static Trabajador construir(Integer id, Persona persona, Estado estado, String correo) throws BusinessException {
        return new Trabajador(id, persona, estado, correo);
    }

    public static Trabajador nuevo(String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio, Estado estado, String correo) throws BusinessException {
        Persona persona = Persona.nuevo(identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, barrio);
        return new Trabajador(null, persona, estado, correo);
    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws BusinessException {
        ComunValidator.validarFormatoCorreo(correo, FORMATO_CORREO_NO_VALIDO);
        this.correo = Objects.requireNonNull(correo, CORREO_REQUERIDO);
    }
}
