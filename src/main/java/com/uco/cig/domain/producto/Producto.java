package com.uco.cig.domain.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.estado.Estado;

import java.util.Objects;

public class Producto {

    private static final String NOMBRE_REQUERIDO = "El campo nombre es requerido";
    private static final String REFERENCIA_REQUERIDO = "El campo Referencia es requerido";
    private static final String REFERENCIA_MAS_DE_UNA_PALABRA = "La referencia de un producto no debe contener espacios";
    private static final String DESCRIPCION_REQUERIDO = "El campo Descripción es requerido";
    private static final String ESTADO_REQUERIDO = "El campo Estado es requerido";
    private static final String DIMENSION_REQUERIDO = "El campo Dimesión es requerido";
    private static final String CATEGORIA_REQUERIDO = "El campo Categoría es requerido";
    private static final String CANTIDAD_REUQERIDO = "La cantidad de productos en existencia es requerido";
    private static final String CANTIDAD_INVALIDA = "La cantidad de productos en existencia no puede ser menor a cero";

    private Integer id;
    private String nombre;
    private String referencia;
    private String descripcion;
    private Estado estado;
    private Dimension dimension;
    private Categoria categoria;
    private Color color;
    private Integer cantidadExistente;

    private Producto(Integer id, String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria, Integer cantidadExistente) throws BusinessException {
        this.id = id;
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REQUERIDO);
        ComunValidator.validarCadenaNoVacia(referencia, REFERENCIA_REQUERIDO);
        ComunValidator.validarUnicaPalabra(referencia, REFERENCIA_MAS_DE_UNA_PALABRA);
        this.referencia = Objects.requireNonNull(referencia, REFERENCIA_REQUERIDO);
        ComunValidator.validarCadenaNoVacia(descripcion, DESCRIPCION_REQUERIDO);
        this.descripcion = Objects.requireNonNull(descripcion, DESCRIPCION_REQUERIDO);
        this.estado = Objects.requireNonNull(estado, ESTADO_REQUERIDO);
        this.dimension = Objects.requireNonNull(dimension, DIMENSION_REQUERIDO);
        this.categoria = Objects.requireNonNull(categoria, CATEGORIA_REQUERIDO);
        ComunValidator.validarNumeroMayorIgualCero(cantidadExistente, CANTIDAD_INVALIDA);
        this.cantidadExistente = Objects.requireNonNull(cantidadExistente, CANTIDAD_REUQERIDO);
    }

    public static Producto nuevo(String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria, Integer cantidadExistente) throws BusinessException {
        return new Producto(null, nombre, referencia, descripcion, estado, dimension, categoria, cantidadExistente);
    }

    public static Producto construir(Integer id, String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria, Integer cantidadExistente) throws BusinessException {
        return new Producto(id, nombre, referencia, descripcion, estado, dimension, categoria, cantidadExistente);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Color getColor() {
        return color;
    }

    public Integer getCantidadExistente() {
        return cantidadExistente;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNombre(String nombre) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REQUERIDO);
    }

    public void setReferencia(String referencia) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(referencia, REFERENCIA_REQUERIDO);
        ComunValidator.validarUnicaPalabra(referencia, REFERENCIA_MAS_DE_UNA_PALABRA);
        this.referencia = Objects.requireNonNull(referencia, REFERENCIA_REQUERIDO);
    }

    public void setDescripcion(String descripcion) throws BusinessException {
        ComunValidator.validarCadenaNoVacia(descripcion, DESCRIPCION_REQUERIDO);
        this.descripcion = Objects.requireNonNull(descripcion, DESCRIPCION_REQUERIDO);
    }

    public void setEstado(Estado estado) {
        this.estado = Objects.requireNonNull(estado, ESTADO_REQUERIDO);
    }

    public void setDimension(Dimension dimension) {
        this.dimension = Objects.requireNonNull(dimension, DIMENSION_REQUERIDO);
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = Objects.requireNonNull(categoria, CATEGORIA_REQUERIDO);
    }

    public void setCantidadExistente(Integer cantidadExistente) throws BusinessException {
        ComunValidator.validarNumeroMayorIgualCero(cantidadExistente, CANTIDAD_INVALIDA);
        this.cantidadExistente = Objects.requireNonNull(cantidadExistente, CANTIDAD_REUQERIDO);
    }
}
