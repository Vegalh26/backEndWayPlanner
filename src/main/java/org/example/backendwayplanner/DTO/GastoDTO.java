package org.example.backendwayplanner.DTO;

import lombok.Data;
import org.example.backendwayplanner.Enums.CategoriaGasto;

import java.util.Date;

@Data
public class GastoDTO {
    private String titulo;
    private Double cantidad;
    private boolean esIngreso;
    private CategoriaGasto categoria;
    private Date fecha;
    private Long viajeId;
}
