package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.shared.dtos.ProductoCreacionDto;
import com.uco.cig.shared.dtos.TrabajadorCreacionDto;
import com.uco.cig.usecase.producto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/v1/productos")
@Slf4j
public class ProductoController {

    private final ListarProductosUseCase listarProductosUseCase;
    private final CrearProductoUseCase crearProductoUseCase;
    private final CambiarEstadoProductoUseCase cambiarEstado;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final ConsultarProductosConPaginacionUseCase consultarProductosConPaginacionUseCase;

    public ProductoController(ListarProductosUseCase listarProductosUseCase, CrearProductoUseCase crearProductoUseCase, CambiarEstadoProductoUseCase cambiarEstado, ActualizarProductoUseCase actualizarProductoUseCase, ConsultarProductosConPaginacionUseCase consultarProductosConPaginacionUseCase) {
        this.listarProductosUseCase = listarProductosUseCase;
        this.crearProductoUseCase = crearProductoUseCase;
        this.cambiarEstado = cambiarEstado;
        this.actualizarProductoUseCase = actualizarProductoUseCase;
        this.consultarProductosConPaginacionUseCase = consultarProductosConPaginacionUseCase;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping()
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = listarProductosUseCase.listar();

        return ResponseEntity.ok(productos);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-vendedor') OR hasAuthority('SCOPE_read:cig-cobrador')")
    @GetMapping("/page")
    public ResponseEntity<List<Producto>> listarPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ) {

        List<Producto> productos = consultarProductosConPaginacionUseCase.consultar(page, size, order, asc);

        return ResponseEntity.ok(productos);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PostMapping("/producto")
    public ResponseEntity<Producto> creacionProducto(@RequestBody ProductoCreacionDto productoCreacionDto) throws BusinessException {
        log.info("Creación de producto: {}", productoCreacionDto);
        Producto response = crearProductoUseCase.crearProducto(productoCreacionDto);
        URI location = URI.create("producto/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PutMapping("/producto/{idProducto}/cambiar-estado")
        public ResponseEntity<Producto> cambiarEstado(@PathVariable Integer idProducto){
        Producto response = cambiarEstado.cambiarEstado(idProducto);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_read:cig-admin')")
    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> actualizar(@RequestBody ProductoCreacionDto creacionDto, @PathVariable Integer id) throws BusinessException {
        log.info("Actualización de producto: {}", id);
        Producto response = actualizarProductoUseCase.actualizar(creacionDto, id);

        return ResponseEntity.ok(response);
    }
}
