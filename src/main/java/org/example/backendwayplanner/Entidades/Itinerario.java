package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaLugar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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

    private String medioTransporte;

    private LocalDateTime duracion;

    @Lob
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;

    @ManyToOne
    private BilleteEntrada billete;

    @ManyToOne
    private Dia dia;

    public Itinerario(Long id, String actividad, LocalTime hora, String latitud, String longitud, boolean estaEnRuta, String medioTransporte, LocalDateTime duracion, byte[] foto, CategoriaLugar categoria, BilleteEntrada billete, Dia dia) {
        this.id = id;
        this.actividad = actividad;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estaEnRuta = estaEnRuta;
        this.medioTransporte = medioTransporte;
        this.duracion = duracion;
        this.foto = foto;
        this.categoria = categoria;
        this.billete = billete;
        this.dia = dia;
    }

    public Itinerario() {
        // Constructor vacío
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public BilleteEntrada getBillete() {
        return billete;
    }

    public void setBillete(BilleteEntrada billete) {
        this.billete = billete;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
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

    public String getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(String medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public LocalDateTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalDateTime duracion) {
        this.duracion = duracion;
    }
}
