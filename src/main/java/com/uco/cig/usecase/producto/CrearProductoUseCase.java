package com.uco.cig.usecase.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.color.ports.ColorRepository;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.shared.dtos.ProductoCreacionDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class CrearProductoUseCase {

    private static final String CATEGORIA_PRODUCTO_INEXISTENTE = "La categoría enviada no existe en el sistema";
    private static final String PRODUCTO_CON_REFERENCIA_YA_REGISTRADO = "La referencia enviada ya se encuentra asignada un producto";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";

    private final DimensionRepository dimensionRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final EstadoRepository estadoRepository;
    private final ColorRepository colorRepository;


    public CrearProductoUseCase(DimensionRepository dimensionRepository, CategoriaRepository categoriaRepository, ProductoRepository productoRepository, EstadoRepository estadoRepository, ColorRepository colorRepository) {
        this.dimensionRepository = dimensionRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.estadoRepository = estadoRepository;
        this.colorRepository = colorRepository;
    }

    public Producto crearProducto(ProductoCreacionDto productoCreacionDto) throws BusinessException {

        Optional<Categoria> categoria = categoriaRepository.findById(productoCreacionDto.getIdCategoria());

        if (categoria.isEmpty())
            throw new NotFoundException(CATEGORIA_PRODUCTO_INEXISTENTE);

        Optional<Dimension> dimension = dimensionRepository.findById(productoCreacionDto.getIdDimension());

        if (dimension.isEmpty()) {
            Dimension dimesionConstruir =
                    Dimension.nuevo(new BigDecimal(productoCreacionDto.getLargo()),
                            new BigDecimal(productoCreacionDto.getAncho()));

            dimension = Optional.of(dimensionRepository.save(dimesionConstruir));
        }

        Optional<Estado> estado =
                Optional.ofNullable(estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString()).orElseThrow(() -> new NotFoundException(ESTADO_NO_ENCONTRADO)));

        Optional<Producto> productoValidar = productoRepository.findByReferencia(productoCreacionDto.getReferencia().toUpperCase());

        if(productoValidar.isPresent()){
            throw new BusinessException(PRODUCTO_CON_REFERENCIA_YA_REGISTRADO);
        }

        Producto productoCrear = Producto.nuevo(productoCreacionDto.getNombre(), productoCreacionDto.getReferencia().toUpperCase(Locale.ROOT), productoCreacionDto.getDescripcion(), estado.get(),
                dimension.get(), categoria.get());

        Optional<Color> color = colorRepository.findById(productoCreacionDto.getIdColor());

        if(color.isEmpty()){
            Color colorConstruir = new Color(null, productoCreacionDto.getNombreColor());

            color = Optional.of(colorRepository.save(colorConstruir));
        }

        return productoRepository.save(productoCrear, color.get());
    }
}
