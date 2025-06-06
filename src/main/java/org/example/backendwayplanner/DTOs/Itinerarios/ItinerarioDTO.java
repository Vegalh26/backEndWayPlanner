package org.example.backendwayplanner.DTOs.Itinerarios;

import org.example.backendwayplanner.Entidades.Billete;
import org.example.backendwayplanner.Entidades.Dia;
import org.example.backendwayplanner.Entidades.Horario;
import org.example.backendwayplanner.Enums.CategoriaLugar;
import org.example.backendwayplanner.Enums.Transporte;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ItinerarioDTO {

    Long id;
    String actividad;
    String latitud;
    String longitud;
    boolean estaEnRuta;
    boolean apareceEnItinerario;
    LocalTime hora;
    Transporte medioTransporte;
    String duracion;
    String foto;
    CategoriaLugar categoria;
    Long idbillete;
    Long iddia;
    List<HorarioDTO> horarios;

    public ItinerarioDTO(Long id, String actividad, String latitud, String longitud, boolean estaEnRuta, boolean apareceEnItinerario, LocalTime hora, Transporte medioTransporte,String duracion, String foto, CategoriaLugar categoria, Long idbillete, Long iddia, List<HorarioDTO> horarios) {
        this.id = id;
        this.actividad = actividad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estaEnRuta = estaEnRuta;
        this.apareceEnItinerario = apareceEnItinerario;
        this.hora = hora;
        this.medioTransporte = medioTransporte;
        this.duracion = duracion;
        this.foto = foto;
        this.categoria = categoria;
        this.idbillete = idbillete;
        this.iddia = iddia;
        this.horarios = horarios;
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

    public boolean isApareceEnItinerario() {
        return apareceEnItinerario;
    }

    public void setApareceEnItinerario(boolean apareceEnItinerario) {
        this.apareceEnItinerario = apareceEnItinerario;
    }


    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Transporte getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(Transporte medioTransporte) {
        this.medioTransporte = medioTransporte;
    }


    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public CategoriaLugar getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLugar categoria) {
        this.categoria = categoria;
    }

    public Long getIdbillete() {
        return idbillete;
    }

    public void setIdbillete(Long idbillete) {
        this.idbillete = idbillete;
    }

    public Long getIddia() {
        return iddia;
    }

    public void setIddia(Long iddia) {
        this.iddia = iddia;
    }

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }
}
