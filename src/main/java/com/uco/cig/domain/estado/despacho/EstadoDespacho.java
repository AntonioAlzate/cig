package com.uco.cig.domain.estado.despacho;

public class EstadoDespacho {

    private Integer id;
    private String estado;

    public EstadoDespacho(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }
}
