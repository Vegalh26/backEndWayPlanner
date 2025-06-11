package org.example.backendwayplanner.DTOs.Login;


import org.example.backendwayplanner.Entidades.Usuario;

import java.time.LocalDate;

public class UsuarioDTO {
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;

    public UsuarioDTO(String nombre, String email, String password, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public UsuarioDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.telefono = usuario.getTelefono();
        this.password = usuario.getContrasena();
        this.fechaNacimiento = usuario.getFechaNacimiento();
    }

    public UsuarioDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
