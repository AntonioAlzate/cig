package com.uco.cig.usecase.precio;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.precio.ports.PrecioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObtenerPrecioActualDeProductoUseCase {

    private static final String PRECIO_NO_CONFIGURADO = "No se encontró un precio vigente para el producto especificado";

    private final PrecioRepository precioRepository;

    public ObtenerPrecioActualDeProductoUseCase(PrecioRepository precioRepository) {
        this.precioRepository = precioRepository;
    }

    public Precio obtener(Integer idProducto, Integer idModalidad){

        List<Precio> preciosProducto = precioRepository.findAllByIdProductoEntityAndIdModalidadEntity(idProducto, idModalidad);

        if(preciosProducto.isEmpty())
            throw new NotFoundException(PRECIO_NO_CONFIGURADO);

        Optional<Precio> precioVigente = obtenerPrecioVigente(preciosProducto);

        if(precioVigente.isEmpty())
            throw new NotFoundException(PRECIO_NO_CONFIGURADO);

        return precioVigente.get();
    }

    private Optional<Precio> obtenerPrecioVigente(List<Precio> preciosProducto) {
        LocalDate fechaActual = LocalDate.now();

        return preciosProducto.stream()
                .filter(precio -> {
                    LocalDate fechaInicio = precio.getFechaInicio() == null ? LocalDate.now() : precio.getFechaInicio();
                    LocalDate fechaFin =  precio.getFechaFin() == null ? LocalDate.now() : precio.getFechaFin();
                    return (fechaActual.isAfter(fechaInicio) || fechaActual.isEqual(fechaInicio)) && (fechaActual.isBefore(fechaFin) || fechaActual.isEqual(fechaFin));
                }).findFirst();
    }
}
