package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendwayplanner.Entidades.Dia;

public class ItinerarioDTO {

    Long id;
    String actividad;
    String ubicacion;
    String hora;
    byte[] foto;
    String categoria;
    String nombreBillete;
    Dia dia;

    public ItinerarioDTO(String actividad, String ubicacion, String hora, byte[] foto, String categoria, String nombreBillete, Dia dia) {
        this.actividad = actividad;
        this.ubicacion = ubicacion;
        this.hora = hora;
        this.foto = foto;
        this.categoria = categoria;
        this.nombreBillete = nombreBillete;
        this.dia = dia;
    }

    public ItinerarioDTO() {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreBillete() {
        return nombreBillete;
    }

    public void setNombreBillete(String nombreBillete) {
        this.nombreBillete = nombreBillete;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
