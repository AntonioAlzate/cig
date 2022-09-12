package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RutaCreacionDTO {
    private Integer idPais;
    private String nombrePais;
    private Integer idDepartamento;
    private String nombreDepartamento;
    private Integer idRegion;
    private String nombreRegion;
    private Integer idCiudad;
    private String nombreCiudad;
    private String nombreZona;
}
