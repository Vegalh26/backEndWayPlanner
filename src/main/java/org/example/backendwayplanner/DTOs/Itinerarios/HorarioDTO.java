package org.example.backendwayplanner.DTOs.Itinerarios;

import org.example.backendwayplanner.Enums.DiasSemana;

import java.time.LocalTime;

public class HorarioDTO {


    private Long id;
    private Long IdItinerario;
    private DiasSemana dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean isClosed;


    public HorarioDTO(Long id, Long idItinerario, DiasSemana dia, LocalTime horaInicio, LocalTime horaFin, boolean isClosed) {
        this.id = id;
        this.IdItinerario = idItinerario;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.isClosed = isClosed;
    }

    public HorarioDTO() {
        // Constructor vacío
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdItinerario() {
        return IdItinerario;
    }

    public void setIdItinerario(Long idItinerario) {
        IdItinerario = idItinerario;
    }

    public DiasSemana getDia() {
        return dia;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
