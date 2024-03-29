package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.shared.dtos.AsignarBarrioZonaDTO;
import com.uco.cig.usecase.barrio.AsignarBarrioZonaUseCase;
import com.uco.cig.usecase.barrio.ListarBarriosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/barrios")
public class BarrioController {

    private final ListarBarriosUseCase listarBarriosUseCase;
    private final AsignarBarrioZonaUseCase asignarBarrioZonaUseCase;

    public BarrioController(ListarBarriosUseCase listarBarriosUseCase, AsignarBarrioZonaUseCase asignarBarrioZonaUseCase) {
        this.listarBarriosUseCase = listarBarriosUseCase;
        this.asignarBarrioZonaUseCase = asignarBarrioZonaUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping()
    public ResponseEntity<List<Barrio>> listar() {
        List<Barrio> response = listarBarriosUseCase.listar();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PostMapping("/asignar-zona")
    public ResponseEntity<Barrio> asignarBarrio(@RequestBody AsignarBarrioZonaDTO asignarBarrioZonaDTO) throws BusinessException {
        Barrio response = asignarBarrioZonaUseCase.asignarZona(asignarBarrioZonaDTO);

        return ResponseEntity.ok(response);
    }
}
