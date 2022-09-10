package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.persona.Persona;

import java.util.UUID;

public class PersonaHelper {

    public static Persona crearNueva() throws BusinessException {
        return Persona.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                "1234567890",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "0123456789",
                BarrioHelper.construirAleratorio()
        );
    }

    public static Persona crearNueva(String identificacion) throws BusinessException {
        return Persona.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                identificacion,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "0123456789",
                BarrioHelper.construirAleratorio()
        );
    }
}
