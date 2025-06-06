package org.example.backendwayplanner.DTOs.Maletas;

public class VerMaletaDTO {
    private Long id;
    private String nombre;
    private Double peso;
    private String tipoMaleta;

    // Constructor
    public VerMaletaDTO(Long id, String nombre, Double peso, String tipoMaleta) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.tipoMaleta = tipoMaleta;
    }

    public VerMaletaDTO() {
        // Constructor vacío
    }

    // Getters y Setters
    // ---------------------------------------
    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
