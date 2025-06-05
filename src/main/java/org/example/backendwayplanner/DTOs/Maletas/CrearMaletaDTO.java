package org.example.backendwayplanner.DTOs.Maletas;

public class CrearMaletaDTO {
    private String nombre;
    private Double peso;
    private String tipoMaleta;
    private Long viaje;

    // Constructor
    public CrearMaletaDTO(String nombre, Double peso, String tipoMaleta, Long viajeId) {
        this.nombre = nombre;
        this.peso = peso;
        this.tipoMaleta = tipoMaleta;
        this.viaje = viajeId;
    }

    public CrearMaletaDTO() {
        // Constructor vacío
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

    // Getter y Setter para peso
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    // Getter y Setter para tipoMaleta
    public String getTipoMaleta() {
        return tipoMaleta;
    }

    public void setTipoMaleta(String tipoMaleta) {
        this.tipoMaleta = tipoMaleta;
    }

    // Getter y Setter para viaje
    public Long getViaje() {
        return viaje;
    }

    public void setViaje(Long viaje) {
        this.viaje = viaje;
    }
}

