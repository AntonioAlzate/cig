package com.uco.cig.domain.estado.cuentacliente;

public class EstadoCuentaCliente {

    private Integer id;
    private String estado;

    public EstadoCuentaCliente(Integer id, String estado) {
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
