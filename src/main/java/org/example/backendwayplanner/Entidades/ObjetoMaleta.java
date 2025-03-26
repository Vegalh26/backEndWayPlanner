package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaObjeto;


@Entity
@Table(name = "objetos_maleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoMaleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private CategoriaObjeto categoria;

    @ManyToOne
    private Maleta maleta;
}
