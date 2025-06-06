package org.example.backendwayplanner.DTOs.Maletas;

public class MasCantidadObjetoDTO {
    private int cantidad;

    /* Constructor */
    public MasCantidadObjetoDTO(int cantidad) {
        this.cantidad = cantidad;
    }

    /* Getters y Setters */
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
