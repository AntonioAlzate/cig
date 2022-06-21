package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.usecase.cliente.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final CrearClienteUseCase crearClienteUseCase;
    private final ActualizarClienteUseCase actualizarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final ObtenerClientePorIdentificacionUseCase obtenerClientePorIdentificacionUseCase;
    private final CambiarEstadoClienteUseCase cambiarEstadoClienteUseCase;

    public ClienteController(CrearClienteUseCase crearClienteUseCase, ActualizarClienteUseCase actualizarClienteUseCase, ListarClientesUseCase listarClientesUseCase, ObtenerClientePorIdentificacionUseCase obtenerClientePorIdentificacionUseCase, CambiarEstadoClienteUseCase cambiarEstadoClienteUseCase) {
        this.crearClienteUseCase = crearClienteUseCase;
        this.actualizarClienteUseCase = actualizarClienteUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
        this.obtenerClientePorIdentificacionUseCase = obtenerClientePorIdentificacionUseCase;
        this.cambiarEstadoClienteUseCase = cambiarEstadoClienteUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> response = listarClientesUseCase.listar();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> obtenerPorIdentificacion(@RequestParam("identificacion") String identificacion){
        Cliente response = obtenerClientePorIdentificacionUseCase.obtener(identificacion);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> creacionCliente(@RequestBody ClienteCreacionDto creacionDto) throws BusinessException {
        Cliente response = crearClienteUseCase.crear(creacionDto);
        URI location = URI.create("cliente/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/cliente/{idCliente}/cambiar-estado")
    public ResponseEntity<Cliente> cambiarEstado(@PathVariable Integer idCliente){
        Cliente response = cambiarEstadoClienteUseCase.cambiarEstado(idCliente);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody ClienteCreacionDto creacionDto, @PathVariable Integer id) throws BusinessException {
        Cliente response = actualizarClienteUseCase.actualizar(creacionDto, id);

        return ResponseEntity.ok(response);
    }
}