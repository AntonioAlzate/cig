package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.usecase.barrio.ListarBarriosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barrios")
public class BarrioController {

    private final ListarBarriosUseCase listarBarriosUseCase;

    public BarrioController(ListarBarriosUseCase listarBarriosUseCase) {
        this.listarBarriosUseCase = listarBarriosUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Barrio>> listar() {
        List<Barrio> response = listarBarriosUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
