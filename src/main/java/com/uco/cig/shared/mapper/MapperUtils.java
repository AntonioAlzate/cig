package com.uco.cig.shared.mapper;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.infrastructure.database.postgres.entities.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {


    //region Entity to Domain
    public Function<BarrioEntity, Barrio> mapperToBarrio() {
        return entity -> {
            try {
                return Barrio.construir(
                        entity.getId(),
                        entity.getNombre(),
                        mapperToZona().apply(entity.getIdZona())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<ZonaEntity, Zona> mapperToZona() {
        return entity -> {
            try {
                return Zona.construir(
                        entity.getId(),
                        entity.getNombre(),
                        mapperToCiudad().apply(entity.getIdCiudad())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<CiudadEntity, Ciudad> mapperToCiudad() {
        return entity -> {
            try {
                return Ciudad.construir(
                        entity.getId(),
                        entity.getNombre(),
                        mapperToRegion().apply(entity.getIdRegion())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<RegionEntity, Region> mapperToRegion() {
        return entity -> {
            try {
                return Region.construir(
                        entity.getId(),
                        entity.getNombre(),
                        mapperToDepartamento().apply(entity.getIdDepartamento())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<DepartamentoEntity, Departamento> mapperToDepartamento() {
        return entity -> {
            try {
                return Departamento.construir(
                        entity.getId(),
                        entity.getNombre(),
                        mapperToPais().apply(entity.getIdPais())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<PaisEntity, Pais> mapperToPais() {
        return entity -> {
            try {
                return Pais.construir(
                        entity.getId(),
                        entity.getNombre()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<EstadoEntity, Estado> mapperToEstado() {
        return entity -> new Estado(
                entity.getId(),
                entity.getNombre()
        );
    }

    public Function<EstadoCuentaClienteEntity, EstadoCuentaCliente> mapperToEstadoCuentaCliente() {
        return entity -> new EstadoCuentaCliente(
                entity.getId(),
                entity.getEstado()
        );
    }

    public Function<ClienteEntity, Cliente> mapperToCliente() {
        return entity -> Cliente.construir(
                entity.getId(),
                mapperToPersona().apply(entity.getIdPersona()),
                mapperToCuentaCliente().apply(entity.getIdCuentaCliente()),
                mapperToEstado().apply(entity.getIdEstado())
        );
    }

    public Function<PersonaEntity, Persona> mapperToPersona(){
        return entity -> {
            try {
                return Persona.construir(
                        entity.getId(),
                        entity.getIdentificacion(),
                        entity.getPrimerNombre(),
                        entity.getSegundoNombre(),
                        entity.getPrimerApellido(),
                        entity.getSegundoApellido(),
                        entity.getDireccion(),
                        entity.getTelefono(),
                        mapperToBarrio().apply(entity.getIdBarrio())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<CuentaClienteEntity, CuentaCliente> mapperToCuentaCliente() {
        return entity -> {
            try {
                return CuentaCliente.construir(
                        entity.getId(),
                        entity.getCupo(),
                        entity.getSaldoDeuda(),
                        mapperToEstadoCuentaCliente().apply(entity.getIdEstadoCuentaCliente()),
                        mapperToDetalleCuentaFavor().apply(entity.getIdDetalleCuentaFavor())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<DetalleCuentaFavorEntity, DetalleCuentaFavor> mapperToDetalleCuentaFavor() {
        return entity -> {
            try {
                return DetalleCuentaFavor.construir(
                        entity.getId(),
                        entity.getValor()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }


    //endregion

    //region Domain To Entity

    public Function<Barrio, BarrioEntity> mapperToBarrioEntity() {
        return barrio -> new BarrioEntity(
                barrio.getId(),
                barrio.getNombre(),
                mapperToZonaEntity().apply(barrio.getZona())
        );
    }

    public Function<Zona, ZonaEntity> mapperToZonaEntity() {
        return zona -> new ZonaEntity(
                zona.getId(),
                zona.getNombre(),
                mapperToCiudadEntity().apply(zona.getCiudad())
        );
    }

    public Function<Ciudad, CiudadEntity> mapperToCiudadEntity() {
        return ciudad -> new CiudadEntity(
                ciudad.getId(),
                ciudad.getNombre(),
                mapperToRegionEntity().apply(ciudad.getRegion())
        );
    }

    public Function<Region, RegionEntity> mapperToRegionEntity() {
        return region -> new RegionEntity(
                region.getId(),
                region.getNombre(),
                mapperToDepartamentoEntity().apply(region.getDepartamento())
        );
    }

    public Function<Departamento, DepartamentoEntity> mapperToDepartamentoEntity() {
        return departamento -> new DepartamentoEntity(
                departamento.getId(),
                departamento.getNombre(),
                mapperToPaisEntity().apply(departamento.getPais())
        );
    }

    public Function<Pais, PaisEntity> mapperToPaisEntity() {
        return pais -> new PaisEntity(
                pais.getId(),
                pais.getNombre()
        );
    }

    public Function<Persona, PersonaEntity> mappertoPersonaEntity() {
        return persona -> new PersonaEntity(
                persona.getId(),
                persona.getIdentificacion(),
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getDireccion(),
                persona.getTelefono(),
                mapperToBarrioEntity().apply(persona.getBarrio())
        );
    }

    public Function<CuentaCliente, CuentaClienteEntity> mappertoCuentaClienteEntity() {
        return cuentaCliente -> new CuentaClienteEntity(
                cuentaCliente.getId(),
                cuentaCliente.getCupo(),
                cuentaCliente.getSaldoDeuda(),
                mappertoEstadoCuentaClienteEntity().apply(cuentaCliente.getEstadoCuentaCliente()),
                mappertoDetalleCuentaFavorEntity().apply(cuentaCliente.getDetalleCuentaFavor())
        );
    }

    public Function<EstadoCuentaCliente, EstadoCuentaClienteEntity> mappertoEstadoCuentaClienteEntity() {
        return estadoCuentaCliente -> new EstadoCuentaClienteEntity(
                estadoCuentaCliente.getId(),
                estadoCuentaCliente.getEstado()
        );
    }

    public Function<DetalleCuentaFavor, DetalleCuentaFavorEntity> mappertoDetalleCuentaFavorEntity() {
        return detalleCuentaFavor -> new DetalleCuentaFavorEntity(
                detalleCuentaFavor.getId(),
                detalleCuentaFavor.getValor()
        );
    }

    public Function<Cliente, ClienteEntity> mappertoClienteEntity() {
        return cliente -> new ClienteEntity(
                cliente.getId(),
                mappertoPersonaEntity().apply(cliente.getPersona()),
                mappertoCuentaClienteEntity().apply(cliente.getCuentaCliente()),
                mappertoEstadoEntity().apply(cliente.getEstado())
        );
    }

    public Function<Estado, EstadoEntity> mappertoEstadoEntity() {
        return estado -> new EstadoEntity(
                estado.getId(),
                estado.getNombre()
        );
    }

    //endregion
}
