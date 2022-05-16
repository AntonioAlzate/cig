package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.producto.Producto;
import com.uco.cig.usecase.producto.ListarProductosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ListarProductosUseCase listarProductosUseCase;

    public ProductoController(ListarProductosUseCase listarProductosUseCase) {
        this.listarProductosUseCase = listarProductosUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = listarProductosUseCase.listar();

        return ResponseEntity.ok(productos);
    }
}
