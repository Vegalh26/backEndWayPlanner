package org.example.backendwayplanner.Dtos.Maletas;

public class ListarMaletasDTO {
    private Long id;
    private String nombre;
    private Double peso;
    private String tipoMaleta;


    // Constructor
    public ListarMaletasDTO(Long id, String nombre, Double peso, String tipoMaleta) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.tipoMaleta = tipoMaleta;
    }

    public ListarMaletasDTO() {
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

    // Getter y Setter de Peso
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    // Getter y Setter de TipoMaleta
    public String getTipoMaleta() {
        return tipoMaleta;
    }

    public void setTipoMaleta(String tipoMaleta) {
        this.tipoMaleta = tipoMaleta;
    }
}
