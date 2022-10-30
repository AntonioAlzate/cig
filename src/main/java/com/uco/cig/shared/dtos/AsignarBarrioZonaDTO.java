package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsignarBarrioZonaDTO {
    private Integer idPais;
    private Integer idDepartamento;
    private Integer idRegion;
    private Integer idCiudad;
    private Integer idZona;
    private Integer idBarrio;
    private String nombreBarrio;
}
