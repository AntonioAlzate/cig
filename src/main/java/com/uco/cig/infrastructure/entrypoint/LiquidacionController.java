package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.usecase.liquidacion.ListarLiquidacionesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/liquidaciones")
public class LiquidacionController {

    private final ListarLiquidacionesUseCase listarLiquidacionesUseCase;

    public LiquidacionController(ListarLiquidacionesUseCase listarLiquidacionesUseCase) {
        this.listarLiquidacionesUseCase = listarLiquidacionesUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Liquidacion>> listar(){
        List<Liquidacion> response = listarLiquidacionesUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
