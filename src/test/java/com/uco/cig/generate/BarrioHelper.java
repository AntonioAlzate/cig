package com.uco.cig.generate;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.zona.Zona;

import java.util.UUID;

public class BarrioHelper {

    public static Barrio construirAleratorio() throws BusinessException {
        return Barrio.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                Zona.construir(
                        GeneralHelper.obtenerEnteroAleatorio(),
                        UUID.randomUUID().toString(),
                        Ciudad.construir(
                                GeneralHelper.obtenerEnteroAleatorio(),
                                UUID.randomUUID().toString(),
                                Region.construir(
                                        GeneralHelper.obtenerEnteroAleatorio(),
                                        UUID.randomUUID().toString(),
                                        Departamento.construir(
                                                GeneralHelper.obtenerEnteroAleatorio(),
                                                UUID.randomUUID().toString(),
                                                Pais.construir(
                                                        GeneralHelper.obtenerEnteroAleatorio(),
                                                        UUID.randomUUID().toString()
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
