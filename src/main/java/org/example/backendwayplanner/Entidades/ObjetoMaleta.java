package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
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

    private boolean isSelected;

    @ManyToOne
    @JoinColumn(name = "maleta_id")
    private Maleta maletaId;

    public ObjetoMaleta(String nombre, int cantidad, CategoriaObjeto categoria, Maleta maletaId) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.isSelected = false;
        this.maletaId = this.maletaId;
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

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }

    public Maleta getMaletaId() {
        return maletaId;
    }

    public void setMaletaId(Maleta maletaId) {
        this.maletaId = maletaId;
    }
}
