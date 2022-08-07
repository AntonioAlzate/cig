package com.uco.cig.infrastructure.database.postgres.adapter.cuentacliente;

import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.cuentacliente.ports.CuentaClienteRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CuentaClienteEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CuentaClienteEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class CuentaClienteRepositoryAdapter implements CuentaClienteRepository {

    private final CuentaClienteEntityRepository cuentaClienteEntityRepository;
    private final MapperUtils mapperUtils;

    public CuentaClienteRepositoryAdapter(CuentaClienteEntityRepository cuentaClienteEntityRepository, MapperUtils mapperUtils) {
        this.cuentaClienteEntityRepository = cuentaClienteEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public CuentaCliente save(CuentaCliente cuentaCliente) {
        CuentaClienteEntity cuentaClienteEntity = mapperUtils.mappertoCuentaClienteEntity().apply(cuentaCliente);
        cuentaClienteEntity = cuentaClienteEntityRepository.save(cuentaClienteEntity);
        return mapperUtils.mapperToCuentaCliente().apply(cuentaClienteEntity);
    }
}
