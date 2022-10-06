package com.uco.cig.domain.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Cuota {

    private static final String VALOR_COBRO_MENOR_CERO = "El valor de un cobro no puede ser Menor o igual a CERO";
    private static final String VALOR_RESTA_MENOR_CERO = "El valor de la resta no puede ser Menor o igual a CERO";
    private static final String FECHA_FUTURO = "La fecha de realización no puede ser una fecha futura";
    private static final String CAMPO_REQUERIDO = "Campo requerido ";

    private Integer id;
    private BigDecimal valorCobro;
    private BigDecimal resta;
    private LocalDate fechaPropuesta;
    private OffsetDateTime fechaRealizacion;
    private Venta venta;
    private Trabajador trabajador;
    private TipoCobro tipoCobro;
    private EstadoCuota estadoCuota;

    private Cuota(Integer id, BigDecimal valorCobro, BigDecimal resta, LocalDate fechaPropuesta, OffsetDateTime fechaRealizacion, Venta venta, Trabajador trabajador, TipoCobro tipoCobro, EstadoCuota estadoCuota) throws BusinessException {
        this.id = id;
        ComunValidator.validarNumeroMayorIgualCero(valorCobro, VALOR_COBRO_MENOR_CERO);
        this.valorCobro = Objects.requireNonNull(valorCobro, CAMPO_REQUERIDO + "valor cobro");
        ComunValidator.validarNumeroMayorIgualCero(resta, VALOR_RESTA_MENOR_CERO);
        this.resta = Objects.requireNonNull(resta, CAMPO_REQUERIDO + "resta");
        this.fechaPropuesta = Objects.requireNonNull(fechaPropuesta, CAMPO_REQUERIDO + "fecha propuesta");
        ComunValidator.validarFechaNoFutura(fechaRealizacion, FECHA_FUTURO);
        this.fechaRealizacion = fechaRealizacion;
        this.venta = Objects.requireNonNull(venta, CAMPO_REQUERIDO + "venta");
        this.trabajador = trabajador;
        this.tipoCobro = Objects.requireNonNull(tipoCobro, CAMPO_REQUERIDO + "tipo cobro");
        this.estadoCuota = Objects.requireNonNull(estadoCuota, CAMPO_REQUERIDO + "estado cuota");
    }

    public static Cuota nueva(BigDecimal valorCobro, BigDecimal resta, LocalDate fechaPropuesta, OffsetDateTime fechaRealizacion, Venta venta, Trabajador trabajador, TipoCobro tipoCobro, EstadoCuota estadoCuota) throws BusinessException {
        return new Cuota(null, valorCobro, resta, fechaPropuesta, fechaRealizacion, venta, trabajador, tipoCobro, estadoCuota);
    }

    public static Cuota construir (Integer id, BigDecimal valorCobro, BigDecimal resta, LocalDate fechaPropuesta, OffsetDateTime fechaRealizacion, Venta venta, Trabajador trabajador, TipoCobro tipoCobro, EstadoCuota estadoCuota) throws BusinessException {
        return new Cuota(id, valorCobro, resta, fechaPropuesta, fechaRealizacion, venta, trabajador, tipoCobro, estadoCuota);
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValorCobro() {
        return valorCobro;
    }

    public BigDecimal getResta() {
        return resta;
    }

    public LocalDate getFechaPropuesta() {
        return fechaPropuesta;
    }

    public OffsetDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public Venta getVenta() {
        return venta;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public TipoCobro getTipoCobro() {
        return tipoCobro;
    }

    public EstadoCuota getEstadoCuota() {
        return estadoCuota;
    }

    public void setResta(BigDecimal resta) throws BusinessException {
        ComunValidator.validarNumeroMayorIgualCero(resta, VALOR_RESTA_MENOR_CERO);
        this.resta = Objects.requireNonNull(resta, CAMPO_REQUERIDO + "resta");
    }

    public void setFechaRealizacion(OffsetDateTime fechaRealizacion) throws BusinessException {
        ComunValidator.validarFechaNoFutura(fechaRealizacion, FECHA_FUTURO);
        this.fechaRealizacion = fechaRealizacion;
    }

    public void setTrabajador(Trabajador trabajador) throws BusinessException {
        this.trabajador = Trabajador.construir(trabajador.getId(), trabajador.getPersona(), trabajador.getEstado());
    }

    public void setEstadoCuota(EstadoCuota estadoCuota) {
        this.estadoCuota = estadoCuota;
    }
}
