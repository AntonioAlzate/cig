package com.uco.cig.infrastructure.database.postgres.adapter.producto;

import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ColorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ColorProductoId;
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
    public Producto save(Producto producto, Color color) {

        ProductoEntity productoEntity = mapperUtils.mappertoProductoEntity().apply(producto);
        productoEntity = productoEntityRepository.save(productoEntity);

        // Enlazar producto y color
        ColorProductoId colorProductoId = new ColorProductoId();
        colorProductoId.setIdProducto(productoEntity.getId());
        colorProductoId.setIdColor(color.getId());

        ColorProductoEntity colorProductoEntity = new ColorProductoEntity();
        colorProductoEntity.setId(colorProductoId);

        colorProductoEntityRepository.save(colorProductoEntity);

        Producto productoCreado = mapperUtils.mapperToProducto().apply(productoEntity);
        productoCreado.setColor(color);

        return productoCreado;
    }

    @Override
    public List<Producto> findAll() {

        List<ProductoEntity> productosEntity = productoEntityRepository.findAll();
        return getProductosConColores(productosEntity);
    }

    private List<Producto> getProductosConColores(List<ProductoEntity> productosEntity) {
        return productosEntity.stream().map(p -> {
            ColorProductoEntity colorProductoEntity = colorProductoEntityRepository.findColorProductoEntityById_IdProducto(p.getId());
            Optional<ColorEntity> colorEntity = colorEntityRepository.findById(colorProductoEntity.getId().getIdColor());
            Producto producto = mapperUtils.mapperToProducto().apply(p);
            producto.setColor(mapperUtils.mapperToColor().apply(colorEntity.get()));
            return producto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Producto> findAll(int page, int size, String order, boolean asc) {

        Page<ProductoEntity> productoEntitiesPage =
                asc ? productoEntityRepository.findAll(PageRequest.of(page, size, Sort.by(order)))
                : productoEntityRepository.findAll(PageRequest.of(page, size, Sort.by(order).descending()));

        List<ProductoEntity> productoEntities = productoEntitiesPage.getContent();

        return getProductosConColores(productoEntities);
    }

    @Override
    public Optional<Producto> findByReferencia(String referencia) {
        ProductoEntity productoEntity = productoEntityRepository.findByReferencia(referencia);

        if (productoEntity == null)
            return Optional.empty();


        return Optional.of(mapperUtils.mapperToProducto().apply(productoEntity));
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        Optional<ProductoEntity> productoEntity =  productoEntityRepository.findById(id);

        if (productoEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToProducto().apply(productoEntity.get()));
    }
}
