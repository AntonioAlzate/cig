package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.producto.Producto;

import java.util.UUID;

public class ProductoHelper {
    public static Producto crearProducto() throws BusinessException {
        return Producto.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                EstadoHelper.crearEstado(),
                DimensionesHelper.crearDiemension(),
                CategoriaHelper.crearCategoria(),
                GeneralHelper.obtenerEnteroAleatorio()
        );
    }
}
