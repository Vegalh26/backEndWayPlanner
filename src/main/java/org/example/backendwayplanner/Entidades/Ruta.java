package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dia dia;
    private String origen;
    private String destino;
    private String medioTransporte;
    private Date duracion;
}
