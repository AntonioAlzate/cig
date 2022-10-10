package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.usecase.producto.dimesion.ListarDimensionesCategoriaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/dimensiones")
public class DimensionController {

    private final ListarDimensionesCategoriaUseCase listarDimensionesCategoriaUseCase;


    public DimensionController(ListarDimensionesCategoriaUseCase listarDimensionesCategoriaUseCase) {
        this.listarDimensionesCategoriaUseCase = listarDimensionesCategoriaUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/{idCategoria}")
    public ResponseEntity<List<Dimension>> listar(@PathVariable Integer idCategoria) {
        List<Dimension> dimensiones = listarDimensionesCategoriaUseCase.listar(idCategoria);

        return ResponseEntity.ok(dimensiones);
    }
}
