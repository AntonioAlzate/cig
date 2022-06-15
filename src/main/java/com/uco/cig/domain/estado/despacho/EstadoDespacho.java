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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
