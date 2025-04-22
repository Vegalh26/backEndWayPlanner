package org.example.backendwayplanner.Dtos;

import jakarta.persistence.ManyToOne;
import org.example.backendwayplanner.Entidades.Viaje;

import java.time.LocalDate;

public class DiaDTO {

    private Long id;

    private LocalDate fecha;

    private int numeroDia;

    private String nombreViaje;

    public DiaDTO(Long id, LocalDate fecha, int numeroDia, String nombreViaje) {
        this.id = id;
        this.fecha = fecha;
        this.numeroDia = numeroDia;
        this.nombreViaje = nombreViaje;
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

    public String getNombreViaje() {
        return nombreViaje;
    }

    public void setNombreViaje(String nombreViaje) {
        this.nombreViaje = nombreViaje;
    }
}
