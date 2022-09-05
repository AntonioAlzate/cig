package com.uco.cig.shared.dtos;

import com.uco.cig.domain.liquidacion.Liquidacion;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DatosLiquidacionDTO {

    private BigDecimal totalVentas;
    private BigDecimal totalCobrosNormales;
    private BigDecimal totalCobrosIniciales;
    private Liquidacion liquidacion;
}
