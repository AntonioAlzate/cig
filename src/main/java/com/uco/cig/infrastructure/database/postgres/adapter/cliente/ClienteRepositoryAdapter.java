package com.uco.cig.infrastructure.database.postgres.adapter.cliente;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.infrastructure.database.postgres.entities.*;
import com.uco.cig.infrastructure.database.postgres.repositories.*;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteRepositoryAdapter implements ClienteRepository {

    private static final String PERSONA_NO_ENCONTRADA = "La identificación que ha ingresa no coincide con la de ninguna persona en el sistema";
    private static final String CLIENTE_CON_IDENTIFICACION_NO_EXISTE = "No se encontro un cliente asociado a la identificación especificada";

    private final PersonaEntityRepository personaEntityRepository;
    private final CuentaClienteEntityRepository cuentaClienteRepository;
    private final DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository;
    private final ClienteEntityRepository clienteEntityRepository;
    private final EstadoEntityRepository estadoEntityRepository;
    private final EstadoCuentaClienteEntityRepository estadoCuentaClienteEntityRepository;
    private final ZonaEntityRepository zonaEntityRepository;
    private final ReferenciaEntityRepository referenciaEntityRepository;
    private final MapperUtils mapperUtils;

    public ClienteRepositoryAdapter(PersonaEntityRepository personaEntityRepository, CuentaClienteEntityRepository cuentaClienteRepository,
                                    DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository, ClienteEntityRepository clienteEntityRepository, EstadoEntityRepository estadoEntityRepository, EstadoCuentaClienteEntityRepository estadoCuentaClienteEntityRepository, ZonaEntityRepository zonaEntityRepository, ReferenciaEntityRepository referenciaEntityRepository, MapperUtils mapperUtils) {
        this.personaEntityRepository = personaEntityRepository;
        this.cuentaClienteRepository = cuentaClienteRepository;
        this.clienteEntityRepository = clienteEntityRepository;
        this.detalleCuentaFavorEntityRepository = detalleCuentaFavorEntityRepository;
        this.estadoEntityRepository = estadoEntityRepository;
        this.estadoCuentaClienteEntityRepository = estadoCuentaClienteEntityRepository;
        this.zonaEntityRepository = zonaEntityRepository;
        this.referenciaEntityRepository = referenciaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Cliente cambiarEstado(Integer idCliente, String estado) {
        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findById(idCliente);

        if (clienteEntity.isEmpty()) {
            throw new NotFoundException(CLIENTE_CON_IDENTIFICACION_NO_EXISTE);
        }

        EstadoEntity estadoEntity = estadoEntityRepository.findByNombre(estado);
        clienteEntity.get().setIdEstado(estadoEntity);

        return mapperUtils.mapperToCliente().apply(clienteEntityRepository.save(clienteEntity.get()));
    }

    @Override
    public Cliente cambiarEstadoCuenta(Integer idCliente, String estadoNuevo) {
        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findById(idCliente);

        if (clienteEntity.isEmpty()) {
            throw new NotFoundException(CLIENTE_CON_IDENTIFICACION_NO_EXISTE);
        }

        Optional<EstadoCuentaClienteEntity> estadoCuentaCliente = estadoCuentaClienteEntityRepository.findByEstado(estadoNuevo);

        if (estadoCuentaCliente.isEmpty()) {
            throw new NotFoundException("Estado de cuenta no encontrado");
        }

        clienteEntity.get().getIdCuentaCliente().setIdEstadoCuentaCliente(estadoCuentaCliente.get());

        return mapperUtils.mapperToCliente().apply(clienteEntityRepository.save(clienteEntity.get()));
    }

    @Override
    public List<Cliente> findClientesConIdZona(Integer idZona) {
        Optional<ZonaEntity> zonaEntity = zonaEntityRepository.findById(idZona);

        if (zonaEntity.isEmpty()) {
            return new ArrayList<>();
        }

        List<ClienteEntity> clienteEntities = clienteEntityRepository.findClienteEntitiesByIdPersonaEntity_IdBarrio_IdZonaEntity(zonaEntity.get());

        return clienteEntities.stream().map(c -> mapperUtils.mapperToCliente().apply(c)).collect(Collectors.toList());
    }

    @Override
    public Cliente update(Cliente cliente, Referencia referencia1, Referencia referencia2) {
        ClienteEntity clienteEntity = mapperUtils.mappertoClienteEntity().apply(cliente);

        clienteEntity.setIdPersona(personaEntityRepository.save(clienteEntity.getIdPersona()));
        clienteEntity.setIdCuentaCliente(cuentaClienteRepository.save(clienteEntity.getIdCuentaCliente()));

        clienteEntity = clienteEntityRepository.save(clienteEntity);

        referenciaEntityRepository.save(mapperUtils.mappertoReferenciaEntity().apply(referencia1));
        referenciaEntityRepository.save(mapperUtils.mappertoReferenciaEntity().apply(referencia2));

        return mapperUtils.mapperToCliente().apply(clienteEntity);
    }

    @Override
    public Cliente save(Cliente cliente) {

        Optional<PersonaEntity> personaValidar = personaEntityRepository.findByIdentificacion(cliente.getPersona().getIdentificacion());

        Persona persona = cliente.getPersona();
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(persona);

        if (personaValidar.isEmpty()) {
            personaEntity = personaEntityRepository.save(personaEntity);
        } else {
            personaEntity = personaValidar.get();
        }

        DetalleCuentaFavor detalleCuentaFavor = cliente.getCuentaCliente().getDetalleCuentaFavor();
        DetalleCuentaFavorEntity detalleCuentaFavorEntity = mapperUtils.mappertoDetalleCuentaFavorEntity().apply(detalleCuentaFavor);
        detalleCuentaFavorEntity = detalleCuentaFavorEntityRepository.save(detalleCuentaFavorEntity);

        CuentaCliente cuentaCliente = cliente.getCuentaCliente();
        CuentaClienteEntity cuentaClienteEntity = mapperUtils.mappertoCuentaClienteEntity().apply(cuentaCliente);
        cuentaClienteEntity.setIdDetalleCuentaFavor(detalleCuentaFavorEntity);
        cuentaClienteEntity = cuentaClienteRepository.save(cuentaClienteEntity);

        ClienteEntity clienteEntity = mapperUtils.mappertoClienteEntity().apply(cliente);
        clienteEntity.setIdPersona(personaEntity);
        clienteEntity.setIdCuentaCliente(cuentaClienteEntity);

        clienteEntity = clienteEntityRepository.save(clienteEntity);

        return mapperUtils.mapperToCliente().apply(clienteEntity);
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findById(id);

        if (clienteEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToCliente().apply(clienteEntity.get()));
    }

    @Override
    public Optional<Cliente> findByIdPersona(Persona idPersona) {

        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findByIdPersonaEntity(mapperUtils.mappertoPersonaEntity().apply(idPersona));

        if (clienteEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToCliente().apply(clienteEntity.get()));
    }

    @Override
    public Boolean existsByIdPersona(Persona idPersona) {
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(idPersona);
        return clienteEntityRepository.existsByIdPersonaEntity(personaEntity);
    }

    @Override
    public List<Cliente> findAll() {

        List<ClienteEntity> clientes = clienteEntityRepository.findAll();

        return clientes.stream().map(x -> mapperUtils.mapperToCliente().apply(x)).collect(Collectors.toList());
    }

    @Override
    public Cliente findByIdentificacion(String identificacion) {
        Optional<PersonaEntity> personaEntity = personaEntityRepository.findByIdentificacion(identificacion);
        Optional<ClienteEntity> clienteEntity;

        if (personaEntity.isEmpty()) {
            throw new NotFoundException(PERSONA_NO_ENCONTRADA);
        }

        clienteEntity = clienteEntityRepository.findByIdPersonaEntity(personaEntity.get());

        if (clienteEntity.isEmpty()) {
            throw new NotFoundException(CLIENTE_CON_IDENTIFICACION_NO_EXISTE);
        }

        return mapperUtils.mapperToCliente().apply(clienteEntity.get());
    }
}
