package com.uco.cig.domain.dimension.ports;

import com.uco.cig.domain.dimension.Dimension;

import java.util.Optional;

public interface DimensionRepository {

    Optional<Dimension> findById(Integer id);
    Dimension save(Dimension dimension);
}
