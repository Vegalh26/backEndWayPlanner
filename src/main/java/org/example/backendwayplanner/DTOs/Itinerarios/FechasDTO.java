package org.example.backendwayplanner.DTOs.Itinerarios;

import java.time.LocalDate;

public class  FechasDTO {
    private Long idViaje;
    private Long idDia;

    public FechasDTO(Long idViaje,Long idDia) {
        this.idDia = idDia;
        this.idViaje = idViaje;
    }

    public FechasDTO() {
    }

    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long fecha) {
        this.idDia = fecha;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long id) {
        this.idViaje = id;
    }
}
