package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class ActualizarCupoDeudaAbonoCuentaClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ActualizarCupoDeudaAbonoCuentaClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente actualizar(BigDecimal valorAbono, Cliente cliente){
        CuentaCliente cuentaCliente = cliente.getCuentaCliente();
        int comparacion = cuentaCliente.getSaldoDeuda().compareTo(valorAbono);
        BigDecimal saldoDeuda = cuentaCliente.getSaldoDeuda();
        BigDecimal cupo = cuentaCliente.getCupo();

        if (comparacion >= 0){
            cuentaCliente.setSaldoDeuda(saldoDeuda.subtract(valorAbono));
            cuentaCliente.setCupo(cupo.add(valorAbono));
        } else {
            BigDecimal diferencia = valorAbono.subtract(saldoDeuda);
            cuentaCliente.setCupo(cupo.add(saldoDeuda));
            cuentaCliente.setSaldoDeuda(BigDecimal.ZERO);

            // todo: la diferencia entra a la cuenta a favor
        }

        cliente.setCuentaCliente(cuentaCliente);
        return clienteRepository.save(cliente);
    }
}
