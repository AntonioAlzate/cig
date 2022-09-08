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
    private final String nombreReferencia1;
    private final String telefonoReferencia1;
    private final Integer idParentescoReferencia1;
    private final String nombreReferencia2;
    private final String telefonoReferencia2;
    private final Integer idParentescoReferencia2;
}
