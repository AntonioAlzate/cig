package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.usecase.venta.ListarVentasUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    public final ListarVentasUseCase listarVentasUseCase;

    public VentaController(ListarVentasUseCase listarVentasUseCase) {
        this.listarVentasUseCase = listarVentasUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> response = listarVentasUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
