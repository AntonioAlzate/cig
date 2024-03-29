package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.usecase.producto.categoria.ListarCategoriasUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private final ListarCategoriasUseCase listarCategoriasUseCase;

    public CategoriaController(ListarCategoriasUseCase listarCategoriasUseCase) {
        this.listarCategoriasUseCase = listarCategoriasUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = listarCategoriasUseCase.listar();

        return ResponseEntity.ok(categorias);
    }
}
