package org.example.backendwayplanner.DTOs.Itinerarios;

import java.time.LocalDate;

public class FechasDTO {
    private Long idViaje;
    private LocalDate fecha;

    public FechasDTO(Long idViaje,LocalDate fecha) {
        this.fecha = fecha;
        this.idViaje = idViaje;
    }

    public FechasDTO() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long id) {
        this.idViaje = id;
    }
}
