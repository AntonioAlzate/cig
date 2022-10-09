package com.uco.cig.domain.common.validator;

import com.uco.cig.domain.businessexception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComunValidator {

    public static void validarFormatoCorreo(String correo, String mensaje) throws BusinessException {
        if(correo == null)
            return;

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(correo);

        if(!matcher.find())
            throw new BusinessException(mensaje);
    }

    public static void validarCadenaNoVacia(String valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;

        if (valor.trim().isEmpty()) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFormatoNumerico(String valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;

        if (!valor.chars().allMatch(Character::isDigit)) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorCero(BigDecimal valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorCero(Integer valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;

        if (valor <= 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorIgualCero(BigDecimal valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorIgualCero(Integer valor, String mensaje) throws BusinessException {
        if (valor == null)
            return;

        if (valor < 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarUnicaPalabra(String valor, String mensaje) throws BusinessException {

        if (valor == null)
            return;

        int longitud = valor.split(" ").length;

        if (longitud > 1) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFechaNoMenorAActual(OffsetDateTime fecha, String mensaje) throws BusinessException {
        if (fecha == null)
            return;

        if (fecha.getDayOfYear() < OffsetDateTime.now().getDayOfYear()) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFechaNoMenorAActual(LocalDate fecha, String mensaje) throws BusinessException {
        if (fecha == null)
            return;

        if (fecha.getDayOfYear() < LocalDate.now().getDayOfYear()) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFechaNoFutura(OffsetDateTime fecha, String mensaje) throws BusinessException {

        if (fecha == null)
            return;

        if (fecha.getDayOfYear() > OffsetDateTime.now().getDayOfYear()) {
            throw new BusinessException(mensaje);
        }
    }
}
