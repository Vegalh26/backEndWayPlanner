package org.example.backendwayplanner.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {

    public Integer estado;
    private String token;
    private String mensaje;
    private Object cuerpo;

    public RespuestaDTO(Integer estado, String token, String mensaje, Object cuerpo) {
        this.estado = estado;
        this.token = token;
        this.mensaje = mensaje;
        this.cuerpo = cuerpo;
    }

    public RespuestaDTO() {
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Object cuerpo) {
        this.cuerpo = cuerpo;
    }
}
