package org.example.backendwayplanner.DTO;

import lombok.Data;

import java.util.Date;


@Data
public class RegistroDTO {

    private Long id;
    private String nombre;
    private String email;
    private String contraseña;
    private String telefono;
    private Date fechaRegistro = new Date();

}
