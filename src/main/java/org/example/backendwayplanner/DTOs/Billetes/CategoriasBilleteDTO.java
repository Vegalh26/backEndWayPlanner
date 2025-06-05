package org.example.backendwayplanner.DTOs.Billetes;

public class CategoriasBilleteDTO {
    // Atributos
    private String nombre;
    private Long cantidad;

    // Constructor
    public CategoriasBilleteDTO(String nombre, Long cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    // ---------------------------------------
    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para cantidad
    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
