package com.Roman.facturacion.modelo;

import com.Roman.facturacion.Calculadores.CalculadorSiguienteNumeroParaAnyo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentYearCalculator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter

@View(members = "anyo, numero, fecha;" + "cliente;" +
        "detalles" + "observaciones")

public class Factura {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Hidden
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    String oid;

    @Column(length = 6)
    @DefaultValueCalculator(CurrentYearCalculator.class)
    int anyo;

    @Column(length=6)
    @DefaultValueCalculator(value= CalculadorSiguienteNumeroParaAnyo.class,
            properties=@PropertyValue(name="anyo")
    )
    int numero;


    @Required
    @DefaultValueCalculator(CurrentYearCalculator.class)
    LocalDate fecha;

    @TextArea
    String obsevaciones;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    Cliente cliente;

    @ElementCollection
            @ListProperties("Producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;




}
