package com.uco.cig.usecase.referencia;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.domain.referencia.ports.ReferenciaRepository;
import com.uco.cig.shared.dtos.ReferenciaCreacionDTO;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.parentesco.ObtenerParentescoPorIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class CrearReferenciaUseCase {

    private final ReferenciaRepository referenciaRepository;
    private final ObtenerClientePorIdUseCase obtenerClientePorIdUseCase;
    private final ObtenerParentescoPorIdUseCase parentescoPorIdUseCase;

    public CrearReferenciaUseCase(ReferenciaRepository referenciaRepository, ObtenerClientePorIdUseCase obtenerClientePorIdUseCase, ObtenerParentescoPorIdUseCase parentescoPorIdUseCase) {
        this.referenciaRepository = referenciaRepository;
        this.obtenerClientePorIdUseCase = obtenerClientePorIdUseCase;
        this.parentescoPorIdUseCase = parentescoPorIdUseCase;
    }

    public Referencia crear(ReferenciaCreacionDTO creacionDTO, Integer idCliente) throws BusinessException {
        Cliente cliente = obtenerClientePorIdUseCase.obtener(idCliente);
        Parentesco parentesco = parentescoPorIdUseCase.obtener(creacionDTO.getIdParentesco());

        Referencia referencia = Referencia.nuevo(
                creacionDTO.getNombre().trim().toUpperCase(),
                creacionDTO.getTelefono().trim(),
                cliente,
                parentesco);

        return referenciaRepository.save(referencia);
    }
}
