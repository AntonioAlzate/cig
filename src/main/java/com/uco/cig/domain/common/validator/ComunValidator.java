package com.uco.cig.domain.common.validator;

import com.uco.cig.domain.businessexception.BusinessException;

import java.math.BigDecimal;

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
        if(valor.compareTo(BigDecimal.ZERO) < 0){
            throw new BusinessException(mensaje);
        }
    }
}
