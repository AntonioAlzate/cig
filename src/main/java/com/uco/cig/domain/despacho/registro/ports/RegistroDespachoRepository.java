package com.uco.cig.domain.despacho.registro.ports;

import com.uco.cig.domain.despacho.registro.RegistroDespacho;

import java.util.List;

public interface RegistroDespachoRepository {

    List<RegistroDespacho> findAll();
}
