package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AbonoPagoDTO {

    private Integer idTrabajador;
    private Integer idCliente;
    private Integer idVenta;
    private BigDecimal valorAbono;
}
