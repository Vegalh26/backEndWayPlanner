package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.CategoriaLugar;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "itinerario")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actividad;

    private String ubicacion;

    private LocalTime hora;

    @Lob
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;

    @ManyToOne
    private BilleteEntrada billete;

    @ManyToOne
    private Dia dia;

    public Itinerario(String actividad, String ubicacion, LocalTime hora, byte[] foto, CategoriaLugar categoria, BilleteEntrada billete, Dia dia) {
        this.actividad = actividad;
        this.ubicacion = ubicacion;
        this.hora = hora;
        this.foto = foto;
        this.categoria = categoria;
        this.billete = billete;
        this.dia = dia;
    }

    public Itinerario() {
        // Constructor vacío
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public CategoriaLugar getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLugar categoria) {
        this.categoria = categoria;
    }

    public BilleteEntrada getBillete() {
        return billete;
    }

    public void setBillete(BilleteEntrada billete) {
        this.billete = billete;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
