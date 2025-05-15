package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.DiasSemana;

import java.time.LocalTime;

@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiasSemana dia;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    private boolean isClosed;

    @ManyToOne
    private Itinerario itinerario;

    public Horario(DiasSemana dia, LocalTime horaInicio, LocalTime horaFin, boolean isClosed) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.isClosed = isClosed;
    }

    public Horario() {
        // Constructor vacío
    }

    // Getters y Setters
    // ---------------------------------------
    // Getter y Setter para el Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para el Día
    public DiasSemana getDia() {
        return dia;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }

    // Getter y Setter para la Hora de Inicio
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    // Getter y Setter para la Hora de Fin
    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    // Getter y Setter para el Estado de Cierre
    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    // Getter y Setter para el Itinerario
    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

}
