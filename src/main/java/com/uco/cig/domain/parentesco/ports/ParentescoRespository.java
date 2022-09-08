package com.uco.cig.domain.parentesco.ports;

import com.uco.cig.domain.parentesco.Parentesco;

import java.util.List;
import java.util.Optional;

public interface ParentescoRespository {
    Optional<Parentesco> findById(Integer id);

    List<Parentesco> findAll();
}
