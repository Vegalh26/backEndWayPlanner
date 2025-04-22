package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UbicacionItinerarioDTO {

    Long idItinerario;
    Long idDia;
    String ubicacion;

    public UbicacionItinerarioDTO(Long idItinerario, Long idDia, String ubicacion) {
        this.idItinerario = idItinerario;
        this.idDia = idDia;
        this.ubicacion = ubicacion;
    }

    public UbicacionItinerarioDTO() {
    }

    public Long getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(Long idItinerario) {
        this.idItinerario = idItinerario;
    }

    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long idDia) {
        this.idDia = idDia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
