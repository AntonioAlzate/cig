package com.uco.cig.generate;

import java.math.BigDecimal;

public class GeneralHelper {

    public static int obtenerEnteroAleatorio() {
        int max = 100, min = 1, rango = max - min + 1;
        return (int) ((Math.random() * rango) + min);
    }

    public static BigDecimal obtenerValorBigDecimalAleatorio() {
        return BigDecimal.valueOf(Math.random() * 1000);
    }
}
