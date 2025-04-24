package org.example.backendwayplanner.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GastosResumenDTO {
    private Long viajeId;
    private String nombreViaje;
    private Double totalIngresos;
    private Double totalGastos;
    private Double saldo;
}
