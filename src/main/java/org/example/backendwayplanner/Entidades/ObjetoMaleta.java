package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.CategoriaObjeto;


@Entity
@Table(name = "objetos_maleta")
public class ObjetoMaleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Maleta maleta;
    private String nombre;
    private int cantidad;
    @Enumerated(EnumType.STRING)
    private CategoriaObjeto categoria;
}
