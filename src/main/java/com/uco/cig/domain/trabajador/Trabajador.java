package com.uco.cig.domain.trabajador;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.persona.Persona;

import java.util.Objects;

public class Trabajador {

    private static final String NOMBRE_USUARIO_REQUERIDO = "El Nombre de usuario es requerido";
    private static final String LONGITUD_INCORRECTA = "La longitud de un nombre de usuario debe ser mayor a 4 y menor a 16 caracteres";
    private static final String FORMATO_USERNAME_INCORRECTO = "El formato del nombre de usuario debe ser de una sola palabra";
    private static final String  FORMATO_PASSWORD_INCORRECTO = "La contraseña debe tener minimo 8 caracteres, empezar y terminar por una letra, contener: 1 mayuscula, 1 minuscula, 1 número, 1 caracter especial";
    private static final String DATOS_PERSONA_REQUERIDOS = "Los datos de una persona son requeridos para crear un trabajador";
    private static final String ESTADO_REQUERODO = "El estado es requerido para crear un trabajador";

    private Integer id;
    private Persona persona;
    private Estado estado;

    public Trabajador(){

    }

    private Trabajador(Integer id, Persona persona, Estado estado) throws BusinessException {
        this.id = id;
        this.persona = Objects.requireNonNull(persona, DATOS_PERSONA_REQUERIDOS);
        this.estado = Objects.requireNonNull(estado, ESTADO_REQUERODO);
    }

    public static Trabajador construir(Integer id, Persona persona, Estado estado) throws BusinessException {
        return new Trabajador(id, persona, estado);
    }

    public static Trabajador nuevo(String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio, Estado estado) throws BusinessException {
        Persona persona = Persona.nuevo(identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, barrio);
        return new Trabajador(null, persona, estado);
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
