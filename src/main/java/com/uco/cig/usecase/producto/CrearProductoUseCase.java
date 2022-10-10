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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CrearProductoUseCase {

    private static final String PRODUCTO_CON_REFERENCIA_YA_REGISTRADO = "La referencia enviada ya se encuentra asignada un producto";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String CATEGORIA_NOMBRE_EXISTENTE = "Ya existe una categoría con el mismo nombre registrada en el sistema";
    private static final String COLOR_NOMBRE_YA_REGISTRADO = "Ya existe un color con el mismo nombre registrado en el sistema";
    private static final String DIMENSION_YA_REGISTRADA = "Ya existe una dimensión con el mismo largo y ancho en el sistema";

    private static final Integer ID_MODALIDAD_CREDITO = 1;
    private static final Integer ID_MODALIDAD_CONTADO = 2;

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

        validarNoExistenciaProducto(productoCreacionDto.getReferencia().trim().toUpperCase());

        Categoria categoria = getCategoria(productoCreacionDto);

        Dimension dimension = getDimension(productoCreacionDto, categoria);

        Optional<Estado> estado = getEstado();

        Producto productoCrear = Producto.nuevo(
                productoCreacionDto.getNombre().trim().toUpperCase(),
                productoCreacionDto.getReferencia().trim().toUpperCase(),
                productoCreacionDto.getDescripcion().trim(),
                estado.get(),
                dimension,
                categoria,
                productoCreacionDto.getCantidad());

        Color color = getColorOCrear(productoCreacionDto);

        Producto productoCreado = productoRepository.save(productoCrear, color);

        crearPrecioProducto(productoCreado.getId(), productoCreacionDto.getValorContado(), ID_MODALIDAD_CONTADO);
        crearPrecioProducto(productoCreado.getId(), productoCreacionDto.getValorCredito(), ID_MODALIDAD_CREDITO);

        return productoCreado;
    }

    private void crearPrecioProducto(Integer idProducto, BigDecimal valor, Integer idModalidad) throws BusinessException {

        PrecioCreacionDTO precioCreacionDTO = new PrecioCreacionDTO(LocalDate.now(), valor, idModalidad, idProducto);
        crearPrecioUseCase.crear(precioCreacionDTO);
    }

    private Color getColorOCrear(ProductoCreacionDto productoCreacionDto) throws BusinessException {
        Optional<Color> color = colorRepository.findById(productoCreacionDto.getIdColor());

        String valNombreColor = productoCreacionDto.getNombreColor().trim().toUpperCase();

        if (color.isEmpty()) {

            Color colorPorNombre = colorRepository.findByNombre(valNombreColor);

            if(colorPorNombre != null)
                throw new BusinessException(COLOR_NOMBRE_YA_REGISTRADO);

            Color colorConstruir = new Color(null, valNombreColor);

            color = Optional.of(colorRepository.save(colorConstruir));
        }
        return color.get();
    }

    private Optional<Estado> getEstado() {
        Optional<Estado> estado = estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString());

        if (estado.isEmpty())
            throw new NotFoundException(ESTADO_NO_ENCONTRADO);
        return estado;
    }

    private Dimension getDimension(ProductoCreacionDto productoCreacionDto, Categoria categoria) throws BusinessException {
        Optional<Dimension> dimension = dimensionRepository.findById(productoCreacionDto.getIdDimension());

        if (dimension.isEmpty()) {

            Dimension dimencionPorAnchoLargo = dimensionRepository.findByAnchoAndLargoAndCategoria(productoCreacionDto.getAncho(), productoCreacionDto.getLargo(), categoria);

            if(dimencionPorAnchoLargo != null)
                throw new BusinessException(DIMENSION_YA_REGISTRADA);

            Dimension dimesionConstruir =
                    Dimension.nuevo(productoCreacionDto.getLargo(),
                            productoCreacionDto.getAncho(),
                            categoria
                    );

            dimension = Optional.of(dimensionRepository.save(dimesionConstruir));
        }
        return dimension.get();
    }

    private Categoria getCategoria(ProductoCreacionDto productoCreacionDto) throws BusinessException {
        Optional<Categoria> categoria = categoriaRepository.findById(productoCreacionDto.getIdCategoria());

        String valNombreCategoria = productoCreacionDto.getNombreCategoria().trim().toUpperCase();

        if (categoria.isEmpty()){

            Categoria categoriaPorNombre = categoriaRepository.findByNombre(valNombreCategoria);

            if(categoriaPorNombre != null)
                throw new BusinessException(CATEGORIA_NOMBRE_EXISTENTE);

            Categoria categoriaConstruir =
                    Categoria.nuevo(valNombreCategoria);

            categoria = Optional.of(categoriaRepository.save(categoriaConstruir));
        }

        return categoria.get();
    }

    private void validarNoExistenciaProducto(String referencia) throws BusinessException {
        Optional<Producto> productoValidar = productoRepository.findByReferencia(referencia.trim().toUpperCase());

        if (productoValidar.isPresent()) {
            throw new BusinessException(PRODUCTO_CON_REFERENCIA_YA_REGISTRADO);
        }
    }
}
