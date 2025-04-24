package org.example.backendwayplanner.Security;

import lombok.Builder;
import lombok.Data;


public class TokenDataDTO {

    private Long id;
    private String username;
    private String rol;
    private Long fecha_creacion;
    private Long fecha_expiracion;

    public TokenDataDTO(Long id, String username, Long fecha_creacion, Long fecha_expiracion,String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
        this.fecha_expiracion = fecha_expiracion;
    }

    public TokenDataDTO(Long id, String username, Long fecha_creacion, Long fecha_expiracion) {
        this.id = id;
        this.username = username;
        this.fecha_creacion = fecha_creacion;
        this.fecha_expiracion = fecha_expiracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Long fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Long getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Long fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }
}