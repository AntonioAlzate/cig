package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuotaPagoDTO {

    private Integer idTrabajador;
    private Integer idCliente;
    private Integer idVenta;
}
