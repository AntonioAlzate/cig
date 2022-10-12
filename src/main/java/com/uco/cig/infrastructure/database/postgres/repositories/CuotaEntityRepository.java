package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CuotaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuotaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuotaEntityRepository extends JpaRepository<CuotaEntity, Integer> {

    Optional<CuotaEntity> findFirstByIdVentaEntityAndIdEstadoCuotaEntityOrderByFechaPropuesta(VentaEntity idVenta, EstadoCuotaEntity idEstadoCuota);

    List<CuotaEntity> findAllByIdVentaEntityAndIdEstadoCuotaEntityOrderByFechaPropuestaDesc(VentaEntity idVenta, EstadoCuotaEntity idEstadoCuota);

    List<CuotaEntity> findAllByIdTrabajadorEntityAndIdEstadoCuotaEntity(TrabajadorEntity idTrabajador, EstadoCuotaEntity idEstadoCuota);

    List<CuotaEntity> findAllByIdVentaEntityOrderByFechaPropuesta(VentaEntity idVenta);
}