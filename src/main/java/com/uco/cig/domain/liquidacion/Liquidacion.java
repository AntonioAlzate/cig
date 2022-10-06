package com.uco.cig.domain.liquidacion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.trabajador.Trabajador;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Liquidacion {

    private static final String FECHA_REQUERIDA = "El campo fecha en una liquidación es requerido";
    private static final String VALOR_REQUERIDO = "El campo valor en una liquidación es requerido";
    private static final String TRABAJADOR_REQUERIDO = "El campo trabajador es requerido en una liquidación";
    private static final String ESTADO_LIQUIDACION_REQUERIDO = "El campo estado de liquidación es requerido";
    private static final String VALOR_INVALIDO = "El campo valor no puede tener un número negativo";
    private Integer id;
    private OffsetDateTime fecha;
    private BigDecimal valor;
    private Trabajador trabajador;
    private EstadoLiquidacion estadoLiquidacion;

    private Liquidacion(Integer id, OffsetDateTime fecha, BigDecimal valor, Trabajador trabajador, EstadoLiquidacion estadoLiquidacion) throws BusinessException {
        this.id = id;
        this.fecha = Objects.requireNonNull(fecha, FECHA_REQUERIDA);
        ComunValidator.validarNumeroMayorIgualCero(valor, VALOR_INVALIDO);
        this.valor = Objects.requireNonNull(valor, VALOR_REQUERIDO);
        this.trabajador = Objects.requireNonNull(trabajador, TRABAJADOR_REQUERIDO);
        this.estadoLiquidacion = Objects.requireNonNull(estadoLiquidacion, ESTADO_LIQUIDACION_REQUERIDO);
    }

    public static Liquidacion nuevo(OffsetDateTime fecha, BigDecimal valor, Trabajador trabajador, EstadoLiquidacion estadoLiquidacion) throws BusinessException {
        return new Liquidacion(null, fecha, valor, trabajador, estadoLiquidacion);
    }

    public static Liquidacion construir(Integer id, OffsetDateTime fecha, BigDecimal valor, Trabajador trabajador, EstadoLiquidacion estadoLiquidacion) throws BusinessException {
        return new Liquidacion(id, fecha, valor, trabajador, estadoLiquidacion);

    }

    public Integer getId() {
        return id;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public EstadoLiquidacion getEstadoLiquidacion() {
        return estadoLiquidacion;
    }
}
