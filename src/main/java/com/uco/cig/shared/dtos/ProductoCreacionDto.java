package com.uco.cig.shared.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductoCreacionDto implements Serializable {

    private String nombre;
    private String referencia;
    private String descripcion;
    private Integer idDimension;
    private Double largo;
    private Double ancho;
    private Integer idCategoria;
    private Integer idColor;
    private String nombreColor;
    private BigDecimal valorCredito;
    private BigDecimal valorContado;
}
