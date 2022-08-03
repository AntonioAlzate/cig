package com.uco.cig.domain.precio;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.producto.Producto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Precio {

    private static final String PRECIO_NO_VALIDO = "El precio de un producto no puede ser igual o menor a cero";
    private static final String FECHA_NO_VALIDA = "La fecha de inicio de un precio no puede ser menor o igual a la del día de hoy";

    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal valor;
    private Modalidad modalidad;
    private Producto producto;

    private Precio(Integer id, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal valor, Modalidad modalidad, Producto producto) throws BusinessException {
        this.id = id;
        this.fechaInicio = Objects.requireNonNull(fechaInicio);
        this.fechaFin = fechaFin;
        ComunValidator.validarNumeroMayorCero(valor, PRECIO_NO_VALIDO);
        this.valor = Objects.requireNonNull(valor);
        this.modalidad = Objects.requireNonNull(modalidad);
        this.producto = Objects.requireNonNull(producto);
    }

    public static Precio nuevo(LocalDate fechaInicio, LocalDate fechaFin, BigDecimal valor, Modalidad modalidad, Producto producto) throws BusinessException {
        ComunValidator.validarFechaNoMenorAActual(fechaInicio, FECHA_NO_VALIDA);
        return new Precio(null, fechaInicio, fechaFin, valor, modalidad, producto);
    }

    public static Precio construir(Integer id, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal valor, Modalidad modalidad, Producto producto) throws BusinessException {
        return new Precio(id, fechaInicio, fechaFin, valor, modalidad, producto);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
