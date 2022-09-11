package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.region.Region;

import java.util.UUID;

public class CiudadHelper {
    public static Ciudad crearCiudad() throws BusinessException {
        return Ciudad.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                RegionHelper.crearRegion()
        );
    }

    public static Ciudad crearCiudad(Region region) throws BusinessException {
        return Ciudad.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                region
        );
    }
}
