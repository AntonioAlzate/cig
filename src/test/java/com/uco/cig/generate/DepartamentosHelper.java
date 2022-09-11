package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;

import java.util.UUID;

public class DepartamentosHelper {
    public static Departamento crearDepartamento() throws BusinessException {
        return Departamento.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                PaisHelper.crearPais()
        );
    }
    public static Departamento crearDepartamento(Pais pais) throws BusinessException {
        return Departamento.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                pais
        );
    }
}
