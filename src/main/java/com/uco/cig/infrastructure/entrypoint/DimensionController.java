package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.usecase.producto.dimesion.ListarDimensionesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/dimensiones")
public class DimensionController {

    private final ListarDimensionesUseCase listarDimensionesUseCase;


    public DimensionController(ListarDimensionesUseCase listarDimensionesUseCase) {
        this.listarDimensionesUseCase = listarDimensionesUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Dimension>> listar() {
        List<Dimension> dimensiones = listarDimensionesUseCase.listar();

        return ResponseEntity.ok(dimensiones);
    }
}