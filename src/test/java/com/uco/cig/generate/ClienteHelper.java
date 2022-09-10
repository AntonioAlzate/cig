package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.persona.Persona;

import java.math.BigDecimal;

public class ClienteHelper {

    public static Cliente crearNuevoCliente(Persona persona) throws BusinessException {
        return Cliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                persona,
                crearNuevaCuentaCliente(),
                new Estado(1, "ACTIVO")
        );
    }

    public static Cliente crearNuevoCliente() throws BusinessException {
        return Cliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                PersonaHelper.crearNueva("1234567890"),
                crearNuevaCuentaCliente(),
                new Estado(1, "ACTIVO")
        );
    }


    public static Cliente crearNuevoCliente(String estadoInicial) throws BusinessException {
        return Cliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                PersonaHelper.crearNueva("1234567890"),
                crearNuevaCuentaCliente(),
                new Estado(1, estadoInicial)
        );
    }

    public static Cliente crearNuevoCliente(CuentaCliente cuentaCliente) throws BusinessException {
        return Cliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                PersonaHelper.crearNueva(),
                cuentaCliente,
                new Estado(1, "ACTIVO")
        );
    }

    public static CuentaCliente crearNuevaCuentaCliente() throws BusinessException {
        return CuentaCliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                new EstadoCuentaCliente(1, "AL_DIA"),
                crearNuevoDetalleCuentaFavor()
        );
    }

    public static CuentaCliente crearNuevaCuentaCliente(BigDecimal cupo, BigDecimal saldoDeuda) throws BusinessException {
        return CuentaCliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                cupo,
                saldoDeuda,
                new EstadoCuentaCliente(1, "AL_DIA"),
                crearNuevoDetalleCuentaFavor()
        );
    }

    public static DetalleCuentaFavor crearNuevoDetalleCuentaFavor() throws BusinessException {
        return DetalleCuentaFavor.construir(1, GeneralHelper.obtenerValorBigDecimalAleatorio());
    }
}
