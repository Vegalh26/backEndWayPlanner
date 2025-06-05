package org.example.backendwayplanner.Dtos.Maletas;

public class ObjetoSeleccionadoDTO {
    private Boolean isSelected;


    // Constructor
    public ObjetoSeleccionadoDTO(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ObjetoSeleccionadoDTO() {
        // Constructor vacío
    }

    // Getters y Setters
    // ------------------------------------------------
    // Getter y Setter de isSelected
    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
}
