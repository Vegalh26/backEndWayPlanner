package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.CategoriaLugar;
import java.util.Date;

@Entity
@Table(name = "itinerario")
public class Itinerario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dia dia;
    private String actividad;
    private String ubicacion;
    private Date hora;
    @Lob
    private byte[] foto;
    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;
    @ManyToOne
    private BilleteEntrada billete;
}
