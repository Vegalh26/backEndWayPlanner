package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionItinerarioDTO {

    Long idItinerario;
    Long idDia;
    String ubicacion;
}
