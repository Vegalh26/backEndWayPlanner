package org.example.backendwayplanner.DTOs.Itinerarios;

import jakarta.persistence.ManyToOne;
import org.example.backendwayplanner.Entidades.Viaje;

import java.time.LocalDate;

public class DiaDTO {

    private Long id;

    private LocalDate fecha;

    private int numeroDia;

    private Long idViaje;

    public DiaDTO(Long id, LocalDate fecha, int numeroDia, Long idViaje) {
        this.id = id;
        this.fecha = fecha;
        this.numeroDia = numeroDia;
        this.idViaje = idViaje;
    }

    public DiaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(int numeroDia) {
        this.numeroDia = numeroDia;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setidViaje(Long idViaje) {
        this.idViaje = idViaje;
    }
}
