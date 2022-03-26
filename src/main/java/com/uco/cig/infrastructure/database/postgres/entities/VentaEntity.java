package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Venta")
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "valorTotal", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private TrabajadorEntity idTrabajador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idFormaPago", nullable = false)
    private FormaPagoEntity idFormaPago;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idModalidad", nullable = false)
    private ModalidadEntity idModalidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCuentaCliente", nullable = false)
    private CuentaClienteEntity idCuentaCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstadoVenta", nullable = false)
    private EstadoVentaEntity idEstadoVenta;

    public EstadoVentaEntity getIdEstadoVenta() {
        return idEstadoVenta;
    }

    public void setIdEstadoVenta(EstadoVentaEntity idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    public CuentaClienteEntity getIdCuentaCliente() {
        return idCuentaCliente;
    }

    public void setIdCuentaCliente(CuentaClienteEntity idCuentaCliente) {
        this.idCuentaCliente = idCuentaCliente;
    }

    public ModalidadEntity getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(ModalidadEntity idModalidad) {
        this.idModalidad = idModalidad;
    }

    public FormaPagoEntity getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(FormaPagoEntity idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public TrabajadorEntity getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(TrabajadorEntity idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}