package com.uco.cig.usecase.formapago;

import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.formapago.ports.FormaPagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarFormasPagoUseCase {

    private final FormaPagoRepository formaPagoRepository;

    public ListarFormasPagoUseCase(FormaPagoRepository formaPagoRepository) {
        this.formaPagoRepository = formaPagoRepository;
    }

    public List<FormaPago> listar() {
        return formaPagoRepository.findAll();
    }
}
