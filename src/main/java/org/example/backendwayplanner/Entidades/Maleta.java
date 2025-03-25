package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.TipoMaleta;

@Entity
@Table(name = "maletas")
public class Maleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Viaje viaje;
    private String nombre;
    private Double peso;
    @Enumerated(EnumType.STRING)
    private TipoMaleta tipoMaleta;
}
