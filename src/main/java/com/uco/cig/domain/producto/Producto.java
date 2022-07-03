package com.uco.cig.domain.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.producto.validator.ProductoValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {

    private static final String NOMBRE_REQUERIDO = "El campo nombre es requerido";
    private static final String REFERENCIA_REQUERIDO = "El campo Referencia es requerido";
    private static final String REFERENCIA_MAS_DE_UNA_PALABRA = "La referencia de un producto no debe contener espacios";
    private static final String DESCRIPCION_REQUERIDO = "El campo Descripción es requerido";
    private static final String ESTADO_REQUERIDO = "El campo Estado es requerido";
    private static final String DIMENSION_REQUERIDO = "El campo Dimesión es requerido";
    private static final String CATEGORIA_REQUERIDO = "El campo Categoría es requerido";

    private Integer id;
    private String nombre;
    private String referencia;
    private String descripcion;
    private Estado estado;
    private Dimension dimension;
    private Categoria categoria;
    private Color color;

    private Producto(Integer id, String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria) throws BusinessException {
        this.id = id;
        ProductoValidator.validarCadenaNoVacia(nombre, NOMBRE_REQUERIDO);
        this.nombre = Objects.requireNonNull(nombre, NOMBRE_REQUERIDO);
        ProductoValidator.validarCadenaNoVacia(referencia, REFERENCIA_REQUERIDO);
        ProductoValidator.validarUnicaPalabra(referencia, REFERENCIA_MAS_DE_UNA_PALABRA);
        this.referencia = Objects.requireNonNull(referencia, REFERENCIA_REQUERIDO);
        ProductoValidator.validarCadenaNoVacia(descripcion, DESCRIPCION_REQUERIDO);
        this.descripcion = Objects.requireNonNull(descripcion, DESCRIPCION_REQUERIDO);
        this.estado = Objects.requireNonNull(estado, ESTADO_REQUERIDO);
        this.dimension = Objects.requireNonNull(dimension, DIMENSION_REQUERIDO);
        this.categoria = Objects.requireNonNull(categoria, CATEGORIA_REQUERIDO);
    }

    public static Producto nuevo(String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria) throws BusinessException {
        return new Producto(null, nombre, referencia, descripcion, estado, dimension, categoria);
    }

    public static Producto construir(Integer id, String nombre, String referencia, String descripcion, Estado estado, Dimension dimension, Categoria categoria) throws BusinessException {
        return new Producto(id, nombre, referencia, descripcion, estado, dimension, categoria);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
