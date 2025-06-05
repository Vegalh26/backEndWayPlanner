package org.example.backendwayplanner.DTOs.Login;

import java.time.LocalDate;


public class RegistroDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaRegistro = LocalDate.now();
    private LocalDate fechaNacimiento;

    public RegistroDTO() {
    }

    public RegistroDTO(Long id, String nombre, String email, String password, String telefono, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaRegistro = LocalDate.now();
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
