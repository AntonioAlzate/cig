package com.uco.cig.domain.trabajador.validator;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

public final class TrabajadorValidator extends ComunValidator {

    public static void validarLongitudUserName(String username, String mensaje) throws BusinessException {
        if(username.length() < 4 || username.length() > 16)
            throw new BusinessException(mensaje);
    }

    public static void validarFormatoPassword(String password, String mensaje) throws BusinessException {

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        if(!password.matches(pattern)){
            throw new BusinessException(mensaje);
        }
    }
}