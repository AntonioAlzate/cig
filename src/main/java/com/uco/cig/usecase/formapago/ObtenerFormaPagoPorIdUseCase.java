package com.uco.cig.usecase.formapago;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.formapago.ports.FormaPagoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ObtenerFormaPagoPorIdUseCase {

    private static final String FORMA_PAGO_NO_ENCONTRADA = "La Forma de pago especificada no ha sido encontrada";

    private final FormaPagoRepository formaPagoRepository;

    public ObtenerFormaPagoPorIdUseCase(FormaPagoRepository formaPagoRepository) {
        this.formaPagoRepository = formaPagoRepository;
    }

    public FormaPago obtener(Integer idFormaPago){
        Optional<FormaPago> formaPago = formaPagoRepository.findById(idFormaPago);

        if(formaPago.isEmpty())
            throw new NotFoundException(FORMA_PAGO_NO_ENCONTRADA + " id: " + idFormaPago);

        return formaPago.get();
    }
}
