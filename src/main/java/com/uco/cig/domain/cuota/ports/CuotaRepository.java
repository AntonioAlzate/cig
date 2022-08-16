package com.uco.cig.domain.cuota.ports;

import com.uco.cig.domain.cuota.Cuota;

import java.util.List;
import java.util.Optional;

public interface CuotaRepository {

    List<Cuota> findAll();
    List<Cuota> saveAll(List<Cuota> cuotas);
    Cuota save(Cuota cuota);

    Optional<Cuota> findFirstByIdVentaAndIdEstadoCuotaOrderByFechaPropuesta(Integer idVenta, Integer idEstadoCuota);
    List<Cuota> findAllByIdVentaAndIdEstadoCuotaOrderByFechaPropuestaDsc(Integer idVenta, Integer idEstadoCuota);

}
