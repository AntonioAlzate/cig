package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;

import java.time.OffsetDateTime;

public class VentaHelper {

    public static Venta crearVenta() throws BusinessException {
        return Venta.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                OffsetDateTime.now(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                TrabajadorHelper.crearTrabajador(),
                FormaPagoHelper.crearFormaPago(),
                ModalidadHelper.crearModalidad(),
                CuentaClienteHelper.crearCuentaCliente(),
                EstadoVentaHelper.crearEstadoVenta()
        );
    }

    public static Venta crearVenta(Trabajador trabajador) throws BusinessException {
        return Venta.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                OffsetDateTime.now(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                trabajador,
                FormaPagoHelper.crearFormaPago(),
                ModalidadHelper.crearModalidad(),
                CuentaClienteHelper.crearCuentaCliente(),
                EstadoVentaHelper.crearEstadoVenta()
        );
    }
}