package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ModalidadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PrecioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecioEntityRepository extends JpaRepository<PrecioEntity, Integer> {

    PrecioEntity findByIdProductoEntityAndIdModalidadEntityAndFechaFinIsNull(ProductoEntity idProducto, ModalidadEntity idModalidad);
    List<PrecioEntity> findAllByIdProductoEntityAndIdModalidadEntity(ProductoEntity idProducto, ModalidadEntity idModalidad);
}