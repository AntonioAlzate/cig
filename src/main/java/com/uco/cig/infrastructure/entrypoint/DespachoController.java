package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.usecase.despacho.ObtenerRegistrosConDetallesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/despachos")
public class DespachoController {

    private final ObtenerRegistrosConDetallesUseCase obtenerRegistrosConDetallesUseCase;

    public DespachoController(ObtenerRegistrosConDetallesUseCase obtenerRegistrosConDetallesUseCase) {
        this.obtenerRegistrosConDetallesUseCase = obtenerRegistrosConDetallesUseCase;
    }

    @GetMapping
    public ResponseEntity<List<RegistroDespacho>> listar(){
        List<RegistroDespacho> registros = obtenerRegistrosConDetallesUseCase.registrosConDetalles();

        return ResponseEntity.ok(registros);
    }
}
