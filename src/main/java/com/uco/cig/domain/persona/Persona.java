package com.uco.cig.domain.persona;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
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

        ComunValidator.validarFormatoNumerico(identificacion, FORMATO_INCORRECTO_IDENTIFICACION);
        PersonaValidator.validarLongitudIdentificacion(identificacion, LONGITUD_IDENTIFICACION_NO_VALIDA);
        ComunValidator.validarCadenaNoVacia(identificacion, IDENTIFICACION_REQUERIDA);
        this.identificacion = Objects.requireNonNull(identificacion, IDENTIFICACION_REQUERIDA);

        ComunValidator.validarCadenaNoVacia(primerNombre, PRIMER_NOMBRE_REQUERIDO);
        ComunValidator.validarUnicaPalabra(primerNombre, FORMATO_PRIMER_NOMBRE_INCORRECTO);
        this.primerNombre = Objects.requireNonNull(primerNombre, PRIMER_NOMBRE_REQUERIDO);

        PersonaValidator.validarUnicaPalabraSegundoNombre(segundoNombre, FORMATO_SEGUNDO_NOMBRE_INCORRECTO);
        this.segundoNombre = segundoNombre;

        ComunValidator.validarCadenaNoVacia(primerApellido, PRIMER_APELLIDO_REQUERIDO);
        ComunValidator.validarUnicaPalabra(primerApellido, FORMATO_PRIMER_APELLIDO_INCORRECTO);
        this.primerApellido = Objects.requireNonNull(primerApellido, PRIMER_APELLIDO_REQUERIDO);

        ComunValidator.validarCadenaNoVacia(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);
        ComunValidator.validarUnicaPalabra(segundoApellido, FORMATO_SEGUNDO_APELLIDO_INCORRECTO);
        this.segundoApellido = Objects.requireNonNull(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);

        ComunValidator.validarCadenaNoVacia(direccion, DIRECCION_REQUERIDA);
        this.direccion = Objects.requireNonNull(direccion, DIRECCION_REQUERIDA);

        ComunValidator.validarCadenaNoVacia(telefono, TELEFONO_REQUERIDO);
        PersonaValidator.validarLongitudTelefono(telefono, LONGITUD_TELEFONO_NO_VALIDA);
        ComunValidator.validarFormatoNumerico(telefono, TELEFONO_FORMATO_INCORRECTO);
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

    public String getIdentificacion() {
        return identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setIdentificacion(String identificacion) throws BusinessException {
        ComunValidator.validarFormatoNumerico(identificacion, FORMATO_INCORRECTO_IDENTIFICACION);
        PersonaValidator.validarLongitudIdentificacion(identificacion, LONGITUD_IDENTIFICACION_NO_VALIDA);
        ComunValidator.validarCadenaNoVacia(identificacion, IDENTIFICACION_REQUERIDA);
        this.identificacion = Objects.requireNonNull(identificacion, IDENTIFICACION_REQUERIDA);
    }

    public void setPrimerNombre(String primerNombre) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(primerNombre, PRIMER_NOMBRE_REQUERIDO);
        ComunValidator.validarUnicaPalabra(primerNombre, FORMATO_PRIMER_NOMBRE_INCORRECTO);
        this.primerNombre = Objects.requireNonNull(primerNombre, PRIMER_NOMBRE_REQUERIDO);
    }

    public void setSegundoNombre(String segundoNombre) throws BusinessException {
        PersonaValidator.validarUnicaPalabraSegundoNombre(segundoNombre, FORMATO_SEGUNDO_NOMBRE_INCORRECTO);
        this.segundoNombre = segundoNombre;
    }

    public void setPrimerApellido(String primerApellido) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(primerApellido, PRIMER_APELLIDO_REQUERIDO);
        ComunValidator.validarUnicaPalabra(primerApellido, FORMATO_PRIMER_APELLIDO_INCORRECTO);
        this.primerApellido = Objects.requireNonNull(primerApellido, PRIMER_APELLIDO_REQUERIDO);
    }

    public void setSegundoApellido(String segundoApellido) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);
        ComunValidator.validarUnicaPalabra(segundoApellido, FORMATO_SEGUNDO_APELLIDO_INCORRECTO);
        this.segundoApellido = Objects.requireNonNull(segundoApellido, SEGUNDO_APELLIDO_REQUERIDO);
    }

    public void setDireccion(String direccion) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(direccion, DIRECCION_REQUERIDA);
        this.direccion = Objects.requireNonNull(direccion, DIRECCION_REQUERIDA);
    }

    public void setTelefono(String telefono) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(telefono, TELEFONO_REQUERIDO);
        PersonaValidator.validarLongitudTelefono(telefono, LONGITUD_TELEFONO_NO_VALIDA);
        ComunValidator.validarFormatoNumerico(telefono, TELEFONO_FORMATO_INCORRECTO);
        this.telefono = Objects.requireNonNull(telefono, TELEFONO_REQUERIDO);
    }

    public void setBarrio(Barrio barrio) throws BusinessException {
        this.barrio = Barrio.construir(barrio.getId(), barrio.getNombre(), barrio.getZona());
    }
}
