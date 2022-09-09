package com.uco.cig.usecase.region;

import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.region.ports.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarRegionesUseCase {

    private final RegionRepository regionRepository;

    public ListarRegionesUseCase(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> listar(Integer idDepartamento){
        return regionRepository.findAll(idDepartamento);
    }
}
