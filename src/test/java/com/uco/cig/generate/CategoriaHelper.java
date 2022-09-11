package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;

import java.util.UUID;

public class CategoriaHelper {
    public static Categoria crearCategoria() throws BusinessException {
        return Categoria.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
