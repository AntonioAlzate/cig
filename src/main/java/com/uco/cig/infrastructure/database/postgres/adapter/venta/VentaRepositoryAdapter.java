package com.uco.cig.infrastructure.database.postgres.adapter.venta;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ClienteEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.TrabajadorEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.VentaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaRepositoryAdapter implements VentaRepository {

    private static final String TRABAJADOR_NO_ENCONTRADO = "Trabajador no encontrado";
    private static final String CLIENTE_NO_ENCONTRADO = "Cliente no encontrado";

    private final VentaEntityRepository ventaEntityRepository;
    private final TrabajadorEntityRepository trabajadorEntityRepository;
    private final ClienteEntityRepository clienteEntityRepository;
    private final MapperUtils mapperUtils;

    public VentaRepositoryAdapter(VentaEntityRepository ventaEntityRepository, TrabajadorEntityRepository trabajadorEntityRepository, ClienteEntityRepository clienteEntityRepository, MapperUtils mapperUtils) {
        this.ventaEntityRepository = ventaEntityRepository;
        this.trabajadorEntityRepository = trabajadorEntityRepository;
        this.clienteEntityRepository = clienteEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Venta> findAll() {
        List<VentaEntity> ventaEntities = ventaEntityRepository.findAll();

        return ventaEntities.stream().map(v -> mapperUtils.mapperToVenta().apply(v)).collect(Collectors.toList());
    }

    @Override
    public Venta save(Venta venta) {
        VentaEntity ventaEntity = ventaEntityRepository.save(mapperUtils.mappertoVentaEntity().apply(venta));
        return mapperUtils.mapperToVenta().apply(ventaEntity);
    }

    @Override
    public List<Venta> findAllByIdTrabajadorAndFechaRealizacion(Integer idTrabajador, OffsetDateTime fechaRealizacion) {
        TrabajadorEntity trabajadorEntity = trabajadorEntityRepository.findById(idTrabajador).orElseThrow(() -> new NotFoundException(TRABAJADOR_NO_ENCONTRADO));

        List<VentaEntity> ventaEntities = ventaEntityRepository.findAllByIdTrabajador(trabajadorEntity);

        int diaCalcular = fechaRealizacion.getDayOfYear();
        int anioCalcular = fechaRealizacion.getYear();

        if (!ventaEntities.isEmpty()){
            ventaEntities = ventaEntities.stream().filter(venta -> {
                int diaCuota = venta.getFecha().getDayOfYear();
                int anioCuota = venta.getFecha().getYear();
                return (diaCuota == diaCalcular) && (anioCuota == anioCalcular);
            }).collect(Collectors.toList());

            return ventaEntities.stream().map(ventaEntity -> mapperUtils.mapperToVenta().apply(ventaEntity)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Venta> findAllByIdCliente(Integer idCliente) {
        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findById(idCliente);

        if(clienteEntity.isEmpty())
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO);

        List<VentaEntity> ventaEntities = ventaEntityRepository.findAllByIdCuentaCliente(clienteEntity.get().getIdCuentaCliente());

        if(ventaEntities.isEmpty())
            return new ArrayList<>();
        return ventaEntities.stream().map(ventaEntity -> mapperUtils.mapperToVenta().apply(ventaEntity)).collect(Collectors.toList());
    }

    @Override
    public Venta findById(Integer idVenta) {
        Optional<VentaEntity> ventaEntity = ventaEntityRepository.findById(idVenta);

        if(ventaEntity.isEmpty())
            throw new NotFoundException("No se encuentra una venta con el id especificado");

        return mapperUtils.mapperToVenta().apply(ventaEntity.get());
    }
}
