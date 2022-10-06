package com.uco.cig.domain.estado.liquidacion;

public class EstadoLiquidacion {

    private Integer id;
    private String nombre;

    public EstadoLiquidacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
