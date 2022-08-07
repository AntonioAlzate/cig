package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.cuentacliente.ports.CuentaClienteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class ValidarActualizarCupoUseCase {

    private static final String CUPO_INSUFICIENTE = "El cupo del cliente es menor al valor de la venta";

    private final CuentaClienteRepository cuentaClienteRepository;

    public ValidarActualizarCupoUseCase(CuentaClienteRepository cuentaClienteRepository) {
        this.cuentaClienteRepository = cuentaClienteRepository;
    }

    public void validarActualizar(CuentaCliente cuentaCliente, BigDecimal valorTotalCompra) {
        if(cuentaCliente.getCupo().subtract(valorTotalCompra).compareTo(BigDecimal.ZERO) < 0 ){
            throw new BadRequestException(CUPO_INSUFICIENTE);
        }

        BigDecimal nuevoCupo = cuentaCliente.getCupo().subtract(valorTotalCompra);
        BigDecimal nuevaDeuda = cuentaCliente.getSaldoDeuda().add(valorTotalCompra);

        cuentaCliente.setCupo(nuevoCupo);
        cuentaCliente.setSaldoDeuda(nuevaDeuda);

        cuentaClienteRepository.save(cuentaCliente);
    }
}