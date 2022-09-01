package com.uco.cig.shared.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class DatosLiquidacionObtenerDTO {

    Integer idTrabajador;
    LocalDate fechaRealizacion;
    Boolean crearLiquidacion;
}
