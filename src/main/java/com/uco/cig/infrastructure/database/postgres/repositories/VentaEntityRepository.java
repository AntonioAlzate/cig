package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CuentaClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaEntityRepository extends JpaRepository<VentaEntity, Integer> {

    List<VentaEntity> findAllByIdTrabajador(TrabajadorEntity idTrabajador);
    List<VentaEntity> findAllByIdCuentaCliente(CuentaClienteEntity idCuentaCliente);
}