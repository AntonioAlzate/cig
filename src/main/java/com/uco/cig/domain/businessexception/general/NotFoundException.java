package com.uco.cig.domain.businessexception.general;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
