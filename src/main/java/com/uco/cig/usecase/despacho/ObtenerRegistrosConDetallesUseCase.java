package com.uco.cig.usecase.despacho;

import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.despacho.registro.ports.RegistroDespachoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObtenerRegistrosConDetallesUseCase {

    private final RegistroDespachoRepository registroDespachoRepository;

    public ObtenerRegistrosConDetallesUseCase(RegistroDespachoRepository registroDespachoRepository) {
        this.registroDespachoRepository = registroDespachoRepository;
    }

    public List<RegistroDespacho> registrosConDetalles(){
        return registroDespachoRepository.findAll();
    }
}
