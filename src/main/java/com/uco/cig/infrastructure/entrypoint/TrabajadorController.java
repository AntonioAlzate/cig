package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.shared.dtos.TrabajadorCreacionDto;
import com.uco.cig.usecase.trabajador.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/trabajadores")
public class TrabajadorController {

    private final CrearTrabajadorUseCase crearTrabajadorUseCase;
    private final ActualizarTrabajadorUseCase actualizarTrabajadorUseCase;
    private final CambiarEstadoTrabajadorUseCase cambiarEstadoTrabajadorUseCase;
    private final ObtenerTrabajadorPorIdentificacionUseCase obtenerTrabajadorPorIdentificacionUseCase;
    private final ListarTrabajadoresUseCase listarTrabajadoresUseCase;
    private final ObtenerTrabajadorPorCorreoUseCase obtenerTrabajadorPorCorreoUseCase;

    public TrabajadorController(CrearTrabajadorUseCase crearTrabajadorUseCase, ActualizarTrabajadorUseCase actualizarTrabajadorUseCase, CambiarEstadoTrabajadorUseCase cambiarEstadoTrabajadorUseCase, ObtenerTrabajadorPorIdentificacionUseCase obtenerTrabajadorPorIdentificacionUseCase, ListarTrabajadoresUseCase listarTrabajadoresUseCase, ObtenerTrabajadorPorCorreoUseCase obtenerTrabajadorPorCorreoUseCase) {
        this.crearTrabajadorUseCase = crearTrabajadorUseCase;
        this.actualizarTrabajadorUseCase = actualizarTrabajadorUseCase;
        this.cambiarEstadoTrabajadorUseCase = cambiarEstadoTrabajadorUseCase;
        this.obtenerTrabajadorPorIdentificacionUseCase = obtenerTrabajadorPorIdentificacionUseCase;
        this.listarTrabajadoresUseCase = listarTrabajadoresUseCase;
        this.obtenerTrabajadorPorCorreoUseCase = obtenerTrabajadorPorCorreoUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping()
    public ResponseEntity<List<Trabajador>> listar(){
        List<Trabajador> response = listarTrabajadoresUseCase.listar();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/trabajador")
    public ResponseEntity<Trabajador> obtenerPorIdentificacion(@RequestParam("identificacion") String identificacion){
        Trabajador response = obtenerTrabajadorPorIdentificacionUseCase.obtener(identificacion);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/trabajador/{correo}")
    public ResponseEntity<Trabajador> obtenerPorCorreo(@PathVariable String correo) {
        Trabajador response = obtenerTrabajadorPorCorreoUseCase.obtener(correo);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PostMapping("/trabajador")
    public ResponseEntity<Trabajador> creacionTrabajador(@RequestBody TrabajadorCreacionDto creacionDto) throws BusinessException {
        Trabajador response = crearTrabajadorUseCase.crear(creacionDto);
        URI location = URI.create("trabajador/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PutMapping("/trabajador/{idTrabajador}/cambiar-estado")
    public ResponseEntity<Trabajador> cambiarEstado(@PathVariable Integer idTrabajador){
        Trabajador response = cambiarEstadoTrabajadorUseCase.cambiarEstado(idTrabajador);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PutMapping("/trabajador/{id}")
    public ResponseEntity<Trabajador> actualizarTrabajador(@RequestBody TrabajadorCreacionDto creacionDto, @PathVariable Integer id) throws BusinessException {
        Trabajador response = actualizarTrabajadorUseCase.actualizar(creacionDto, id);

        return ResponseEntity.ok(response);
    }
}
