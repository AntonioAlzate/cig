package com.uco.cig.infrastructure.database.postgres.adapter.producto;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.*;
import com.uco.cig.infrastructure.database.postgres.repositories.*;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductoRepositoryAdapter implements ProductoRepository {

    private final ProductoEntityRepository productoEntityRepository;
    private final ColorEntityRepository colorEntityRepository;
    private final ColorProductoEntityRepository colorProductoEntityRepository;
    private final EstadoEntityRepository estadoEntityRepository;
    private final DimensionEntityRepository dimensionEntityRepository;
    private final CategoriaEntityRepository categoriaEntityRepository;
    private final MapperUtils mapperUtils;

    public ProductoRepositoryAdapter(ProductoEntityRepository productoEntityRepository, ColorEntityRepository colorEntityRepository, ColorProductoEntityRepository colorProductoEntityRepository, EstadoEntityRepository estadoEntityRepository, DimensionEntityRepository dimensionEntityRepository, CategoriaEntityRepository categoriaEntityRepository, MapperUtils mapperUtils) {
        this.productoEntityRepository = productoEntityRepository;
        this.colorEntityRepository = colorEntityRepository;
        this.colorProductoEntityRepository = colorProductoEntityRepository;
        this.estadoEntityRepository = estadoEntityRepository;
        this.dimensionEntityRepository = dimensionEntityRepository;
        this.categoriaEntityRepository = categoriaEntityRepository;
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

    @Override
    public Producto cambiarEstado(Integer idProducto, String estadoNuevo) {
        Optional<ProductoEntity> productoEntity = productoEntityRepository.findById(idProducto);

        if(productoEntity.isEmpty())
            throw new NotFoundException("Producto no encontrado");

        EstadoEntity estadoEntity = estadoEntityRepository.findByNombre(estadoNuevo);
        productoEntity.get().setIdEstado(estadoEntity);

        return mapperUtils.mapperToProducto().apply(productoEntityRepository.save(productoEntity.get()));
    }

    @Override
    public Producto update(Producto producto, Integer idColor) {
        ProductoEntity productoEntity = mapperUtils.mappertoProductoEntity().apply(producto);
        productoEntity.setIdDimension(dimensionEntityRepository.save(productoEntity.getIdDimension()));
        productoEntity.setIdCategoria(categoriaEntityRepository.save(productoEntity.getIdCategoria()));
        ColorProductoEntity colorProductoEntity = colorProductoEntityRepository.findColorProductoEntityById_IdProducto(producto.getId());

        if(!Objects.equals(colorProductoEntity.getId().getIdColor(), idColor)) {
            colorProductoEntityRepository.delete(colorProductoEntity);
            colorProductoEntityRepository.save(new ColorProductoEntity(new ColorProductoId(idColor, producto.getId())));
        }

        productoEntity = productoEntityRepository.save(productoEntity);
        return mapperUtils.mapperToProducto().apply(productoEntity);
    }
}
