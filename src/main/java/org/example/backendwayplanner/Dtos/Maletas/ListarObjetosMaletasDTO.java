package org.example.backendwayplanner.DTOs.Maletas;

public class ListarObjetosMaletasDTO {
    private Long id;
    private String nombre;
    private int cantidad;
    private String categoria;
    private boolean isSelected;
    private Long maletaId;

    // Constructor
    public ListarObjetosMaletasDTO(Long id, String nombre, int cantidad, String categoria, boolean isSelected, Long maletaId) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.isSelected = isSelected;
        this.maletaId = maletaId;
    }

    public ListarObjetosMaletasDTO() {
        // Constructor vacío
    }


    // Getters y Setters
    // ------------------------------------------------
    // Getter y Setter de Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter de Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de Cantidad
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter de Categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter y Setter de isSelected
    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
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
