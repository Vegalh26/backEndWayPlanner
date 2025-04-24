package org.example.backendwayplanner.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.backendwayplanner.Enums.TipoNotificacion;

@Getter
@Setter
public class NotificacionDTO {
    private Long idUsuario;
    private Long idViaje;
    private String mensaje;
    private TipoNotificacion tipo;
}
