package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.usecase.cuota.ListarCuotasUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuotas")
public class CuotaController {

    private final ListarCuotasUseCase listarCuotasUseCase;

    public CuotaController(ListarCuotasUseCase listarCuotasUseCase) {
        this.listarCuotasUseCase = listarCuotasUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Cuota>> listar(){
        List<Cuota> cuotas = listarCuotasUseCase.listar();

        return ResponseEntity.ok(cuotas);
    }
}
