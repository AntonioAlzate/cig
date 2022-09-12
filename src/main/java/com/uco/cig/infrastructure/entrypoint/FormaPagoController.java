package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.usecase.formapago.ListarFormasPagoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/fomas-pago")
public class FormaPagoController {

    private final ListarFormasPagoUseCase listarFormasPagoUseCase;

    public FormaPagoController(ListarFormasPagoUseCase listarFormasPagoUseCase) {
        this.listarFormasPagoUseCase = listarFormasPagoUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping()
    public ResponseEntity<List<FormaPago>> listar(){
        List<FormaPago> response = listarFormasPagoUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
