package com.uco.cig.infrastructure.database.postgres.adapter.precio;

import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.precio.ports.PrecioRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ModalidadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PrecioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ModalidadEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.PrecioEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.ProductoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrecioRepositoryAdapter implements PrecioRepository {

    private final PrecioEntityRepository precioEntityRepository;
    private final ProductoEntityRepository productoEntityRepository;
    private final ModalidadEntityRepository modalidadEntityRepository;
    private final MapperUtils mapperUtils;

    public PrecioRepositoryAdapter(PrecioEntityRepository precioEntityRepository, ProductoEntityRepository productoEntityRepository, ModalidadEntityRepository modalidadEntityRepository, MapperUtils mapperUtils) {
        this.precioEntityRepository = precioEntityRepository;
        this.productoEntityRepository = productoEntityRepository;
        this.modalidadEntityRepository = modalidadEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Precio save(Precio precio) {
        PrecioEntity precioEntity = mapperUtils.mappertoPrecioEntity().apply(precio);
        precioEntity = precioEntityRepository.save(precioEntity);
        return mapperUtils.mapperToPrecio().apply(precioEntity);
    }

    @Override
    public Optional<Precio> findByIdProductoAndIdModalidadAndFechaFinIsNull(Integer idProducto, Integer idModalidad) {
        Optional<ProductoEntity> productoEntity = productoEntityRepository.findById(idProducto);
        Optional<ModalidadEntity> modalidadEntity = modalidadEntityRepository.findById(idModalidad);

        if(productoEntity.isEmpty() || modalidadEntity.isEmpty())
            return Optional.empty();

        PrecioEntity precioEntity = precioEntityRepository.findByIdProductoEntityAndIdModalidadEntityAndFechaFinIsNull(productoEntity.get(), modalidadEntity.get());

        if (precioEntity == null)
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToPrecio().apply(precioEntity));
    }

    @Override
    public List<Precio> findAllByIdProductoEntityAndIdModalidadEntity(Integer idProducto, Integer idModalidad) {
        Optional<ProductoEntity> productoEntity = productoEntityRepository.findById(idProducto);
        Optional<ModalidadEntity> modalidadEntity = modalidadEntityRepository.findById(idModalidad);

        if(productoEntity.isEmpty() || modalidadEntity.isEmpty())
            return new ArrayList<>();

        List<PrecioEntity> precioEntities = precioEntityRepository.findAllByIdProductoEntityAndIdModalidadEntity(productoEntity.get(), modalidadEntity.get());

        return precioEntities.stream().map(precioEntity -> mapperUtils.mapperToPrecio().apply(precioEntity)).collect(Collectors.toList());
    }
}
