package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendwayplanner.Enums.CategoriaGasto;

import java.util.Date;

@Entity
@Table(name = "gastos")
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Double cantidad;

    private boolean esIngreso;

    @Enumerated(EnumType.STRING)
    private CategoriaGasto categoria;


    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;

    public Gastos(Long id, String titulo, Double cantidad, boolean esIngreso, CategoriaGasto categoria, Date fecha, Viaje viaje) {
        this.id = id;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.esIngreso = esIngreso;
        this.categoria = categoria;
        this.fecha = fecha;
        this.viaje = viaje;
    }

    public Gastos(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEsIngreso() {
        return esIngreso;
    }

    public void setEsIngreso(boolean esIngreso) {
        this.esIngreso = esIngreso;
    }

    public CategoriaGasto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaGasto categoria) {
        this.categoria = categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
}

