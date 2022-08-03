package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.shared.dtos.PrecioCreacionDTO;
import com.uco.cig.usecase.precio.CrearPrecioUseCase;
import com.uco.cig.usecase.precio.TraerPrecioActualProductoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/precios")
public class PrecioController {

    private final TraerPrecioActualProductoUseCase traerPrecioActualProductoUseCase;
    private final CrearPrecioUseCase crearPrecioUseCase;

    public PrecioController(TraerPrecioActualProductoUseCase traerPrecioActualProductoUseCase, CrearPrecioUseCase crearPrecioUseCase) {
        this.traerPrecioActualProductoUseCase = traerPrecioActualProductoUseCase;
        this.crearPrecioUseCase = crearPrecioUseCase;
    }

    @GetMapping("/producto/{idProducto}/modalidad/{idModalidad}")
    public ResponseEntity<Precio> obtenerPrecioProducto(@PathVariable Integer idProducto, @PathVariable Integer idModalidad){
        Precio precio = traerPrecioActualProductoUseCase.precioActualDeProducto(idProducto, idModalidad);

        return ResponseEntity.ok(precio);
    }

    @PostMapping("/crear")
    public ResponseEntity<Precio> crearPrecio(@RequestBody PrecioCreacionDTO precioCreacionDTO) throws BusinessException {
        Precio precio = crearPrecioUseCase.crear(precioCreacionDTO);
        URI location = URI.create("precio/" + precio.getId());
        return ResponseEntity.created(location).body(precio);
    }
}
