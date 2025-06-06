package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.DiasSemana;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "dias")
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private int numeroDia;

    @Enumerated(EnumType.STRING)
    private DiasSemana diaSemana;

    @ManyToOne
    private Viaje viaje;

    public Dia(Long id, LocalDate fecha, int numeroDia, DiasSemana diaSemana, Viaje viaje) {
        this.id = id;
        this.fecha = fecha;
        this.numeroDia = numeroDia;
        this.diaSemana = diaSemana;
        this.viaje = viaje;
    }

    public Dia(){}

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

    public DiasSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiasSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
}

