package org.example.backendwayplanner.Dtos.Gastos;


import java.time.LocalDate;
import java.util.List;
import org.example.backendwayplanner.Dtos.Gastos.GastoDTO;
public class VerGastosDTO {
    private LocalDate fecha;
    private List<GastoDTO> gastos;

    public VerGastosDTO(LocalDate fecha, List<GastoDTO> gastos) {
        this.fecha = fecha;
        this.gastos = gastos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<GastoDTO> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoDTO> gastos) {
        this.gastos = gastos;
    }
}