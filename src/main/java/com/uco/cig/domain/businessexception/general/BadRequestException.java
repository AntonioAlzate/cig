package com.uco.cig.domain.businessexception.general;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
