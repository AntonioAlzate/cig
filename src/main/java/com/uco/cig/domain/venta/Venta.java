package com.uco.cig.domain.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.trabajador.Trabajador;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Venta {

    private static final String FECHA_PASADA = "La fecha de una venta no puede ser del pasado";
    private static final String VALOR_INVALIDO = "El valor total de una venta no puede ser menor o igual a cero";

    private Integer id;
    private OffsetDateTime fecha;
    private BigDecimal valorTotal;
    private Trabajador trabajador;
    private FormaPago formaPago;
    private Modalidad modalidad;
    private CuentaCliente cuentaCliente;
    private EstadoVenta estadoVenta;

    private Venta(Integer id, OffsetDateTime fecha, BigDecimal valorTotal, Trabajador trabajador, FormaPago formaPago, Modalidad modalidad, CuentaCliente cuentaCliente, EstadoVenta estadoVenta) throws BusinessException {
        this.id = id;
        this.fecha = Objects.requireNonNull(fecha);
        ComunValidator.validarNumeroMayorCero(valorTotal, VALOR_INVALIDO);
        this.valorTotal = Objects.requireNonNull(valorTotal);
        this.trabajador = Objects.requireNonNull(trabajador);
        this.formaPago = Objects.requireNonNull(formaPago);
        this.modalidad = Objects.requireNonNull(modalidad);
        this.cuentaCliente = Objects.requireNonNull(cuentaCliente);
        this.estadoVenta = Objects.requireNonNull(estadoVenta);
    }

    public static Venta nuevo(OffsetDateTime fecha, BigDecimal valorTotal, Trabajador trabajador, FormaPago formaPago, Modalidad modalidad, CuentaCliente cuentaCliente, EstadoVenta estadoVenta) throws BusinessException {
        ComunValidator.validarFechaNoMenorAActual(fecha, FECHA_PASADA);
        return new Venta(null, fecha, valorTotal, trabajador, formaPago, modalidad, cuentaCliente, estadoVenta);
    }

    public static Venta construir(Integer id, OffsetDateTime fecha, BigDecimal valorTotal, Trabajador trabajador, FormaPago formaPago, Modalidad modalidad, CuentaCliente cuentaCliente, EstadoVenta estadoVenta) throws BusinessException {
        return new Venta(id, fecha, valorTotal, trabajador, formaPago, modalidad, cuentaCliente, estadoVenta);
    }

    public Integer getId() {
        return id;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public CuentaCliente getCuentaCliente() {
        return cuentaCliente;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
}
