package com.uco.cig.usecase.producto.color;

import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.color.ports.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarColoresUseCase {

    private final ColorRepository colorRepository;

    public ListarColoresUseCase(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> listar(){
        return colorRepository.findAll();
    }
}
