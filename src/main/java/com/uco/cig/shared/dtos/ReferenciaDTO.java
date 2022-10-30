package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferenciaDTO {

    private String nombre;
    private String telefono;
    private Integer parentesco;
    private String nombreParentesco;
}
