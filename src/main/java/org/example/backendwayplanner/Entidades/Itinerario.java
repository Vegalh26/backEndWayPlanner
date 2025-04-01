package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaLugar;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "itinerario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actividad;

    private String ubicacion;

    private LocalTime hora;

    @Lob
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;

    @ManyToOne
    private BilleteEntrada billete;

    @ManyToOne
    private Dia dia;
}
