package com.uco.cig.infrastructure.database.postgres.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "color_producto")
public class ColorProductoEntity {
    @EmbeddedId
    private ColorProductoId id;

    public ColorProductoId getId() {
        return id;
    }

    public void setId(ColorProductoId id) {
        this.id = id;
    }
}