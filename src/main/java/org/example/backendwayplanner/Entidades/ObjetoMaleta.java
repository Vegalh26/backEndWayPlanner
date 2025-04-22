package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaObjeto;


@Entity
@Table(name = "objetos_maleta")
public class ObjetoMaleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private CategoriaObjeto categoria;

    @ManyToOne
    private Maleta maleta;

    public ObjetoMaleta(String nombre, int cantidad, CategoriaObjeto categoria, Maleta maleta) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.maleta = maleta;
    }

    public ObjetoMaleta() {
        // Constructor vacío
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CategoriaObjeto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaObjeto categoria) {
        this.categoria = categoria;
    }

    public Maleta getMaleta() {
        return maleta;
    }

    public void setMaleta(Maleta maleta) {
        this.maleta = maleta;
    }
}
