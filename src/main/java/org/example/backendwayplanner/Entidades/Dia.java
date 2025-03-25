package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dias")
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Viaje viaje;
    private Date fecha;
    private int numeroDia;
}

