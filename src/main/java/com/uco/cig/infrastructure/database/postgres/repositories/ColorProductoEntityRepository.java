package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ColorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoId;
import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorProductoEntityRepository extends JpaRepository<ColorProductoEntity, ColorProductoId> {

    ColorEntity findColorProductoEntityById_IdProducto(ProductoEntity idProducto);
}