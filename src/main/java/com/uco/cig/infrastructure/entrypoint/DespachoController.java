package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.usecase.despacho.ObtenerRegistrosConDetallesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/despachos")
public class DespachoController {

    private final ObtenerRegistrosConDetallesUseCase obtenerRegistrosConDetallesUseCase;

    public DespachoController(ObtenerRegistrosConDetallesUseCase obtenerRegistrosConDetallesUseCase) {
        this.obtenerRegistrosConDetallesUseCase = obtenerRegistrosConDetallesUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping
    public ResponseEntity<List<RegistroDespacho>> listar(){
        List<RegistroDespacho> registros = obtenerRegistrosConDetallesUseCase.registrosConDetalles();

        return ResponseEntity.ok(registros);
    }
}
