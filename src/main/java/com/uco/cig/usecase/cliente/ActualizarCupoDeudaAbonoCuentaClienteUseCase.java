package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class ActualizarCupoDeudaAbonoCuentaClienteUseCase {

    private static final String ENTRADA_DESCRIPCION = "Entrada automatica ya que abono generado supera a la deuda del cliente";

    private final ClienteRepository clienteRepository;
    private final RegistrarEntradaCuentaFavorUseCase entradaCuentaFavorUseCase;

    public ActualizarCupoDeudaAbonoCuentaClienteUseCase(ClienteRepository clienteRepository, RegistrarEntradaCuentaFavorUseCase entradaCuentaFavorUseCase) {
        this.clienteRepository = clienteRepository;
        this.entradaCuentaFavorUseCase = entradaCuentaFavorUseCase;
    }

    public Cliente actualizar(BigDecimal valorAbono, Cliente cliente) throws BusinessException {
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

            // se crea entrada a la cuenta a favor y se actualizar el valor de la cuenta a favor
            entradaCuentaFavorUseCase.registrar(ENTRADA_DESCRIPCION, diferencia, cliente.getCuentaCliente().getDetalleCuentaFavor());

            BigDecimal valorCuentaFavor = cliente.getCuentaCliente().getDetalleCuentaFavor().getValor();
            cliente.getCuentaCliente().getDetalleCuentaFavor().setValor(valorCuentaFavor.add(diferencia));
        }

        cliente.setCuentaCliente(cuentaCliente);
        return clienteRepository.save(cliente);
    }
}
