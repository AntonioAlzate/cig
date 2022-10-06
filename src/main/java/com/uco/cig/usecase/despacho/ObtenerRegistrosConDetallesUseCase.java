package com.uco.cig.usecase.despacho;

import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.despacho.registro.ports.RegistroDespachoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtenerRegistrosConDetallesUseCase {

    private final RegistroDespachoRepository registroDespachoRepository;

    public ObtenerRegistrosConDetallesUseCase(RegistroDespachoRepository registroDespachoRepository) {
        this.registroDespachoRepository = registroDespachoRepository;
    }

    public List<RegistroDespacho> registrosConDetalles(){
        return registroDespachoRepository.findAll();
    }
}
