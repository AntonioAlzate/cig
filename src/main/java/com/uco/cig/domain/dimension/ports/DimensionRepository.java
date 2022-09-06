package com.uco.cig.domain.dimension.ports;

import com.uco.cig.domain.dimension.Dimension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DimensionRepository {

    Optional<Dimension> findById(Integer id);
    Dimension save(Dimension dimension);

    List<Dimension> findAll();

    Dimension findByAnchoAndLargo(BigDecimal ancho, BigDecimal largo);
}
