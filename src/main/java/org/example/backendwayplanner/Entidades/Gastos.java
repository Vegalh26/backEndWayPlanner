package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendwayplanner.Enums.CategoriaGasto;

import java.util.Date;

@Entity
@Table(name = "gastos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Double cantidad;

    private boolean esIngreso;

    @Enumerated(EnumType.STRING)
    private CategoriaGasto categoria;


    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;
}

