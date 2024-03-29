package com.uco.cig.domain.businessexception.handler;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public MensajeError notFoundRequest(HttpServletRequest request, Exception exception){
        log.warn(exception.getClass().getCanonicalName() + exception.getMessage());
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BusinessException.class
    })
    @ResponseBody
    public MensajeError badRequest(HttpServletRequest request, Exception exception){
        log.warn("Error de negocio: " + exception.getMessage());
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public MensajeError fatalErrorUnexpectedException(HttpServletRequest request, Exception exception){
        log.error(exception.getClass().getCanonicalName() + exception.getMessage());
        return new MensajeError(exception, request.getRequestURI());
    }
}
