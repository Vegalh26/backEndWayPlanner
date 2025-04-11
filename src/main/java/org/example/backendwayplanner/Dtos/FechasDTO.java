package org.example.backendwayplanner.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechasDTO {

    private LocalDate FechaInicio;
    private LocalDate FechaFin;

}
