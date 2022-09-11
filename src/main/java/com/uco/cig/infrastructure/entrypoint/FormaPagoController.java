package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.usecase.formapago.ListarFormasPagoUseCase;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<FormaPago>> listar(){
        List<FormaPago> response = listarFormasPagoUseCase.listar();

        return ResponseEntity.ok(response);
    }
}
