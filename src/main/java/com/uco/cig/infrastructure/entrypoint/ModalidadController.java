package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.usecase.modalidad.ListarModalidadesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/modalidades")
public class ModalidadController {

    private final ListarModalidadesUseCase listarModalidadesUseCase;

    public ModalidadController(ListarModalidadesUseCase listarModalidadesUseCase) {
        this.listarModalidadesUseCase = listarModalidadesUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Modalidad>> listar() {
        List<Modalidad> response = listarModalidadesUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
