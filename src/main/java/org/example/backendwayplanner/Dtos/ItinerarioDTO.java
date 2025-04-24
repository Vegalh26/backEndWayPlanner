package org.example.backendwayplanner.Dtos;

import org.example.backendwayplanner.Entidades.Dia;

public class ItinerarioDTO {

    Long id;
    String actividad;
    String latitud;
    String longitud;
    boolean estaEnRuta;
    String medioTransporte;
    String duracion;
    String hora;
    byte[] foto;
    String categoria;
    // El nombre del billete para asociar el itinerario con el billete y se buscará por su nombre
    String nombreBillete;
    Dia dia;

    public ItinerarioDTO(Long id, String actividad, String latitud, String longitud, boolean estaEnRuta, String medioTransporte, String duracion, String hora, byte[] foto, String categoria, String nombreBillete, Dia dia) {
        this.id = id;
        this.actividad = actividad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estaEnRuta = estaEnRuta;
        this.medioTransporte = medioTransporte;
        this.duracion = duracion;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
