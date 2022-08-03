package com.uco.cig.shared.mapper;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.despacho.detalle.DetalleDespacho;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.estado.despacho.EstadoDespacho;
import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.infrastructure.database.postgres.entities.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Function<EstadoVentaEntity, EstadoVenta> mapperToEstadoVenta() {
        return entity -> new EstadoVenta(
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

    public Function<PersonaEntity, Persona> mapperToPersona() {
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

    public Function<TrabajadorEntity, Trabajador> mapperToTrabajador() {
        return entity -> {
            try {
                return Trabajador.construir(
                        entity.getId(),
                        mapperToPersona().apply(entity.getIdPersona()),
                        mapperToEstado().apply(entity.getIdEstado())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<DimensionEntity, Dimension> mapperToDimension() {
        return entity -> {
            try {
                return Dimension.construir(
                        entity.getId(),
                        entity.getLargo(),
                        entity.getAncho()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<CategoriaEntity, Categoria> mapperToCategoria() {
        return entity -> {
            try {
                return Categoria.construir(
                        entity.getId(),
                        entity.getNombre()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<ProductoEntity, Producto> mapperToProducto() {
        return entity -> {
            try {
                return Producto.construir(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getReferencia(),
                        entity.getDescripcion(),
                        mapperToEstado().apply(entity.getIdEstado()),
                        mapperToDimension().apply(entity.getIdDimension()),
                        mapperToCategoria().apply(entity.getIdCategoria())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<ColorEntity, Color> mapperToColor() {
        return entity -> new Color(entity.getId(), entity.getNombre());
    }

    public Function<FormaPagoEntity, FormaPago> mapperToFormaPago() {
        return entity -> {
            try {
                return FormaPago.Construir(
                        entity.getId(),
                        entity.getNombre()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<ModalidadEntity, Modalidad> mapperToModalidad() {
        return entity -> {
            try {
                return Modalidad.Construir(
                        entity.getId(),
                        entity.getNombre()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<VentaEntity, Venta> mapperToVenta() {
        return entity -> {
            try {
                return Venta.construir(
                        entity.getId(),
                        entity.getFecha(),
                        entity.getValorTotal(),
                        mapperToTrabajador().apply(entity.getIdTrabajador()),
                        mapperToFormaPago().apply(entity.getIdFormaPago()),
                        mapperToModalidad().apply(entity.getIdModalidad()),
                        mapperToCuentaCliente().apply(entity.getIdCuentaCliente()),
                        mapperToEstadoVenta().apply(entity.getIdEstadoVenta())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<TipoCobroEntity, TipoCobro> mapperToTipoCobro() {
        return entity -> {
            try {
                return TipoCobro.Construir(
                        entity.getId(),
                        entity.getNombre()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<EstadoCuotaEntity, EstadoCuota> mapperToEstadoCuota() {
        return entity -> new EstadoCuota(
                entity.getId(),
                entity.getNombre()
        );
    }

    public Function<CuotaEntity, Cuota> mapperToCuota() {
        return entity -> {
            try {
                return Cuota.construir(
                        entity.getId(),
                        entity.getValorCobro(),
                        entity.getResta(),
                        entity.getFechaPropuesta(),
                        entity.getFechaRealizacion(),
                        mapperToVenta().apply(entity.getIdVenta()),
                        mapperToTrabajador().apply(entity.getIdTrabajador()),
                        mapperToTipoCobro().apply(entity.getIdTipoCobro()),
                        mapperToEstadoCuota().apply(entity.getIdEstadoCuota())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<RegistroDespachoEntity, RegistroDespacho> mapperToRegistroDespacho() {
        return entity -> {
            try {
                return RegistroDespacho.construir(
                        entity.getId(),
                        mapperToTrabajador().apply(entity.getIdTrabajadorRealiza()),
                        mapperToTrabajador().apply(entity.getIdTrabajadorRecibe()),
                        entity.getFecha(),
                        new ArrayList<>()
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<EstadoDespachoEntity, EstadoDespacho> mapperToEstadoDespacho() {
        return entity -> new EstadoDespacho(
                entity.getId(),
                entity.getNombre()
        );
    }

    public Function<DetalleDespachoEntity, DetalleDespacho> mapperToDetalleDespacho() {
        return entity -> {
            try {
                return DetalleDespacho.construir(
                        entity.getId(),
                        entity.getCantidadInicial(),
                        entity.getCantidadEntregar(),
                        mapperToRegistroDespacho().apply(entity.getIdRegistroDespacho()),
                        mapperToEstadoDespacho().apply(entity.getIdEstadoDespacho()),
                        mapperToProducto().apply(entity.getIdProducto())
                );
            } catch (BusinessException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }

    public Function<EstadoLiquidacionEntity, EstadoLiquidacion> mapperToEstadoLiquidacion() {
        return entity -> new EstadoLiquidacion(
                entity.getId(),
                entity.getNombre()
        );
    }

    public Function<LiquidacionEntity, Liquidacion> mapperToLiquidacion() {
        return entity -> {
            try {
                return Liquidacion.construir(
                        entity.getId(),
                        entity.getFecha(),
                        entity.getValor(),
                        mapperToTrabajador().apply(entity.getIdTrabajador()),
                        mapperToEstadoLiquidacion().apply(entity.getIdEstadoLiquidacion())
                );
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public Function<PrecioEntity, Precio> mapperToPrecio() {
        return entity -> {
            try {
                return Precio.construir(
                        entity.getId(),
                        entity.getFechaInicio(),
                        entity.getFechaFin(),
                        entity.getValor(),
                        mapperToModalidad().apply(entity.getIdModalidad()),
                        mapperToProducto().apply(entity.getIdProducto())
                );
            } catch (BusinessException e) {
                throw new RuntimeException(e);
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

    public Function<EstadoVenta, EstadoVentaEntity> mappertoEstadoVentaEntity() {
        return estado -> new EstadoVentaEntity(
                estado.getId(),
                estado.getNombre()
        );
    }

    public Function<Trabajador, TrabajadorEntity> mappertoTrabajadorEntity() {
        return trabajador -> new TrabajadorEntity(
                trabajador.getId(),
                mappertoPersonaEntity().apply(trabajador.getPersona()),
                mappertoEstadoEntity().apply(trabajador.getEstado())
        );
    }

    public Function<Dimension, DimensionEntity> mappertoDimensionEntity() {
        return dimension -> new DimensionEntity(
                dimension.getId(),
                dimension.getLargo(),
                dimension.getAncho()
        );
    }

    public Function<Categoria, CategoriaEntity> mappertoCategoriaEntity() {
        return categoria -> new CategoriaEntity(
                categoria.getId(),
                categoria.getNombre()
        );
    }

    public Function<Producto, ProductoEntity> mappertoProductoEntity() {
        return producto -> new ProductoEntity(
                producto.getId(),
                producto.getNombre(),
                producto.getReferencia(),
                producto.getDescripcion(),
                mappertoEstadoEntity().apply(producto.getEstado()),
                mappertoDimensionEntity().apply(producto.getDimension()),
                mappertoCategoriaEntity().apply(producto.getCategoria())
        );
    }

    public Function<Color, ColorEntity> mappertoColorEntity() {
        return color -> new ColorEntity(
                color.getId(),
                color.getNombre()
        );
    }

    public Function<FormaPago, FormaPagoEntity> mappertoFormaPagoEntity() {
        return formaPago -> new FormaPagoEntity(
                formaPago.getId(),
                formaPago.getNombre()
        );
    }

    public Function<Modalidad, ModalidadEntity> mappertoModalidadEntity() {
        return modalidad -> new ModalidadEntity(
                modalidad.getId(),
                modalidad.getNombre()
        );
    }

    public Function<Venta, VentaEntity> mappertoVentaEntity() {
        return venta -> new VentaEntity(
                venta.getId(),
                venta.getFecha(),
                venta.getValorTotal(),
                mappertoTrabajadorEntity().apply(venta.getTrabajador()),
                mappertoFormaPagoEntity().apply(venta.getFormaPago()),
                mappertoModalidadEntity().apply(venta.getModalidad()),
                mappertoCuentaClienteEntity().apply(venta.getCuentaCliente()),
                mappertoEstadoVentaEntity().apply(venta.getEstadoVenta())
        );
    }

    public Function<TipoCobro, TipoCobroEntity> mappertoTipoCobroEntity() {
        return tipoCobro -> new TipoCobroEntity(
                tipoCobro.getId(),
                tipoCobro.getNombre()
        );
    }

    public Function<EstadoCuota, EstadoCuotaEntity> mappertoEstadoCuotaEntity() {
        return estadoCuota -> new EstadoCuotaEntity(
                estadoCuota.getId(),
                estadoCuota.getNombre()
        );
    }

    public Function<Cuota, CuotaEntity> mappertoCuotaEntity() {
        return cuota -> new CuotaEntity(
                cuota.getId(),
                cuota.getValorCobro(),
                cuota.getResta(),
                cuota.getFechaPropuesta(),
                cuota.getFechaRealizacion(),
                mappertoVentaEntity().apply(cuota.getVenta()),
                mappertoTrabajadorEntity().apply(cuota.getTrabajador()),
                mappertoTipoCobroEntity().apply(cuota.getTipoCobro()),
                mappertoEstadoCuotaEntity().apply(cuota.getEstadoCuota())
        );
    }

    public Function<RegistroDespacho, RegistroDespachoEntity> mappertoRegistroDespachoEntity() {
        return registroDespacho -> new RegistroDespachoEntity(
                registroDespacho.getId(),
                registroDespacho.getFecha(),
                mappertoTrabajadorEntity().apply(registroDespacho.getTrabajadorRealiza()),
                mappertoTrabajadorEntity().apply(registroDespacho.getTrabajadorRecibe())
        );
    }

    public Function<EstadoDespacho, EstadoDespachoEntity> mappertoEstadoDespachoEntity() {
        return estadoDespacho -> new EstadoDespachoEntity(
                estadoDespacho.getId(),
                estadoDespacho.getEstado()
        );
    }

    public Function<DetalleDespacho, DetalleDespachoEntity> mappertoDetalleDespachoEntity() {
        return detalleDespacho -> new DetalleDespachoEntity(
                detalleDespacho.getId(),
                detalleDespacho.getCantidadInicial(),
                detalleDespacho.getCantidadEntregar(),
                mappertoRegistroDespachoEntity().apply(detalleDespacho.getRegistroDespacho()),
                mappertoEstadoDespachoEntity().apply(detalleDespacho.getEstadoDespacho()),
                mappertoProductoEntity().apply(detalleDespacho.getProducto())
        );
    }

    public Function<EstadoLiquidacion, EstadoLiquidacionEntity> mappertoEstadoLiquidacionEntity() {
        return liquidacion -> new EstadoLiquidacionEntity(
                liquidacion.getId(),
                liquidacion.getNombre()
        );
    }

    public Function<Liquidacion, LiquidacionEntity> mappertoLiquidacionEntity() {
        return liquidacion -> new LiquidacionEntity(
                liquidacion.getId(),
                liquidacion.getFecha(),
                liquidacion.getValor(),
                mappertoTrabajadorEntity().apply(liquidacion.getTrabajador()),
                mappertoEstadoLiquidacionEntity().apply(liquidacion.getEstadoLiquidacion())
        );
    }

    public Function<Precio, PrecioEntity> mappertoPrecioEntity() {
        return precio -> new PrecioEntity(
                precio.getId(),
                precio.getFechaInicio(),
                precio.getFechaFin(),
                precio.getValor(),
                mappertoModalidadEntity().apply(precio.getModalidad()),
                mappertoProductoEntity().apply(precio.getProducto())
        );
    }

    //endregion
}
