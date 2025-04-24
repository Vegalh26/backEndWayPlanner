package org.example.backendwayplanner.Dtos;

import lombok.Data;
import org.example.backendwayplanner.Enums.CategoriaGasto;

import java.util.Date;


public class GastoDTO {

    private String titulo;
    private Double cantidad;
    private boolean esIngreso;
    private CategoriaGasto categoria;
    private Date fecha;
    private Long viajeId;

    public GastoDTO(String titulo, Double cantidad, boolean esIngreso, CategoriaGasto categoria, Date fecha, Long viajeId) {
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.esIngreso = esIngreso;
        this.categoria = categoria;
        this.fecha = fecha;
        this.viajeId = viajeId;
    }

    public GastoDTO() {
        // Constructor vacío
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

    public Long getViajeId() {
        return viajeId;
    }

    public void setViajeId(Long viajeId) {
        this.viajeId = viajeId;
    }
}
