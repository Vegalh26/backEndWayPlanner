package org.example.backendwayplanner.Dtos.Notificaciones;

import lombok.Getter;
import lombok.Setter;
import org.example.backendwayplanner.Enums.EstadoNotificacion;
import org.example.backendwayplanner.Enums.TipoNotificacion;

import java.time.LocalDateTime;


public class NotificacionListaDTO {
    private Long id;
    private String mensaje;
    private TipoNotificacion tipo;
    private EstadoNotificacion estado;
    private LocalDateTime fechaEnvio;

    public NotificacionListaDTO(Long id, String mensaje, TipoNotificacion tipo, EstadoNotificacion estado, LocalDateTime fechaEnvio) {
        this.id = id;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoNotificacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoNotificacion estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
