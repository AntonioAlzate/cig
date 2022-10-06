package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegistrarEntradaCuentaFavorUseCase {

    private final EntradaCuentaFavorRepository entradaCuentaFavorRepository;

    public RegistrarEntradaCuentaFavorUseCase(EntradaCuentaFavorRepository entradaCuentaFavorRepository) {
        this.entradaCuentaFavorRepository = entradaCuentaFavorRepository;
    }

    public EntradaCuentaFavor registrar(String descripcion, BigDecimal valor, DetalleCuentaFavor detalleCuentaFavor) throws BusinessException {
        EntradaCuentaFavor entradaCuentaFavor = EntradaCuentaFavor.nuevo(descripcion, valor, detalleCuentaFavor);

        return entradaCuentaFavorRepository.save(entradaCuentaFavor);
    }
}
