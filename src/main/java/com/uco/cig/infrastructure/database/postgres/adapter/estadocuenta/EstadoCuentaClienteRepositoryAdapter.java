package com.uco.cig.infrastructure.database.postgres.adapter.estadocuenta;

import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.estado.cuentacliente.ports.EstadoCuentaClienteRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuentaClienteEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoCuentaClienteEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class EstadoCuentaClienteRepositoryAdapter implements EstadoCuentaClienteRepository {

    private final EstadoCuentaClienteEntityRepository estadoCuentaClienteEntityRepository;
    private final MapperUtils mapperUtils;

    public EstadoCuentaClienteRepositoryAdapter(EstadoCuentaClienteEntityRepository estadoCuentaClienteEntityRepository, MapperUtils mapperUtils) {
        this.estadoCuentaClienteEntityRepository = estadoCuentaClienteEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public EstadoCuentaCliente findByEstado(String nombre) {
        EstadoCuentaClienteEntity estadoCuentaCliente = estadoCuentaClienteEntityRepository.findByEstado(nombre);
        return mapperUtils.mapperToEstadoCuentaCliente().apply(estadoCuentaCliente);
    }
}
