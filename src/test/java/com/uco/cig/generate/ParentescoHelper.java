package com.uco.cig.generate;

import com.uco.cig.domain.parentesco.Parentesco;

import java.util.UUID;

public class ParentescoHelper {

    public static Parentesco crearParentesco() {
        return new Parentesco(GeneralHelper.obtenerEnteroAleatorio(), UUID.randomUUID().toString());
    }
}
