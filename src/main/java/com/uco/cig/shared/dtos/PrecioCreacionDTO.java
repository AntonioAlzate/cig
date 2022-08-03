package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PrecioCreacionDTO {

    private LocalDate fechaInicio;
    private BigDecimal valor;
    private Integer idModalidad;
    private Integer idProducto;
}
