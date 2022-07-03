package com.uco.cig.infrastructure.database.postgres.adapter.producto;

import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ColorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ColorEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.ColorProductoEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.ProductoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoRepositoryAdapter implements ProductoRepository {

    private final ProductoEntityRepository productoEntityRepository;
    private final ColorEntityRepository colorEntityRepository;
    private final ColorProductoEntityRepository colorProductoEntityRepository;
    private final MapperUtils mapperUtils;

    public ProductoRepositoryAdapter(ProductoEntityRepository productoEntityRepository, ColorEntityRepository colorEntityRepository, ColorProductoEntityRepository colorProductoEntityRepository, MapperUtils mapperUtils) {
        this.productoEntityRepository = productoEntityRepository;
        this.colorEntityRepository = colorEntityRepository;
        this.colorProductoEntityRepository = colorProductoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Producto save(Producto producto) {

        ProductoEntity productoEntity = mapperUtils.mappertoProductoEntity().apply(producto);
        productoEntity = productoEntityRepository.save(productoEntity);

        return mapperUtils.mapperToProducto().apply(productoEntity);
    }

    @Override
    public List<Producto> findAll() {

        List<ProductoEntity> productosEntity = productoEntityRepository.findAll();
        List<Producto> productos = productosEntity.stream().map(p -> {
            ColorEntity colorEntity = colorProductoEntityRepository.findColorProductoEntityById_IdProducto(p);
            Producto producto = mapperUtils.mapperToProducto().apply(p);
            producto.setColor(mapperUtils.mapperToColor().apply(colorEntity));
            return producto;
        }).collect(Collectors.toList());

        return productos;
    }

    @Override
    public List<Producto> findAll(int page, int size, String order, boolean asc) {

        Page<ProductoEntity> productoEntitiesPage =
                asc ? productoEntityRepository.findAll(PageRequest.of(page, size, Sort.by(order)))
                : productoEntityRepository.findAll(PageRequest.of(page, size, Sort.by(order).descending()));

        List<ProductoEntity> productoEntities = productoEntitiesPage.getContent();

        return productoEntities.stream().map(p -> mapperUtils.mapperToProducto().apply(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findByReferencia(String referencia) {
        ProductoEntity productoEntity = productoEntityRepository.findByReferencia(referencia);

        if (productoEntity == null)
            return Optional.empty();


        return Optional.of(mapperUtils.mapperToProducto().apply(productoEntity));
    }
}
