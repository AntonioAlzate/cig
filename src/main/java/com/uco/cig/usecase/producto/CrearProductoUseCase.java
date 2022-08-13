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
import com.uco.cig.shared.dtos.PrecioCreacionDTO;
import com.uco.cig.shared.dtos.ProductoCreacionDto;
import com.uco.cig.usecase.precio.CrearPrecioUseCase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class CrearProductoUseCase {

    private static final String CATEGORIA_PRODUCTO_INEXISTENTE = "La categoría enviada no existe en el sistema";
    private static final String PRODUCTO_CON_REFERENCIA_YA_REGISTRADO = "La referencia enviada ya se encuentra asignada un producto";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";

    private static final Integer idModalidadCredito = 1;
    private static final Integer idModalidadContado = 2;

    private final DimensionRepository dimensionRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final EstadoRepository estadoRepository;
    private final ColorRepository colorRepository;
    private final CrearPrecioUseCase crearPrecioUseCase;


    public CrearProductoUseCase(DimensionRepository dimensionRepository, CategoriaRepository categoriaRepository, ProductoRepository productoRepository, EstadoRepository estadoRepository, ColorRepository colorRepository, CrearPrecioUseCase crearPrecioUseCase) {
        this.dimensionRepository = dimensionRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.estadoRepository = estadoRepository;
        this.colorRepository = colorRepository;
        this.crearPrecioUseCase = crearPrecioUseCase;
    }

    public Producto crearProducto(ProductoCreacionDto productoCreacionDto) throws BusinessException {

        Optional<Categoria> categoria = getCategoria(productoCreacionDto);

        Optional<Dimension> dimension = getDimension(productoCreacionDto);

        Optional<Estado> estado = getEstado();

        validarNoExistenciaProducto(productoCreacionDto.getReferencia().toUpperCase());

        Producto productoCrear = Producto.nuevo(productoCreacionDto.getNombre(), productoCreacionDto.getReferencia().toUpperCase(Locale.ROOT), productoCreacionDto.getDescripcion(), estado.get(),
                dimension.get(), categoria.get(), productoCreacionDto.getCantidad());

        Optional<Color> color = getColorOCrear(productoCreacionDto);

        Producto productoCreado = productoRepository.save(productoCrear, color.get());

        crearPrecioProducto(productoCreado.getId(), productoCreacionDto.getValorContado(), idModalidadContado);
        crearPrecioProducto(productoCreado.getId(), productoCreacionDto.getValorCredito(), idModalidadCredito);

        return productoCreado;
    }

    private void crearPrecioProducto(Integer idProducto, BigDecimal valor, Integer idModalidad) throws BusinessException {

        PrecioCreacionDTO precioCreacionDTO = new PrecioCreacionDTO(LocalDate.now(), valor, idModalidad, idProducto);
        crearPrecioUseCase.crear(precioCreacionDTO);
    }

    private Optional<Color> getColorOCrear(ProductoCreacionDto productoCreacionDto) {
        Optional<Color> color = colorRepository.findById(productoCreacionDto.getIdColor());

        if (color.isEmpty()) {
            Color colorConstruir = new Color(null, productoCreacionDto.getNombreColor());

            color = Optional.of(colorRepository.save(colorConstruir));
        }
        return color;
    }

    private Optional<Estado> getEstado() {
        Optional<Estado> estado = estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString());

        if (estado.isEmpty())
            throw new NotFoundException(ESTADO_NO_ENCONTRADO);
        return estado;
    }

    private Optional<Dimension> getDimension(ProductoCreacionDto productoCreacionDto) throws BusinessException {
        Optional<Dimension> dimension = dimensionRepository.findById(productoCreacionDto.getIdDimension());

        if (dimension.isEmpty()) {
            Dimension dimesionConstruir =
                    Dimension.nuevo(BigDecimal.valueOf(productoCreacionDto.getLargo()),
                            BigDecimal.valueOf(productoCreacionDto.getAncho()));

            dimension = Optional.of(dimensionRepository.save(dimesionConstruir));
        }
        return dimension;
    }

    private Optional<Categoria> getCategoria(ProductoCreacionDto productoCreacionDto) {
        Optional<Categoria> categoria = categoriaRepository.findById(productoCreacionDto.getIdCategoria());

        if (categoria.isEmpty())
            throw new NotFoundException(CATEGORIA_PRODUCTO_INEXISTENTE);
        return categoria;
    }

    private void validarNoExistenciaProducto(String referencia) throws BusinessException {
        Optional<Producto> productoValidar = productoRepository.findByReferencia(referencia);

        if (productoValidar.isPresent()) {
            throw new BusinessException(PRODUCTO_CON_REFERENCIA_YA_REGISTRADO);
        }
    }
}
