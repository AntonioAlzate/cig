package com.uco.cig.domain.formapago.ports;

import com.uco.cig.domain.formapago.FormaPago;

import java.util.Optional;

public interface FormaPagoRepository {
    Optional<FormaPago> findById(Integer id);
}
