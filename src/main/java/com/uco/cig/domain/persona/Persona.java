package com.uco.cig.domain.persona;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.persona.validator.PersonaValidator;

import java.util.Objects;

public class Persona {

    private static final String FORMATO_INCORRECTO_IDENTIFICACION = "Se han introducido caracteres no validos para un número de identificación";
    private static final String LONGITUD_IDENTIFICACION_NO_VALIDA = "El numero de identificación de un ciudadano debe tener mínimo 4 digitos y máximo 10";
    private static final String IDENTIFICACION_REQUERIDA = "El número de identificación de una persona es requerido";
    private static final String FORMATO_PRIMER_NOMBRE_INCORRECTO = "El primer nombre debe ser una sola palabra y sin espacios intermedios";
    private static final String PRIMER_NOMBRE_REQUERIDO = "El primer nombre de una persona es requerido";
    private static final String FORMATO_SEGUNDO_NOMBRE_INCORRECTO = "El segundo nombre debe ser una sola palabra y sin espacios intermedios";
    private static final String FORMATO_PRIMER_APELLIDO_INCORRECTO = "El primer apellido debe ser una sola palabra y sin espacios intermedios";
    private static final String PRIMER_APELLIDO_REQUERIDO = "El primer apellido de una persona es requerido";
    private static final String FORMATO_SEGUNDO_APELLIDO_INCORRECTO = "El segundo apellido debe ser una sola palabra y sin espacios intermedios";
    private static final String SEGUNDO_APELLIDO_REQUERIDO = "El segundo apellido de una persona es requerido";
    private static final String DIRECCION_REQUERIDA = "La dirección de una persona es requerida";
    private static final String TELEFONO_REQUERIDO = "El telefono de una persona es requerido";
    private static final String LONGITUD_TELEFONO_NO_VALIDA = "La longitud del telefono de una persona debe ser de 10 digitos";
    private static final String TELEFONO_FORMATO_INCORRECTO = "El formato del telefono debe ser numerico";
    private static final String BARRIO_ES_REQUERIDO = "Se debe proporcionar información valida y de un barrio existente";

    private Integer id;
    private String identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;
    private String telefono;
    private Barrio barrio;

    private Persona(Integer id, String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio) throws BusinessException {
        this.id = id;

        PersonaValidator.validarFormatoNumerico(identificacion, FORMATO_INCORRECTO_IDENTIFICACION);
        PersonaValidator.validarLongitudIdentificacion(identificacion, LONGITUD_IDENTIFICACION_NO_VALIDA);
        PersonaValidator.validarCadenaNoVacia(identificacion, IDENTIFICACION_REQUERIDA);
        this.identificacion = Objects.requireNonNull(identificacion, IDENTIFICACION_REQUERIDA);

        PersonaValidator.validarCadenaNoVacia(primerNombre, PRIMER_NOMBRE_REQUERIDO);
        PersonaValidator.validarUnicaPalabra(primerNombre, FORMATO_PRIMER_NOMBRE_INCORRECTO);
        this.primerNombre = Objects.requireNonNull(primerNombre, PRIMER_NOMBRE_REQUERIDO);

        PersonaValidator.validarUnicaPalabraSegundoNombre(segundoNombre, FORMATO_SEGUNDO_NOMBRE_INCORRECTO);
        this.segundoNombre = segundoNombre;

        PersonaValidator.validarCadenaNoVacia(primerApellido, PRIMER_APELLIDO_REQUERIDO);
        PersonaValidator.validarUnicaPalabra(primerApellido, FORMATO_PRIMER_APELLIDO_INCORRECTO);
        this.primerApellido = Objects.requireNonNull(primerApellido, PRIMER_APELLIDO_REQUERIDO);

        PersonaValidator.validarCadenaNoVacia(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);
        PersonaValidator.validarUnicaPalabra(segundoApellido, FORMATO_SEGUNDO_APELLIDO_INCORRECTO);
        this.segundoApellido = Objects.requireNonNull(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);

        PersonaValidator.validarCadenaNoVacia(direccion, DIRECCION_REQUERIDA);
        this.direccion = Objects.requireNonNull(direccion, DIRECCION_REQUERIDA);

        PersonaValidator.validarCadenaNoVacia(telefono, TELEFONO_REQUERIDO);
        PersonaValidator.validarLongitudTelefono(telefono, LONGITUD_TELEFONO_NO_VALIDA);
        PersonaValidator.validarFormatoNumerico(telefono, TELEFONO_FORMATO_INCORRECTO);
        this.telefono = Objects.requireNonNull(telefono, TELEFONO_REQUERIDO);

        this.barrio = Objects.requireNonNull(barrio, BARRIO_ES_REQUERIDO);
    }

    public static Persona nuevo(String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio) throws BusinessException {
        return new Persona(null, identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, barrio);
    }

    public static Persona construir(Integer id, String identificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, Barrio barrio) throws BusinessException {
        return new Persona(id, identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, barrio);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
}
