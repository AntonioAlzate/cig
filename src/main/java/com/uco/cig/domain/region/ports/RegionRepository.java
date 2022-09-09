package com.uco.cig.domain.region.ports;

import com.uco.cig.domain.region.Region;

import java.util.List;

public interface RegionRepository {

    List<Region> findAll(Integer idDepartamento);
}
