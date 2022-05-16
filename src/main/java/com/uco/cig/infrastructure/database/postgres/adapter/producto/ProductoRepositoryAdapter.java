package com.uco.cig.infrastructure.database.postgres.adapter.producto;

import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ProductoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoRepositoryAdapter implements ProductoRepository {

    private final ProductoEntityRepository productoEntityRepository;
    private final MapperUtils mapperUtils;

    public ProductoRepositoryAdapter(ProductoEntityRepository productoEntityRepository, MapperUtils mapperUtils) {
        this.productoEntityRepository = productoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Producto save(Producto producto) {
        return null;
    }

    @Override
    public List<Producto> findAll() {

        List<ProductoEntity> productosEntity = productoEntityRepository.findAll();

        return productosEntity.stream().map(p -> mapperUtils.mapperToProducto().apply(p)).collect(Collectors.toList());
    }
}
