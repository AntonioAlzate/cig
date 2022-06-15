package com.uco.cig.domain.common.validator;

import com.uco.cig.domain.businessexception.BusinessException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ComunValidator {

    public static void validarCadenaNoVacia(String valor, String mensaje) throws BusinessException {
        if (valor.trim().isEmpty()) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFormatoNumerico(String valor, String mensaje) throws BusinessException {
        if (!valor.chars().allMatch(Character::isDigit)) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorCero(BigDecimal valor, String mensaje) throws BusinessException {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorCero(Integer valor, String mensaje) throws BusinessException {
        if (valor <= 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorIgualCero(BigDecimal valor, String mensaje) throws BusinessException {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarNumeroMayorIgualCero(Integer valor, String mensaje) throws BusinessException {
        if (valor < 0) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarUnicaPalabra(String valor, String mensaje) throws BusinessException {
        int longitud = valor.split(" ").length;

        if (longitud > 1) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFechaNoMenorAActual(OffsetDateTime fecha, String mensaje) throws BusinessException {
        if(fecha.getDayOfYear() < OffsetDateTime.now().getDayOfYear()){
            throw new BusinessException(mensaje);
        }
    }

    public static void validarFechaNoFutura(OffsetDateTime fecha, String mensaje) throws BusinessException {

        if(fecha == null)
            return;

        if(fecha.getDayOfYear() > OffsetDateTime.now().getDayOfYear()){
            throw new BusinessException(mensaje);
        }
    }
}
