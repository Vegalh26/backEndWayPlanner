package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import org.example.backendwayplanner.Enums.CategoriaLugar;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "itinerario")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actividad;

    private LocalTime hora;

    private String latitud;

    private String longitud;

    private boolean estaEnRuta;

    private boolean apareceEnItinerario;

    private String medioTransporte;

    private String duracion;

    @Lob
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;

    @ManyToOne
    private Billete billete;

    @ManyToOne
    private Dia dia;

    @OneToMany
    private List<Horario> horarios;

    public Itinerario() {

    }

    public Itinerario(Long id, String actividad, LocalTime hora, String latitud, String longitud, boolean estaEnRuta, boolean apareceEnItinerario, String medioTransporte, String duracion, byte[] foto, CategoriaLugar categoria, Billete billete, Dia dia, List<Horario> horarios) {
        this.id = id;
        this.actividad = actividad;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estaEnRuta = estaEnRuta;
        this.apareceEnItinerario = apareceEnItinerario;
        this.medioTransporte = medioTransporte;
        this.duracion = duracion;
        this.foto = foto;
        this.categoria = categoria;
        this.billete = billete;
        this.dia = dia;
        this.horarios = horarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public boolean isEstaEnRuta() {
        return estaEnRuta;
    }

    public void setEstaEnRuta(boolean estaEnRuta) {
        this.estaEnRuta = estaEnRuta;
    }

    public boolean apareceEnItinerario() {
        return apareceEnItinerario;
    }

    public void setApareceEnItinerario(boolean apareceEnItinerario) {
        this.apareceEnItinerario = apareceEnItinerario;
    }

    public String getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(String medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public CategoriaLugar getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLugar categoria) {
        this.categoria = categoria;
    }

    public Billete getBillete() {
        return billete;
    }

    public void setBillete(Billete billete) {
        this.billete = billete;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
