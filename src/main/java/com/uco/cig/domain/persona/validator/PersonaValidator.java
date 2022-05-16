package com.uco.cig.domain.persona.validator;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;

import java.util.Objects;
import java.util.Optional;

public final class PersonaValidator extends ComunValidator {

    public static void validarLongitudIdentificacion(String identificacion, String mensaje) throws BusinessException {
        if (identificacion.length() < 4 || identificacion.length() > 10) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarUnicaPalabraSegundoNombre(String valor, String mensaje) throws BusinessException {

        int longitud = 0;

        if(valor != null)
            longitud = valor.split(" ").length;

        if (longitud > 1) {
            throw new BusinessException(mensaje);
        }
    }

    public static void validarLongitudTelefono(String telefono, String mensaje) throws BusinessException {
        if (telefono.length() != 10)
            throw new BusinessException(mensaje);
    }
}
