package org.example.backendwayplanner.DTOs.Gastos;


import lombok.AllArgsConstructor;
import lombok.Data;


public class GastosResumenDTO {

    private Long viajeId;
    private String nombreViaje;
    private Double totalIngresos;
    private Double totalGastos;
    private Double saldo;

    public GastosResumenDTO(Long viajeId, String nombreViaje, Double totalIngresos, Double totalGastos, Double saldo) {
        this.viajeId = viajeId;
        this.nombreViaje = nombreViaje;
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
        this.saldo = saldo;
    }

    public GastosResumenDTO() {
        // Constructor vacío
    }

    public Long getViajeId() {
        return viajeId;
    }

    public void setViajeId(Long viajeId) {
        this.viajeId = viajeId;
    }

    public String getNombreViaje() {
        return nombreViaje;
    }

    public void setNombreViaje(String nombreViaje) {
        this.nombreViaje = nombreViaje;
    }

    public Double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public Double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(Double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
