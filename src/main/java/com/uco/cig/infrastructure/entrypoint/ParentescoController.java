package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.usecase.parentesco.ListarParentescosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping
    public ResponseEntity<List<Parentesco>> listar() {
        List<Parentesco> parentescos = listarParentescosUseCase.listar();

        return ResponseEntity.ok(parentescos);
    }
}
