package com.Roman.facturacion.modelo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.openxava.annotations.NoFrame;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;

import javax.persistence.*;

@Entity
@Getter @Setter

@View(name = "simple", members = "numero, nombre")

public class Cliente {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ReferenceView("simple")
    Cliente cliente;

    @Id
    @Column(length = 6)
    private Long id;

    @Column(length = 50)
    @Required
    private String nombre;

    @Embedded
            @NoFrame
    Direccion direccion;

}
