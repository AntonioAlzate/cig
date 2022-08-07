package com.uco.cig.shared.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreacionVentaDTO {

    // de la venta se recibe: idTrabajador, idFormaPago, idModalidad, idCliente
    // Se recibe un arreglo de detalles, cada uno tendria el producto la cantidad y si posee descuento y la razon

    private Integer idTrabajador;
    private Integer idCliente;
    private Integer idFormaPago;
    private Integer idModalidad;
    private List<DetalleVentaDTO> detallesVenta;
    private BigDecimal cuotaInicial;

}
