package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.referencia.Referencia;

import java.util.UUID;

public class ReferenciasHelper {

    public static Referencia crearReferencia() throws BusinessException {

        return Referencia.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                "1234567890",
                ClienteHelper.crearNuevoCliente(),
                ParentescoHelper.crearParentesco()
        );
    }

    public static Referencia crearReferencia(Cliente cliente) throws BusinessException {

        return Referencia.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                "1234567890",
                cliente,
                ParentescoHelper.crearParentesco()
        );
    }
}
