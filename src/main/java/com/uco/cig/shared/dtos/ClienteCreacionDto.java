package com.uco.cig.shared.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteCreacionDto implements Serializable {
    private final String identificacion;
    private final String primerNombre;
    private final String segundoNombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final String direccion;
    private final String telefono;
    private final String cupo;
    private final Integer idBarrio;
    private final ReferenciaCreacionDTO referencia1;
    private final ReferenciaCreacionDTO referencia2;
}
