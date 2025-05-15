package org.example.backendwayplanner.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwayplanner.Enums.TipoMaleta;

import java.util.List;

@Entity
@Table(name = "maletas")
public class Maleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double peso;

    @Enumerated(EnumType.STRING)
    private TipoMaleta tipoMaleta;

    @ManyToOne
    private Viaje viaje;

    @OneToMany(mappedBy = "maletaId", cascade = CascadeType.ALL)
    private List<ObjetoMaleta> objetosMaleta;

    public Maleta(String nombre, Double peso, TipoMaleta tipoMaleta, Viaje viaje) {
        this.nombre = nombre;
        this.peso = peso;
        this.tipoMaleta = tipoMaleta;
        this.viaje = viaje;
    }

    public Maleta() {
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public TipoMaleta getTipoMaleta() {
        return tipoMaleta;
    }

    public void setTipoMaleta(TipoMaleta tipoMaleta) {
        this.tipoMaleta = tipoMaleta;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
}
