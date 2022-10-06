package com.uco.cig.usecase.referencia;

import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.domain.referencia.ports.ReferenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarReferenciasDeClienteUseCase {

    private final ReferenciaRepository referenciaRepository;

    public ListarReferenciasDeClienteUseCase(ReferenciaRepository referenciaRepository) {
        this.referenciaRepository = referenciaRepository;
    }

    public List<Referencia> listar(Integer idCliente){
        return referenciaRepository.findAllByCliente(idCliente);
    }
}
