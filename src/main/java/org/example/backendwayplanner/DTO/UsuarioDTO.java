package org.example.backendwayplanner.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String email;
    private String contraseña;
    private String telefono;
}