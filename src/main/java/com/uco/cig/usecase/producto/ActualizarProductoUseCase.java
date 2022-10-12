package com.uco.cig.usecase.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.shared.dtos.PrecioCreacionDTO;
import com.uco.cig.shared.dtos.ProductoCreacionDto;
import com.uco.cig.usecase.precio.CrearPrecioUseCase;
import com.uco.cig.usecase.precio.ObtenerPrecioActualDeProductoUseCase;
import com.uco.cig.usecase.producto.color.ListarColoresUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class ActualizarProductoUseCase {

    private static final String PRODUCTO_NO_ENCONTRADO = "El producto que intenta actualizar no se ha encontrado";

    private final ProductoRepository productoRepository;
    private final DimensionRepository dimensionRepository;
    private final CategoriaRepository categoriaRepository;
    private final CrearPrecioUseCase crearPrecioUseCase;
    private final ListarColoresUseCase listarColoresUseCase;
    private final ObtenerPrecioActualDeProductoUseCase obtenerPrecioActualDeProductoUseCase;

    public ActualizarProductoUseCase(ProductoRepository productoRepository, DimensionRepository dimensionRepository, CategoriaRepository categoriaRepository, CrearPrecioUseCase crearPrecioUseCase, ListarColoresUseCase listarColoresUseCase, ObtenerPrecioActualDeProductoUseCase obtenerPrecioActualDeProductoUseCase) {
        this.productoRepository = productoRepository;
        this.dimensionRepository = dimensionRepository;
        this.categoriaRepository = categoriaRepository;
        this.crearPrecioUseCase = crearPrecioUseCase;
        this.listarColoresUseCase = listarColoresUseCase;
        this.obtenerPrecioActualDeProductoUseCase = obtenerPrecioActualDeProductoUseCase;
    }

    public Producto actualizar(ProductoCreacionDto productoCreacionDto, Integer id) throws BusinessException {
        Optional<Producto> producto = productoRepository.findById(id);

        if(producto.isEmpty())
            throw new NotFoundException(PRODUCTO_NO_ENCONTRADO);

        Color color = listarColoresUseCase.listar().stream().filter(c -> Objects.equals(c.getId(), productoCreacionDto.getIdColor())).findFirst().orElse(null);

        producto = pasarDatosDTO(producto.get(), productoCreacionDto, color);

        return productoRepository.update(producto.get(), color.getId());
    }

    private Optional<Producto> pasarDatosDTO(Producto producto, ProductoCreacionDto productoCreacionDto, Color color) throws BusinessException {
        Dimension dimension = dimensionRepository.findById(productoCreacionDto.getIdDimension()).orElse(null);
        Categoria categoria = categoriaRepository.findById(productoCreacionDto.getIdCategoria()).orElse(null);
        Precio precioCredito = obtenerPrecioActualDeProductoUseCase.obtener(producto.getId(), 1);
        Precio precioContado = obtenerPrecioActualDeProductoUseCase.obtener(producto.getId(), 2);

        if(yaExisteProductoConReferencia(productoCreacionDto.getReferencia(), producto)) {
            throw new BusinessException("Ya existe un producto con la misma referencia");
        }

        if(dimension == null)
            throw new BusinessException("Dimensión no encontrada en el sistema");

        if(categoria == null)
            throw new BusinessException("Categpría no encontrada en el sistema");

        if(color == null)
            throw new BusinessException("Color no encontrado en el sistema");

        producto.setNombre(productoCreacionDto.getNombre().trim().toUpperCase());
        producto.setReferencia(productoCreacionDto.getReferencia().trim());
        producto.setDescripcion(productoCreacionDto.getDescripcion().trim());
        producto.setDimension(dimension);
        producto.setCategoria(categoria);
        producto.setColor(color);
        producto.setCantidadExistente(productoCreacionDto.getCantidad());

        Producto productoValidado =
                Producto.construir(producto.getId(),
                        producto.getNombre(),
                        producto.getReferencia(),
                        producto.getDescripcion(),
                        producto.getEstado(),
                        producto.getDimension(),
                        producto.getCategoria(),
                        producto.getCantidadExistente()
                );

        if(!Objects.equals(precioCredito.getValor(), productoCreacionDto.getValorCredito())){
            crearPrecioUseCase.crear(
                    new PrecioCreacionDTO(
                            LocalDate.now(),
                            productoCreacionDto.getValorCredito(),
                            1,
                            producto.getId()));
        }

        if(!Objects.equals(precioContado.getValor(), productoCreacionDto.getValorContado())){
            crearPrecioUseCase.crear(
                    new PrecioCreacionDTO(
                            LocalDate.now(),
                            productoCreacionDto.getValorContado(),
                            2,
                            producto.getId()));
        }

        return Optional.of(productoValidado);
    }

    private boolean yaExisteProductoConReferencia(String referencia, Producto producto) {
        Optional<Producto> productoDespues = productoRepository.findByReferencia(referencia);

        if(productoDespues.isEmpty())
            return false;

        return !producto.getId().equals(productoDespues.get().getId());
    }
}
