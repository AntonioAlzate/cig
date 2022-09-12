package com.uco.cig.usecase.region;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.region.ports.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearRegionUseCase {

    private final RegionRepository regionRepository;

    public CrearRegionUseCase(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region crear(String nombreRegion, Departamento departamento) throws BusinessException {
        Region region = Region.nuevo(nombreRegion, departamento);

        return regionRepository.save(region);
    }
}
