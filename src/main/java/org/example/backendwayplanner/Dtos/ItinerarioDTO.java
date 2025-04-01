package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendwayplanner.Entidades.Dia;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItinerarioDTO {

    Long id;
    String actividad;
    String ubicacion;
    String hora;
    byte[] foto;
    String categoria;
    String nombreBillete;
    Dia dia;

}
