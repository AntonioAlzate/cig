package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.shared.dtos.PrecioCreacionDTO;
import com.uco.cig.usecase.precio.CrearPrecioUseCase;
import com.uco.cig.usecase.precio.ObtenerPrecioActualDeProductoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/precios")
public class PrecioController {

    private final ObtenerPrecioActualDeProductoUseCase obtenerPrecioActualDeProductoUseCase;
    private final CrearPrecioUseCase crearPrecioUseCase;

    public PrecioController(ObtenerPrecioActualDeProductoUseCase obtenerPrecioActualDeProductoUseCase, CrearPrecioUseCase crearPrecioUseCase) {
        this.obtenerPrecioActualDeProductoUseCase = obtenerPrecioActualDeProductoUseCase;
        this.crearPrecioUseCase = crearPrecioUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/producto/{idProducto}/modalidad/{idModalidad}")
    public ResponseEntity<Precio> obtenerPrecioProducto(@PathVariable Integer idProducto, @PathVariable Integer idModalidad){
        Precio precio = obtenerPrecioActualDeProductoUseCase.obtener(idProducto, idModalidad);

        return ResponseEntity.ok(precio);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PostMapping("/crear")
    public ResponseEntity<Precio> crearPrecio(@RequestBody PrecioCreacionDTO precioCreacionDTO) throws BusinessException {
        Precio precio = crearPrecioUseCase.crear(precioCreacionDTO);
        URI location = URI.create("precio/" + precio.getId());
        return ResponseEntity.created(location).body(precio);
    }
}
