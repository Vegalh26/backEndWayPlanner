package org.example.backendwayplanner.DTOs.Notificaciones;

import java.time.LocalTime;

public class HoraNotificacionDTO {
    private LocalTime hora;

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
