package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaBillete;

@Entity
@Table(name = "billetes_entradas")
public class BilleteEntrada {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaBillete categoria;

    @Lob
    private byte[] pdf;

    private String ubicacion;

    @ManyToOne
    private Viaje viaje;


    public BilleteEntrada(String nombre, CategoriaBillete categoria, byte[] pdf, String ubicacion, Viaje viaje) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.pdf = pdf;
        this.ubicacion = ubicacion;
        this.viaje = viaje;
    }

    public BilleteEntrada() {
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

    public CategoriaBillete getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaBillete categoria) {
        this.categoria = categoria;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
}
