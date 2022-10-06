package com.uco.cig.infrastructure.database.postgres.adapter.cuota;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CuotaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuotaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CuotaEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoCuotaEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.TrabajadorEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.VentaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuotaRepositoryAdapter implements CuotaRepository {

    private static final String ESTADO_CUOTA_NO_ENCONTRADO = "El estado de cuota buscado no existe";
    private static final String VENTA_NO_ENCONTRADA = "La venta no ha sido encontrada";
    private static final String TRABAJADOR_NO_ENCONTRADO = "El tyrabajador no ha sido encontrado";

    private final CuotaEntityRepository cuotaEntityRepository;
    private final EstadoCuotaEntityRepository estadoCuotaEntityRepository;
    private final VentaEntityRepository ventaEntityRepository;
    private final TrabajadorEntityRepository trabajadorEntityRepository;
    private final MapperUtils mapperUtils;

    public CuotaRepositoryAdapter(CuotaEntityRepository cuotaEntityRepository, EstadoCuotaEntityRepository estadoCuotaEntityRepository, VentaEntityRepository ventaEntityRepository, TrabajadorEntityRepository trabajadorEntityRepository, MapperUtils mapperUtils) {
        this.cuotaEntityRepository = cuotaEntityRepository;
        this.estadoCuotaEntityRepository = estadoCuotaEntityRepository;
        this.ventaEntityRepository = ventaEntityRepository;
        this.trabajadorEntityRepository = trabajadorEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Cuota> findAll() {
        List<CuotaEntity> cuotaEntities = cuotaEntityRepository.findAll();

        return cuotaEntities.stream().map(c -> mapperUtils.mapperToCuota().apply(c)).collect(Collectors.toList());
    }

    @Override
    public List<Cuota> saveAll(List<Cuota> cuotas) {
        List<CuotaEntity> cuotaEntities = cuotas.stream().map(cuota -> mapperUtils.mappertoCuotaEntity().apply(cuota)).collect(Collectors.toList());
        cuotaEntities = cuotaEntityRepository.saveAll(cuotaEntities);
        return cuotaEntities.stream().map(cuotaEntity -> mapperUtils.mapperToCuota().apply(cuotaEntity)).collect(Collectors.toList());
    }

    @Override
    public Cuota save(Cuota cuota) {
        CuotaEntity cuotaEntity = cuotaEntityRepository.save(mapperUtils.mappertoCuotaEntity().apply(cuota));

        return mapperUtils.mapperToCuota().apply(cuotaEntity);
    }

    @Override
    public Optional<Cuota> findFirstByIdVentaAndIdEstadoCuotaOrderByFechaPropuesta(Integer idVenta, Integer idEstadoCuota) {

        EstadoCuotaEntity estadoCuotaEntity = getEstadoCuotaEntity(idEstadoCuota);

        VentaEntity ventaEntity = getVentaEntity(idVenta);

        Optional<CuotaEntity> cuotaEntity = cuotaEntityRepository.findFirstByIdVentaEntityAndIdEstadoCuotaEntityOrderByFechaPropuesta(ventaEntity, estadoCuotaEntity);

        if(cuotaEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToCuota().apply(cuotaEntity.get()));
    }

    @Override
    public List<Cuota> findAllByIdVentaAndIdEstadoCuotaOrderByFechaPropuestaDsc(Integer idVenta, Integer idEstadoCuota) {

        VentaEntity ventaEntity = getVentaEntity(idVenta);
        EstadoCuotaEntity estadoCuotaEntity = getEstadoCuotaEntity(idEstadoCuota);

        List<CuotaEntity> cuotaEntities = cuotaEntityRepository.findAllByIdVentaEntityAndIdEstadoCuotaEntityOrderByFechaPropuestaDesc(ventaEntity, estadoCuotaEntity);

        return cuotaEntities.stream().map(entity -> mapperUtils.mapperToCuota().apply(entity)).collect(Collectors.toList());
    }

    @Override
    public List<Cuota> findAllByIdTrabajadorAndIdEstadoCuotaAndFechaRealizacion(Integer idTrabajador, Integer idEstadoCuota, OffsetDateTime fechaRealizacion) {
        TrabajadorEntity trabajadorEntity = getTrabajadorEntity(idTrabajador);
        EstadoCuotaEntity estadoCuotaEntity = getEstadoCuotaEntity(idEstadoCuota);

        int diaCalcular = fechaRealizacion.getDayOfYear();
        int anioCalcular = fechaRealizacion.getYear();

        List<CuotaEntity> cuotaEntities = cuotaEntityRepository.findAllByIdTrabajadorEntityAndIdEstadoCuotaEntity(trabajadorEntity, estadoCuotaEntity);

        if(!cuotaEntities.isEmpty()){

            cuotaEntities = cuotaEntities.stream().filter(cuota -> {
                int diaCuota = cuota.getFechaRealizacion().getDayOfYear();
                int anioCuota = cuota.getFechaRealizacion().getYear();
                return (diaCuota == diaCalcular) && (anioCuota == anioCalcular);
            }).collect(Collectors.toList());

            return cuotaEntities.stream().map(cuotaEntity -> mapperUtils.mapperToCuota().apply(cuotaEntity)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private TrabajadorEntity getTrabajadorEntity(Integer idTrabajador) {
        Optional<TrabajadorEntity> trabajadorEntity = trabajadorEntityRepository.findById(idTrabajador);

        if(trabajadorEntity.isEmpty())
            throw new NotFoundException(TRABAJADOR_NO_ENCONTRADO);
        return trabajadorEntity.get();
    }

    private VentaEntity getVentaEntity(Integer idVenta) {
        Optional<VentaEntity> ventaEntity = ventaEntityRepository.findById(idVenta);

        if(ventaEntity.isEmpty())
            throw new NotFoundException(VENTA_NO_ENCONTRADA);
        return ventaEntity.get();
    }

    private EstadoCuotaEntity getEstadoCuotaEntity(Integer idEstadoCuota) {
        Optional<EstadoCuotaEntity> estadoCuotaEntity = estadoCuotaEntityRepository.findById(idEstadoCuota);

        if(estadoCuotaEntity.isEmpty())
            throw new NotFoundException(ESTADO_CUOTA_NO_ENCONTRADO);

        return estadoCuotaEntity.get();
    }
}
