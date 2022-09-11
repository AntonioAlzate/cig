package com.uco.cig.generate;

import com.uco.cig.domain.color.Color;

import java.util.UUID;

public class ColorHelper {
    public static Color crearColor(){
        return new Color(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
