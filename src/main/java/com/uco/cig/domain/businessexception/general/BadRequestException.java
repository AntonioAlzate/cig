package com.uco.cig.domain.businessexception.general;

public class BadRequestException extends RuntimeException{

    private static final String DESCRIPCION = "Bad Request (400). ";

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
