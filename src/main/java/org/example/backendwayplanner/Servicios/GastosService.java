package org.example.backendwayplanner.Servicios;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.DTO.GastoDTO;
import org.example.backendwayplanner.DTO.GastosResumenDTO;
import org.example.backendwayplanner.Entidades.Gastos;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Repositorios.GastosRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosService {

    @Autowired
    private final GastosRepository gastosRepository;

    @Autowired
    private final ViajeRepository viajeRepository;

    public GastosService(GastosRepository gastosRepository, ViajeRepository viajeRepository) {
        this.gastosRepository = gastosRepository;
        this.viajeRepository = viajeRepository;
    }



    public GastosResumenDTO obtenerResumenDeViaje(Long viajeId) {
        Viaje viaje = viajeRepository.findById(viajeId)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        List<Gastos> listaGastos = gastosRepository.findByViajeId(viajeId);

        double totalIngresos = listaGastos.stream()
                .filter(Gastos::isEsIngreso)
                .mapToDouble(Gastos::getCantidad)
                .sum();

        double totalGastos = listaGastos.stream()
                .filter(g -> !g.isEsIngreso())
                .mapToDouble(Gastos::getCantidad)
                .sum();

        double saldo = totalIngresos - totalGastos;

        return new GastosResumenDTO(
                viaje.getId(),
                viaje.getNombre(),
                totalIngresos,
                totalGastos,
                saldo
        );
    }

    public Gastos guardarGasto(GastoDTO gastoDTO) {
        Viaje viaje = viajeRepository.findById(gastoDTO.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        Gastos gasto = new Gastos();
        gasto.setTitulo(gastoDTO.getTitulo());
        gasto.setCantidad(gastoDTO.getCantidad());
        gasto.setEsIngreso(gastoDTO.isEsIngreso());
        gasto.setCategoria(gastoDTO.getCategoria());
        gasto.setFecha(gastoDTO.getFecha());
        gasto.setViaje(viaje);

        return gastosRepository.save(gasto);
    }

}
