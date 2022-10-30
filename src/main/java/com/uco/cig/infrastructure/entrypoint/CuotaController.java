package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.shared.dtos.AbonoPagoDTO;
import com.uco.cig.shared.dtos.CuotaPagoDTO;
import com.uco.cig.usecase.cuota.ListarCuotasUseCase;
import com.uco.cig.usecase.cuota.ListarCuotasVentaUseCase;
import com.uco.cig.usecase.cuota.PagarCuotaProximaUseCase;
import com.uco.cig.usecase.cuota.RealizarAbonoCuentaUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/cuotas")
@Slf4j
public class CuotaController {

    private final ListarCuotasUseCase listarCuotasUseCase;
    private final PagarCuotaProximaUseCase pagarCuotaProximaUseCase;
    private final RealizarAbonoCuentaUseCase realizarAbonoCuentaUseCase;
    private final ListarCuotasVentaUseCase listarCuotasVentaUseCase;

    public CuotaController(ListarCuotasUseCase listarCuotasUseCase, PagarCuotaProximaUseCase pagarCuotaProximaUseCase, RealizarAbonoCuentaUseCase realizarAbonoCuentaUseCase, ListarCuotasVentaUseCase listarCuotasVentaUseCase) {
        this.listarCuotasUseCase = listarCuotasUseCase;
        this.pagarCuotaProximaUseCase = pagarCuotaProximaUseCase;
        this.realizarAbonoCuentaUseCase = realizarAbonoCuentaUseCase;
        this.listarCuotasVentaUseCase = listarCuotasVentaUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping
    public ResponseEntity<List<Cuota>> listar(){
        List<Cuota> cuotas = listarCuotasUseCase.listar();

        return ResponseEntity.ok(cuotas);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<Cuota>> listarPorIdVenta(@PathVariable Integer idVenta){
        List<Cuota> cuotas = listarCuotasVentaUseCase.listar(idVenta);

        return ResponseEntity.ok(cuotas);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-cobrador')")
    @PostMapping("/pago-cuota")
    public ResponseEntity<Cuota> pagarCuotaProxima(@RequestBody CuotaPagoDTO cuotaPagoDTO) throws BusinessException {
        log.info("Pago de cuota: {}", cuotaPagoDTO);
        Cuota result = pagarCuotaProximaUseCase.pagar(cuotaPagoDTO);

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-cobrador')")
    @PostMapping("/abono-cuenta")
    public ResponseEntity<String> realizarAbonoCuenta(@RequestBody AbonoPagoDTO abonoPagoDTO) throws BusinessException {
        log.info("Realización de abono: {}", abonoPagoDTO);
        String result = realizarAbonoCuentaUseCase.abonar(abonoPagoDTO);

        return ResponseEntity.ok(result);
    }
}
