package com.uco.cig.infrastructure.entrypoint;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.shared.dtos.DatosLiquidacionDTO;
import com.uco.cig.shared.dtos.DatosLiquidacionObtenerDTO;
import com.uco.cig.usecase.liquidacion.CalcularLiquidacionTrabajadorUseCase;
import com.uco.cig.usecase.liquidacion.ListarLiquidacionesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/liquidaciones")
public class LiquidacionController {

    private final ListarLiquidacionesUseCase listarLiquidacionesUseCase;
    private final CalcularLiquidacionTrabajadorUseCase calcularLiquidacionTrabajadorUseCase;

    public LiquidacionController(ListarLiquidacionesUseCase listarLiquidacionesUseCase, CalcularLiquidacionTrabajadorUseCase calcularLiquidacionTrabajadorUseCase) {
        this.listarLiquidacionesUseCase = listarLiquidacionesUseCase;
        this.calcularLiquidacionTrabajadorUseCase = calcularLiquidacionTrabajadorUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<Liquidacion>> listar(){
        List<Liquidacion> response = listarLiquidacionesUseCase.listar();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('read:cig-admin')")
    @GetMapping("/calcular-valores")
    public ResponseEntity<DatosLiquidacionDTO> obtenerDatos(@RequestBody DatosLiquidacionObtenerDTO datosLiquidacionObtenerDTO) throws BusinessException {
        OffsetDateTime fechaRealizacion = OffsetDateTime.of(datosLiquidacionObtenerDTO.getFechaRealizacion(), LocalTime.MIDNIGHT, ZoneOffset.UTC);

        DatosLiquidacionDTO datosLiquidacion = calcularLiquidacionTrabajadorUseCase.calcular(datosLiquidacionObtenerDTO.getIdTrabajador(), fechaRealizacion, datosLiquidacionObtenerDTO.getCrearLiquidacion());

        return ResponseEntity.ok(datosLiquidacion);
    }
}
