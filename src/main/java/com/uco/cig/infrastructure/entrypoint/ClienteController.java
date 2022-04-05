package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.usecase.cliente.CrearClienteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private final CrearClienteUseCase crearClienteUseCase;

    public ClienteController(CrearClienteUseCase crearClienteUseCase) {
        this.crearClienteUseCase = crearClienteUseCase;
    }

    @PostMapping("/cliente")
   public ResponseEntity<Cliente> creacionCliente(@RequestBody ClienteCreacionDto creacionDto){
        Cliente response = crearClienteUseCase.crear(creacionDto);
        URI location = URI.create("cliente/" + response.getId());
        return ResponseEntity.created(location).body(response);
   }
}
