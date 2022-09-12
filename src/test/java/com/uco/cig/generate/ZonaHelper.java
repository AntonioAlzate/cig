package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.zona.Zona;

import java.util.UUID;

public class ZonaHelper {
    public static Zona crearZona() throws BusinessException {
        return Zona.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                Ciudad.construir(
                        GeneralHelper.obtenerEnteroAleatorio(),
                        UUID.randomUUID().toString(),
                        RegionHelper.crearRegion()
                )
        );
    }
    public static Zona crearZona(Ciudad ciudad) throws BusinessException {
        return Zona.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                ciudad
        );
    }
}
