package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.TipoMaleta;

@Entity
@Table(name = "maletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double peso;

    @Enumerated(EnumType.STRING)
    private TipoMaleta tipoMaleta;

    @ManyToOne
    private Viaje viaje;
}
