package com.uco.cig.shared.dtos;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.referencia.Referencia;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteConReferenciasDTO {
    private Cliente cliente;
    private List<ReferenciaDTO> referencias;
}
