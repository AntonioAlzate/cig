package com.uco.cig.domain.businessexception;

public class BusinessException extends Exception{
    public BusinessException(String mensaje){
        super(mensaje);
    }
}
