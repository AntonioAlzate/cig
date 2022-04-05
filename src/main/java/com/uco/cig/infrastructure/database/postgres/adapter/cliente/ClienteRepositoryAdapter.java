package com.uco.cig.infrastructure.database.postgres.adapter.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.CuentaClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.DetalleCuentaFavorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ClienteEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.CuentaClienteEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.DetalleCuentaFavorEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.PersonaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final PersonaEntityRepository personaEntityRepository;
    private final CuentaClienteEntityRepository cuentaClienteRepository;
    private final DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository;
    private final ClienteEntityRepository clienteEntityRepository;
    private final MapperUtils mapperUtils;

    public ClienteRepositoryAdapter(PersonaEntityRepository personaEntityRepository, CuentaClienteEntityRepository cuentaClienteRepository,
                                    DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository, ClienteEntityRepository clienteEntityRepository, MapperUtils mapperUtils) {
        this.personaEntityRepository = personaEntityRepository;
        this.cuentaClienteRepository = cuentaClienteRepository;
        this.clienteEntityRepository = clienteEntityRepository;
        this.detalleCuentaFavorEntityRepository = detalleCuentaFavorEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Cliente save(Cliente cliente) {
        Persona persona = cliente.getPersona();
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(persona);
        personaEntity = personaEntityRepository.save(personaEntity);

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
}
