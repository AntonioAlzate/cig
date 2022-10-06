package com.uco.cig.shared.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DatosLiquidacionObtenerDTO {

    Integer idTrabajador;
    LocalDate fechaRealizacion;
    Boolean crearLiquidacion;
}
