package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contraseña;
    private String telefono;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro = new Date();
}
