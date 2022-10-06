package com.uco.cig.usecase.precio;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.modalidad.ports.ModalidadRepository;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.precio.ports.PrecioRepository;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.shared.dtos.PrecioCreacionDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CrearPrecioUseCase {

    private static final String FECHA_INICIO_ILEGAL = "Ya existe un precio configurado para la fecha especificada";
    private final PrecioRepository precioRepository;
    private final ModalidadRepository modalidadRepository;
    private final ProductoRepository productoRepository;

    public CrearPrecioUseCase(PrecioRepository precioRepository, ModalidadRepository modalidadRepository, ProductoRepository productoRepository) {
        this.precioRepository = precioRepository;
        this.modalidadRepository = modalidadRepository;
        this.productoRepository = productoRepository;
    }

    public Precio crear(PrecioCreacionDTO precioCreacionDTO) throws BusinessException {
        Integer idModalidad = precioCreacionDTO.getIdModalidad();
        Integer idProducto = precioCreacionDTO.getIdProducto();

        Optional<Modalidad> modalidad = modalidadRepository.findById(idModalidad);
        Optional<Producto> producto = productoRepository.findById(idProducto);

        if (modalidad.isEmpty()) {
            throw new NotFoundException("No se ha encontrado la modalidad especificada");
        }

        if (producto.isEmpty()) {
            throw new NotFoundException("No se ha encontrado el producto especificado");
        }

        List<Precio> preciosProducto = precioRepository.findAllByIdProductoEntityAndIdModalidadEntity(idProducto, idModalidad);

        // Si es el primer precio
        if (preciosProducto.isEmpty()) {
            Precio precio = Precio.nuevo(LocalDate.now(), null, precioCreacionDTO.getValor(), modalidad.get(), producto.get());

            return precioRepository.save(precio);
        }

        Optional<Precio> precioActual = precioRepository.findByIdProductoAndIdModalidadAndFechaFinIsNull(idProducto, idModalidad);

        if (precioActual.isPresent()) {

            // Si la fecha de inicio especificada es la de hoy la pongo para regir desde mañana sino se deja la que llega
            precioCreacionDTO.setFechaInicio(
                    precioCreacionDTO.getFechaInicio().getDayOfYear() == (LocalDate.now().getDayOfYear()) ?
                            precioCreacionDTO.getFechaInicio().plusDays(1) :
                            precioCreacionDTO.getFechaInicio()
            );

            // Valido que la fecha enviada no sea menor o igual a la fecha de inicio de un precio ya configurado
            if (precioCreacionDTO.getFechaInicio().isBefore(precioActual.get().getFechaInicio())
                    || precioCreacionDTO.getFechaInicio().isEqual(precioActual.get().getFechaInicio())) {
                throw new BadRequestException(FECHA_INICIO_ILEGAL);
            }

            // Actualizo el precio que estaba vigente para ponerle fecha final
            precioActual.get().setFechaFin(precioCreacionDTO.getFechaInicio().minusDays(1));
            precioRepository.save(precioActual.get());
        }

        Precio precio = Precio.nuevo(precioCreacionDTO.getFechaInicio(), null, precioCreacionDTO.getValor(), modalidad.get(), producto.get());

        return precioRepository.save(precio);
    }
}
