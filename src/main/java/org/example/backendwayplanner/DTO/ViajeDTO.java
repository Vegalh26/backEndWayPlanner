package org.example.backendwayplanner.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViajeDTO {
    private Long id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String destino;
    private String descripcion;
}
