package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "viajes")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String destino;
    private String descripcion;
}

