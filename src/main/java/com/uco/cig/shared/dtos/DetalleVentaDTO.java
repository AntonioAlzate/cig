package com.uco.cig.shared.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleVentaDTO {
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal descuentoAdicional;
    private String justificacion;
}
