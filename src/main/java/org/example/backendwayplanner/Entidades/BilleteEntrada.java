package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.CategoriaBillete;

@Entity
@Table(name = "billetes_entradas")
public class BilleteEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Viaje viaje;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private CategoriaBillete categoria;
    @Lob
    private byte[] pdf;
    private String ubicacion;
}
