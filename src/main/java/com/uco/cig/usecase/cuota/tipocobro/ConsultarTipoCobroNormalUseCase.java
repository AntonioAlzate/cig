package com.uco.cig.usecase.cuota.tipocobro;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.tipocobro.ports.TipoCobroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultarTipoCobroNormalUseCase {

    private static final String TIPO_COBRO_NORMAL = "NORMAL";
    private static final String TIPO_COBRO_NORMAL_NO_ENCONTRADO = "El tipo de cobro inicial no ha sido encontrado en el sistema";

    private final TipoCobroRepository tipoCobroRepository;

    public ConsultarTipoCobroNormalUseCase(TipoCobroRepository tipoCobroRepository) {
        this.tipoCobroRepository = tipoCobroRepository;
    }

    public TipoCobro consultar(){
        Optional<TipoCobro> tipoCobro = tipoCobroRepository.findByNombre(TIPO_COBRO_NORMAL);

        if(tipoCobro.isEmpty())
            throw new NotFoundException(TIPO_COBRO_NORMAL_NO_ENCONTRADO);

        return tipoCobro.get();
    }
}
