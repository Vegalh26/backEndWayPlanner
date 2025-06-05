package org.example.backendwayplanner.Dtos.Billetes;

public class CrearBilleteDTO {
    // Atributos
    private String nombre;
    private String categoria;
    private byte[] pdf;
    private Long viajeId;

    // Constructor
    public CrearBilleteDTO(String nombre, String categoria, byte[] pdf, Long viajeId) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.pdf = pdf;
        this.viajeId = viajeId;
    }

    // Getters y Setters
    // ---------------------------------------
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
    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
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
