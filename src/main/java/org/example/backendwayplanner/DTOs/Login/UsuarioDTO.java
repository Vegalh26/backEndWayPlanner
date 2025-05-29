package org.example.backendwayplanner.DTOs.Login;


import org.example.backendwayplanner.Entidades.Usuario;

public class UsuarioDTO {
    private String nombre;
    private String email;
    private String password;
    private String telefono;



    public UsuarioDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.telefono = usuario.getTelefono();
        this.password = usuario.getContrasena();
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
}
