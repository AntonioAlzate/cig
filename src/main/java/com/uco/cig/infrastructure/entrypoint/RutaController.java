package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.region.Region;
import com.uco.cig.usecase.barrio.ListarBarriosCiudadUseCase;
import com.uco.cig.usecase.ciudad.ListarCiudadesUseCase;
import com.uco.cig.usecase.departamento.ListarDepartamentosUseCase;
import com.uco.cig.usecase.pais.ListarPaisesUseCase;
import com.uco.cig.usecase.region.ListarRegionesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/rutas")
public class RutaController {

    private final ListarPaisesUseCase listarPaisesUseCase;
    private final ListarDepartamentosUseCase listarDepartamentosUseCase;
    private final ListarRegionesUseCase listarRegionesUseCase;
    private final ListarCiudadesUseCase listarCiudadesUseCase;
    private final ListarBarriosCiudadUseCase listarBarriosCiudadUseCase;

    public RutaController(ListarPaisesUseCase listarPaisesUseCase, ListarDepartamentosUseCase listarDepartamentosUseCase, ListarRegionesUseCase listarRegionesUseCase, ListarCiudadesUseCase listarCiudadesUseCase, ListarBarriosCiudadUseCase listarBarriosCiudadUseCase) {
        this.listarPaisesUseCase = listarPaisesUseCase;
        this.listarDepartamentosUseCase = listarDepartamentosUseCase;
        this.listarRegionesUseCase = listarRegionesUseCase;
        this.listarCiudadesUseCase = listarCiudadesUseCase;
        this.listarBarriosCiudadUseCase = listarBarriosCiudadUseCase;
    }

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises(){
        List<Pais> pais = listarPaisesUseCase.listar();

        return ResponseEntity.ok(pais);
    }

    @GetMapping("/departamentos/{idPais}")
    public ResponseEntity<List<Departamento>> listarDepartamentos(@PathVariable Integer idPais){
        List<Departamento> departamentos = listarDepartamentosUseCase.listar(idPais);

        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/regiones/{idDepartamento}")
    public ResponseEntity<List<Region>> listarRegiones(@PathVariable Integer idDepartamento){
        List<Region> regiones = listarRegionesUseCase.listar(idDepartamento);

        return ResponseEntity.ok(regiones);
    }

    @GetMapping("/ciudades/{idRegion}")
    public ResponseEntity<List<Ciudad>> listarCiudades(@PathVariable Integer idRegion){
        List<Ciudad> ciudades = listarCiudadesUseCase.listar(idRegion);

        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/barrios/{idCiudad}")
    public ResponseEntity<List<Barrio>> listarBarrios(@PathVariable Integer idCiudad){
        List<Barrio> barrios = listarBarriosCiudadUseCase.listar(idCiudad);

        return ResponseEntity.ok(barrios);
    }


}
