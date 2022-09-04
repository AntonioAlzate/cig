package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.shared.dtos.ProductoCreacionDto;
import com.uco.cig.usecase.producto.ConsultarProductosConPaginacionUseCase;
import com.uco.cig.usecase.producto.CrearProductoUseCase;
import com.uco.cig.usecase.producto.ListarProductosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ListarProductosUseCase listarProductosUseCase;
    private final CrearProductoUseCase crearProductoUseCase;
    private final ConsultarProductosConPaginacionUseCase consultarProductosConPaginacionUseCase;

    public ProductoController(ListarProductosUseCase listarProductosUseCase, CrearProductoUseCase crearProductoUseCase, ConsultarProductosConPaginacionUseCase consultarProductosConPaginacionUseCase) {
        this.listarProductosUseCase = listarProductosUseCase;
        this.crearProductoUseCase = crearProductoUseCase;
        this.consultarProductosConPaginacionUseCase = consultarProductosConPaginacionUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = listarProductosUseCase.listar();

        return ResponseEntity.ok(productos);
    }

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

    @PostMapping("/producto")
    public ResponseEntity<Producto> creacionProducto(@RequestBody ProductoCreacionDto productoCreacionDto) throws BusinessException {
        Producto response = crearProductoUseCase.crearProducto(productoCreacionDto);
        URI location = URI.create("producto/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }
}
