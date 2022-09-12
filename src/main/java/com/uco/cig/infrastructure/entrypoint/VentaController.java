package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.shared.dtos.CreacionVentaDTO;
import com.uco.cig.usecase.venta.ListarVentasClienteUseCase;
import com.uco.cig.usecase.venta.ListarVentasUseCase;
import com.uco.cig.usecase.venta.RegistarVentaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/ventas")
public class VentaController {

    public final ListarVentasUseCase listarVentasUseCase;
    public final RegistarVentaUseCase registarVentaUseCase;
    public final ListarVentasClienteUseCase listarVentasClienteUseCase;

    public VentaController(ListarVentasUseCase listarVentasUseCase, RegistarVentaUseCase registarVentaUseCase, ListarVentasClienteUseCase listarVentasClienteUseCase) {
        this.listarVentasUseCase = listarVentasUseCase;
        this.registarVentaUseCase = registarVentaUseCase;
        this.listarVentasClienteUseCase = listarVentasClienteUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> response = listarVentasUseCase.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> listarVentasCliente(@PathVariable Integer idCliente) {
        List<Venta> response = listarVentasClienteUseCase.listar(idCliente);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear")
    public ResponseEntity<Venta> realizarVenta(@RequestBody CreacionVentaDTO creacionVentaDTO) throws BusinessException {
        Venta venta = registarVentaUseCase.realizarVenta(creacionVentaDTO);
        URI uri = URI.create("");
        return ResponseEntity.created(uri).body(venta);
    }
}
