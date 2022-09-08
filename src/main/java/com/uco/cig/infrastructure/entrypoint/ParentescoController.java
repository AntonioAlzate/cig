package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.usecase.parentesco.ListarParentescosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/parentescos")
public class ParentescoController {

    private final ListarParentescosUseCase listarParentescosUseCase;

    public ParentescoController(ListarParentescosUseCase listarParentescosUseCase) {
        this.listarParentescosUseCase = listarParentescosUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Parentesco>> listar() {
        List<Parentesco> parentescos = listarParentescosUseCase.listar();

        return ResponseEntity.ok(parentescos);
    }
}
