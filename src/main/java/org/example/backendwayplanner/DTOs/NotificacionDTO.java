package org.example.backendwayplanner.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.backendwayplanner.Enums.TipoNotificacion;


public class NotificacionDTO {
    private Long idUsuario;
    private Long idViaje;
    private String mensaje;
    private TipoNotificacion tipo;

    public NotificacionDTO(Long idUsuario, Long idViaje, String mensaje, TipoNotificacion tipo) {
        this.idUsuario = idUsuario;
        this.idViaje = idViaje;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoNotificacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoNotificacion tipo) {
        this.tipo = tipo;
    }
}
