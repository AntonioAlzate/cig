package com.uco.cig.infrastructure.database.postgres.adapter.color;

import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.color.ports.ColorRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ColorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ColorEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorRepositoryAdapter implements ColorRepository {

    private final ColorEntityRepository colorEntityRepository;
    private final MapperUtils mapperUtils;

    public ColorRepositoryAdapter(ColorEntityRepository colorEntityRepository, MapperUtils mapperUtils) {
        this.colorEntityRepository = colorEntityRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Color save(Color color) {
        ColorEntity colorEntity = mapperUtils.mappertoColorEntity().apply(color);

        colorEntity = colorEntityRepository.save(colorEntity);

        return mapperUtils.mapperToColor().apply(colorEntity);
    }

    @Override
    public Optional<Color> findById(Integer id) {

        Optional<ColorEntity> colorEntity = colorEntityRepository.findById(id);

        if(colorEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToColor().apply(colorEntity.get()));
    }

    @Override
    public List<Color> findAll() {
        List<ColorEntity> colorEntities = colorEntityRepository.findAll();

        if(colorEntities.isEmpty())
            return new ArrayList<>();

        return colorEntities.stream().map(colorEntity -> mapperUtils.mapperToColor().apply(colorEntity)).collect(Collectors.toList());
    }
}
