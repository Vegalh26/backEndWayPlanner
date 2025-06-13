package org.example.backendwayplanner.DTOs.Billetes;

public class VerBilleteDTO {
    // Atributos
    private Long id;
    private String nombre;
    private String categoria;
    private String pdf;
    private Long viajeId;

    // Constructor
    public VerBilleteDTO(Long id,String nombre, String categoria, String pdf, Long viajeId) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.pdf = pdf;
        this.viajeId = viajeId;
    }

    // Getters y Setters
    // ---------------------------------------
    // Getter y Setter para el ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para el Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para la Categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter y Setter para el PDF
    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }


    // Getter y Setter para el ID del Viaje
    public Long getViajeId() {
        return viajeId;
    }

    public void setViajeId(Long viajeId) {
        this.viajeId = viajeId;
    }
}
