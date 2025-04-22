package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class FechasDTO {

    private LocalDate FechaInicio;
    private LocalDate FechaFin;

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }


    public FechasDTO(LocalDate fechaInicio, LocalDate fechaFin) {
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
    }

    public FechasDTO() {
    }
}
