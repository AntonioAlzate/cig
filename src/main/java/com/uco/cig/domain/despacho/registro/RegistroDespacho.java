package com.uco.cig.domain.despacho.registro;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.common.validator.ComunValidator;
import com.uco.cig.domain.despacho.detalle.DetalleDespacho;
import com.uco.cig.domain.trabajador.Trabajador;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistroDespacho {

    private static final String TRABAJADOR_REALIZA_REQUERIDO = "El trabajador que realiza el despacho es requerido";
    private static final String TRABAJADOR_RECIBE_REQUERIDO = "El trabajador que recibe el despacho es requerido";
    private static final String FECHA_REQUERIDO = "La fecha de realización de un despacho es requerida";
    private static final String FECHA_FUTURA = "La fecha de realización de un despacho no puede ser una fecha del futuro";


    private Integer id;
    private Trabajador trabajadorRealiza;
    private Trabajador trabajadorRecibe;
    private OffsetDateTime fecha;
    private List<DetalleDespacho> detalles;

    private RegistroDespacho(Integer id, Trabajador trabajadorRealiza, Trabajador trabajadorRecibe, OffsetDateTime fecha, List<DetalleDespacho> detalles) {
        this.id = id;
        this.trabajadorRealiza = Objects.requireNonNull(trabajadorRealiza, TRABAJADOR_REALIZA_REQUERIDO);
        this.trabajadorRecibe = Objects.requireNonNull(trabajadorRecibe, TRABAJADOR_RECIBE_REQUERIDO);
        this.fecha = Objects.requireNonNull(fecha);
        this.detalles = detalles;
    }

    public static RegistroDespacho nuevo(Trabajador trabajadorRealiza, Trabajador trabajadorRecibe, OffsetDateTime fecha) throws BusinessException {
        ComunValidator.validarFechaNoFutura(fecha, FECHA_FUTURA);
        return new RegistroDespacho(null, trabajadorRealiza,trabajadorRecibe,fecha, new ArrayList<>());
    }

    public static RegistroDespacho construir(Integer id, Trabajador trabajadorRealiza, Trabajador trabajadorRecibe, OffsetDateTime fecha, List<DetalleDespacho> detalles) {
        return new RegistroDespacho(id, trabajadorRealiza,trabajadorRecibe,fecha, detalles);
    }

    public Integer getId() {
        return id;
    }

    public Trabajador getTrabajadorRealiza() {
        return trabajadorRealiza;
    }

    public Trabajador getTrabajadorRecibe() {
        return trabajadorRecibe;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public List<DetalleDespacho> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDespacho> detalles) {
        this.detalles = detalles;
    }
}
