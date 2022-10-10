package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.trabajador.Trabajador;

public class TrabajadorHelper {

    public static Trabajador crearTrabajador() throws BusinessException {
        return Trabajador.construir(
            GeneralHelper.obtenerEnteroAleatorio(),
            PersonaHelper.crearNueva(),
            EstadoHelper.crearEstado(),
                "correo123@gmail.com"
        );
    }
    public static Trabajador crearTrabajador(String IdPersona) throws BusinessException {
        return Trabajador.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                PersonaHelper.crearNueva(),
                EstadoHelper.crearEstado(),
                "correo123@gmail.com"
        );
    }
}
