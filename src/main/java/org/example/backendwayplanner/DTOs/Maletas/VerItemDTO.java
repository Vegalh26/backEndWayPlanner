package org.example.backendwayplanner.DTOs.Maletas;

public class VerItemDTO {
    private Long id;
    private String nombre;
    private Integer cantidad;
    private String categoria;
    private Boolean isSelected;
    private Long maletaId;


    // Constructor
    public VerItemDTO(Long id, String nombre, Integer cantidad, String categoria, Boolean isSelected, Long maletaId) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.isSelected = isSelected;
        this.maletaId = maletaId;
    }

    public VerItemDTO() {
        // Constructor vacío
    }


    // Getters y Setters
    // ------------------------------------------------
    // Getter y Setter de id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de cantidad
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter de categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter y Setter de isSelected
    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    // Getter y Setter de maletaId
    public Long getMaletaId() {
        return maletaId;
    }

    public void setMaletaId(Long maletaId) {
        this.maletaId = maletaId;
    }
}
