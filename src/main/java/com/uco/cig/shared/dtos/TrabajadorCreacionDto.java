package com.uco.cig.shared.dtos;

import lombok.Data;

@Data
public class TrabajadorCreacionDto {
    private final String identificacion;
    private final String primerNombre;
    private final String segundoNombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final String direccion;
    private final String telefono;
    private final Integer idBarrio;
    private final String correo;
}
