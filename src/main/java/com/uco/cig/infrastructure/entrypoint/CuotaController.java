package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.shared.dtos.AbonoPagoDTO;
import com.uco.cig.shared.dtos.CuotaPagoDTO;
import com.uco.cig.usecase.cuota.ListarCuotasUseCase;
import com.uco.cig.usecase.cuota.PagarCuotaProximaUseCase;
import com.uco.cig.usecase.cuota.RealizarAbonoCuentaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/cuotas")
public class CuotaController {

    private final ListarCuotasUseCase listarCuotasUseCase;
    private final PagarCuotaProximaUseCase pagarCuotaProximaUseCase;
    private final RealizarAbonoCuentaUseCase realizarAbonoCuentaUseCase;

    public CuotaController(ListarCuotasUseCase listarCuotasUseCase, PagarCuotaProximaUseCase pagarCuotaProximaUseCase, RealizarAbonoCuentaUseCase realizarAbonoCuentaUseCase) {
        this.listarCuotasUseCase = listarCuotasUseCase;
        this.pagarCuotaProximaUseCase = pagarCuotaProximaUseCase;
        this.realizarAbonoCuentaUseCase = realizarAbonoCuentaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Cuota>> listar(){
        List<Cuota> cuotas = listarCuotasUseCase.listar();

        return ResponseEntity.ok(cuotas);
    }

    @GetMapping("/pago-cuota")
    public ResponseEntity<Cuota> pagarCuotaProxima(@RequestBody CuotaPagoDTO cuotaPagoDTO) throws BusinessException {
        Cuota result = pagarCuotaProximaUseCase.pagar(cuotaPagoDTO);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/abono-cuenta")
    public ResponseEntity<String> realizarAbonoCuenta(@RequestBody AbonoPagoDTO abonoPagoDTO) throws BusinessException {
        String result = realizarAbonoCuentaUseCase.abonar(abonoPagoDTO);

        return ResponseEntity.ok(result);
    }
}
