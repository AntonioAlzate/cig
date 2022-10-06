package com.uco.cig.domain.detalle.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.venta.Venta;

import java.math.BigDecimal;
import java.util.Objects;

public class DetalleVenta {

    private static final String VALOR_NEGATIVO = "El valor total no puede ser menor a cero";
    private static final String CANTIDAD_REQUERIDA = "La cantidad de un producto es requerida";
    private static final String VALOR_TOTAL_REQUERIDO = "Valor total de un detalle requerido";
    private static final String PRODUCTO_REQUERIDO = "El producto en un detalle de venta es requerido";

    private Integer id;
    private Integer cantidad;
    private BigDecimal valorTotal;
    private BigDecimal descuentoAdicional;
    private String justificacion;
    private Venta venta;
    private Producto producto;

    private DetalleVenta(Integer id, Integer cantidad, BigDecimal valorTotal, BigDecimal descuentoAdicional, String justificacion, Venta venta, Producto producto) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorCero(cantidad, VALOR_NEGATIVO);
        this.cantidad = Objects.requireNonNull(cantidad, CANTIDAD_REQUERIDA);
        ComunValidator.validarNumeroMayorIgualCero(valorTotal, VALOR_NEGATIVO);
        this.valorTotal = Objects.requireNonNull(valorTotal, VALOR_TOTAL_REQUERIDO);
        ComunValidator.validarNumeroMayorIgualCero(descuentoAdicional, VALOR_NEGATIVO);
        this.descuentoAdicional = descuentoAdicional;
        this.justificacion = justificacion;
        this.venta = venta;
        this.producto = Objects.requireNonNull(producto, PRODUCTO_REQUERIDO);
    }

    public static DetalleVenta nuevo(Integer cantidad, BigDecimal valorTotal, BigDecimal descuentoAdicional, String justificacion, Venta venta, Producto producto) throws BusinessException {
        return new DetalleVenta(null, cantidad, valorTotal, descuentoAdicional, justificacion, venta, producto);
    }

    public static DetalleVenta construir(Integer id, Integer cantidad, BigDecimal valorTotal, BigDecimal descuentoAdicional, String justificacion, Venta venta, Producto producto) throws BusinessException {
        return new DetalleVenta(id, cantidad, valorTotal, descuentoAdicional, justificacion, venta, producto);
    }

    public Integer getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDescuentoAdicional() {
        return descuentoAdicional;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public Venta getVenta() {
        return venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setVenta(Venta venta) throws BusinessException {
        this.venta = Venta.construir(venta.getId(), venta.getFecha(), venta.getValorTotal(), venta.getTrabajador(), venta.getFormaPago(), venta.getModalidad(),
                venta.getCuentaCliente(), venta.getEstadoVenta());
    }
}
