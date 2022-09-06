package com.uco.cig.domain.color.ports;

import com.uco.cig.domain.color.Color;

import java.util.List;
import java.util.Optional;

public interface ColorRepository {

    Color save(Color color);
    Optional<Color> findById(Integer id);
    List<Color> findAll();

    Color findByNombre(String nombreColor);
}
