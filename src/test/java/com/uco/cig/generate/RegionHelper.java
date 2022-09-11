package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.region.Region;

import java.util.UUID;

public class RegionHelper {
    public static Region crearRegion() throws BusinessException {
        return Region.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                DepartamentosHelper.crearDepartamento()
        );
    }
    public static Region crearRegion(Departamento departamento) throws BusinessException {
        return Region.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                departamento
        );
    }
}
