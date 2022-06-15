package com.uco.cig.infrastructure.database.postgres.adapter.despacho;

import com.uco.cig.domain.despacho.detalle.DetalleDespacho;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.despacho.registro.ports.RegistroDespachoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.DetalleDespachoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.RegistroDespachoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DetalleDespachoEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.RegistroDespachoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroDespachoRepositoryAdapter implements RegistroDespachoRepository {

    private final RegistroDespachoEntityRepository registroDespachoEntityRepository;
    private final DetalleDespachoEntityRepository detalleDespachoEntityRepository;
    private final MapperUtils mapperUtils;

    public RegistroDespachoRepositoryAdapter(RegistroDespachoEntityRepository registroDespachoEntityRepository, DetalleDespachoEntityRepository detalleDespachoEntityRepository, MapperUtils mapperUtils) {
        this.registroDespachoEntityRepository = registroDespachoEntityRepository;
        this.detalleDespachoEntityRepository = detalleDespachoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<RegistroDespacho> findAll() {
        List<RegistroDespachoEntity> registroDespachoEntities = registroDespachoEntityRepository.findAll();
        List<DetalleDespachoEntity> detalleDespachoEntities = detalleDespachoEntityRepository.findAll();

        List<RegistroDespacho> registroDespachos = registroDespachoEntities.stream().map(r -> mapperUtils.mapperToRegistroDespacho().apply(r)).collect(Collectors.toList());
        List<DetalleDespacho> detalleDespachos = detalleDespachoEntities.stream().map(d -> mapperUtils.mapperToDetalleDespacho().apply(d)).collect(Collectors.toList());

        registroDespachos.stream().forEach(registroDespacho -> registroDespacho.setDetalles(detallesDeRegistro(registroDespacho.getId(), detalleDespachos)));

        return registroDespachos;
    }

    private List<DetalleDespacho> detallesDeRegistro(Integer idRegistro, List<DetalleDespacho> detalles) {
        List<DetalleDespacho> detallesDeRegistroEspecificado = new ArrayList<>();

        detalles.stream().forEach(detalleDespacho -> {
            if (detalleDespacho.getRegistroDespacho().getId().equals(idRegistro)) {
                detallesDeRegistroEspecificado.add(detalleDespacho);
            }
        });

        return detallesDeRegistroEspecificado;
    }
}
