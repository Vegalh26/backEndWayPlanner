package org.example.backendwayplanner.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.backendwayplanner.Enums.EstadoNotificacion;
import org.example.backendwayplanner.Enums.TipoNotificacion;

import java.time.LocalDateTime;

@Getter
@Setter
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
}
