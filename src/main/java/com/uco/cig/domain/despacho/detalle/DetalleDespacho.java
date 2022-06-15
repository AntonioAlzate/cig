package com.uco.cig.domain.despacho.detalle;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.estado.despacho.EstadoDespacho;
import com.uco.cig.domain.producto.Producto;

import java.util.Objects;

public class DetalleDespacho {

    public static final String CANTIDAD_INICIAL_REQUERIDO = "La cantidad inicial en un detalle de despacho es requerida";
    public static final String CANTIDAD_ENTREGAR_REQUERIDO = "La cantidad a entregar en un detalle de despacho es requerida";
    public static final String REGISTRO_DESPACHO_REQUERIDO = "El registro de despacho es requerido y debe existir para crear un detalle";
    public static final String ESTADO_DESPACHO_REQUERIDO = "El estado de despacho en un detalle de despacho es requerida";
    public static final String PRODUCTO_REQUERIDO = "El producto en un detalle de despacho es requerida";
    private static final String CANTIDAD_INICIAL_INVALIDA = "La cantidad inicial de un producto debe ser mayor a cero de lo contrario no se deberia agregar";
    private static final String CANTIDAD_ENTREGAR_INVALIDA = "La cantidad a entregar no puede ser un número negativo";

    private Integer id;
    private Integer cantidadInicial;
    private Integer cantidadEntregar;
    private RegistroDespacho registroDespacho;
    private EstadoDespacho estadoDespacho;
    private Producto producto;

    private DetalleDespacho(Integer id, Integer cantidadInicial, Integer cantidadEntregar, RegistroDespacho registroDespacho, EstadoDespacho estadoDespacho, Producto producto) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorCero(cantidadInicial, CANTIDAD_INICIAL_INVALIDA);
        this.cantidadInicial = Objects.requireNonNull(cantidadInicial, CANTIDAD_INICIAL_REQUERIDO);
        ComunValidator.validarNumeroMayorIgualCero(cantidadEntregar, CANTIDAD_ENTREGAR_INVALIDA);
        this.cantidadEntregar = Objects.requireNonNull(cantidadEntregar, CANTIDAD_ENTREGAR_REQUERIDO);
        this.registroDespacho = Objects.requireNonNull(registroDespacho, REGISTRO_DESPACHO_REQUERIDO);
        this.estadoDespacho = Objects.requireNonNull(estadoDespacho, ESTADO_DESPACHO_REQUERIDO);
        this.producto = Objects.requireNonNull(producto, PRODUCTO_REQUERIDO);
    }

    public static DetalleDespacho nuevo(Integer cantidadInicial, Integer cantidadEntregar, RegistroDespacho registroDespacho, EstadoDespacho estadoDespacho, Producto producto) throws BusinessException {
        return new DetalleDespacho(null, cantidadInicial, cantidadEntregar, registroDespacho, estadoDespacho, producto);
    }

    public static DetalleDespacho construir(Integer id, Integer cantidadInicial, Integer cantidadEntregar, RegistroDespacho registroDespacho, EstadoDespacho estadoDespacho, Producto producto) throws BusinessException {
        return new DetalleDespacho(id, cantidadInicial, cantidadEntregar, registroDespacho, estadoDespacho, producto);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Integer getCantidadEntregar() {
        return cantidadEntregar;
    }

    public void setCantidadEntregar(Integer cantidadEntregar) {
        this.cantidadEntregar = cantidadEntregar;
    }

    public RegistroDespacho getRegistroDespacho() {
        return registroDespacho;
    }

    public void setRegistroDespacho(RegistroDespacho registroDespacho) {
        this.registroDespacho = registroDespacho;
    }

    public EstadoDespacho getEstadoDespacho() {
        return estadoDespacho;
    }

    public void setEstadoDespacho(EstadoDespacho estadoDespacho) {
        this.estadoDespacho = estadoDespacho;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
