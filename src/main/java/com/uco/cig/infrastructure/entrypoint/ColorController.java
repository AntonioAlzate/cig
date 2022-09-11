package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.color.Color;
import com.uco.cig.usecase.producto.color.ListarColoresUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/colores")
public class ColorController {

    private final ListarColoresUseCase listarColoresUseCase;


    public ColorController(ListarColoresUseCase listarColoresUseCase) {
        this.listarColoresUseCase = listarColoresUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping
    public ResponseEntity<List<Color>> listar(){
        List<Color> colores = listarColoresUseCase.listar();

        return ResponseEntity.ok(colores);
    }
}
