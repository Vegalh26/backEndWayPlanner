package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaBillete;

@Entity
@Table(name = "billetes_entradas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BilleteEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaBillete categoria;

    @Lob
    private byte[] pdf;

    private String ubicacion;

    @ManyToOne
    private Viaje viaje;
}
