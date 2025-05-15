package org.example.backendwayplanner.Dtos.Billetes;

public class ListarBilletesDTO {
    // Atributos
    private Long id;
    private String nombre;

    // Constructor
    public ListarBilletesDTO(Long id, String nombre, String categoria, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
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
}
