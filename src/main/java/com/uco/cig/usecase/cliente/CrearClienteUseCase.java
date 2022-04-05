package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaClienteEnum;
import com.uco.cig.domain.estado.cuentacliente.ports.EstadoCuentaClienteRepository;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class CrearClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final BarrioRepository barrioRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoCuentaClienteRepository estadoCuentaClienteRepository;
    private final MapperUtils mapperUtils;

    public CrearClienteUseCase(ClienteRepository clienteRepository, MapperUtils mapperUtils, BarrioRepository barrioRepository, EstadoRepository estadoRepository,
                               EstadoCuentaClienteRepository estadoCuentaClienteRepository) {
        this.clienteRepository = clienteRepository;
        this.barrioRepository = barrioRepository;
        this.estadoRepository = estadoRepository;
        this.estadoCuentaClienteRepository = estadoCuentaClienteRepository;
        this.mapperUtils = mapperUtils;
    }

    public Cliente crear(ClienteCreacionDto creacionDto) {
        Optional<Barrio> barrio = barrioRepository.findById(creacionDto.getIdBarrio());

        Persona persona = new Persona(
                null,
                creacionDto.getIdentificacion(),
                creacionDto.getPrimerNombre(),
                creacionDto.getSegundoNombre(),
                creacionDto.getPrimerApellido(),
                creacionDto.getSegundoNombre(),
                creacionDto.getDireccion(),
                creacionDto.getTelefono(),
                barrio.get()
        );

        Estado estado = estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString());

        EstadoCuentaCliente estadoCuentaCliente = estadoCuentaClienteRepository.findByEstado(EstadoCuentaClienteEnum.AL_DIA.toString());
        DetalleCuentaFavor detalleCuentaFavor = new DetalleCuentaFavor(null, BigDecimal.ZERO);
        CuentaCliente cuentaCliente = new CuentaCliente(
                null,
                new BigDecimal(creacionDto.getCupo()),
                BigDecimal.ZERO,
                estadoCuentaCliente,
                detalleCuentaFavor
        );

        Cliente cliente = new Cliente(
                null,
                persona,
                cuentaCliente,
                estado
        );

        // TODO: Refactorizar y agregar reglas de negocio y el campo de identificación en persona.

        return clienteRepository.save(cliente);
    }

}
