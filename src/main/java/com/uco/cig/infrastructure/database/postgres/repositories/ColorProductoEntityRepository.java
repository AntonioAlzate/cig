package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorProductoEntityRepository extends JpaRepository<ColorProductoEntity, ColorProductoId> {

    ColorProductoEntity findColorProductoEntityById_IdProducto(Integer idProducto);
}