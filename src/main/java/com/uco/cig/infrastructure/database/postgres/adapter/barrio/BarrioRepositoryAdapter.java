package com.uco.cig.infrastructure.database.postgres.adapter.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.infrastructure.database.postgres.entities.BarrioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.BarrioEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.CiudadEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.ZonaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarrioRepositoryAdapter implements BarrioRepository {

    private static final String CIUDAD_NO_ENCONTRADA = "La ciudad especificada no ha sido encontrada en el sistema";

    private final BarrioEntityRepository barrioEntityRepository;
    private final CiudadEntityRepository ciudadEntityRepository;
    private final ZonaEntityRepository zonaEntityRepository;
    private final MapperUtils mapperUtils;

    public BarrioRepositoryAdapter(BarrioEntityRepository barrioEntityRepository, CiudadEntityRepository ciudadEntityRepository, ZonaEntityRepository zonaEntityRepository, MapperUtils mapperUtils) {
        this.barrioEntityRepository = barrioEntityRepository;
        this.ciudadEntityRepository = ciudadEntityRepository;
        this.zonaEntityRepository = zonaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Barrio> findById(Integer id) {
        Optional<BarrioEntity> barrioEntity = barrioEntityRepository.findById(id);

        if(barrioEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(mapperUtils.mapperToBarrio().apply(barrioEntity.get()));
    }

    @Override
    public List<Barrio> findAll() {
        List<BarrioEntity> barrioEntities = barrioEntityRepository.findAll();

        if(barrioEntities.isEmpty())
            return new ArrayList<>();

        return barrioEntities.stream().map(barrioEntity -> mapperUtils.mapperToBarrio().apply(barrioEntity)).collect(Collectors.toList());
    }

    @Override
    public List<Barrio> findAllByIdZAndIdZonaIdCiudad(Integer idCiudad) {
        Optional<CiudadEntity> ciudad = ciudadEntityRepository.findById(idCiudad);

        if(ciudad.isEmpty())
            throw new NotFoundException(CIUDAD_NO_ENCONTRADA);

        List<BarrioEntity> barrioEntities = barrioEntityRepository.findAllByIdZonaEntity_IdCiudad(ciudad.get());

        if(barrioEntities.isEmpty())
            return new ArrayList<>();

        return barrioEntities.stream().map(barrioEntity -> mapperUtils.mapperToBarrio().apply(barrioEntity)).collect(Collectors.toList());
    }

    @Override
    public Barrio assignZone( Integer idZona, Integer idBarrio, String nombreBarrio) throws BusinessException {
        nombreBarrio = nombreBarrio.trim().toUpperCase();
        Optional<BarrioEntity> barrioEntity = barrioEntityRepository.findById(idBarrio);
        Optional<ZonaEntity> zonaEntity = zonaEntityRepository.findById(idZona);

        barrioEntity = barrioEntity.isEmpty() ? barrioEntityRepository.findByNombre(nombreBarrio) : barrioEntity;

        if(barrioEntity.isEmpty()){
            BarrioEntity barrioGuardar = new BarrioEntity(null, nombreBarrio, zonaEntity.get());
            return mapperUtils.mapperToBarrio().apply(barrioEntityRepository.save(barrioGuardar));
        }

        if(barrioEntity.get().getIdZona().getId().equals(idZona))
            throw new BusinessException("El barrio ya pertenece a la zona seleccionada");

         barrioEntity.get().setIdZona(zonaEntity.get());

        return mapperUtils.mapperToBarrio().apply(barrioEntityRepository.save(barrioEntity.get()));
    }
}
