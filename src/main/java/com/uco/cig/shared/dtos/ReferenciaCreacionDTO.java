package com.uco.cig.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferenciaCreacionDTO {
    private String nombre;
    private String telefono;
    private Integer idParentesco;
}
