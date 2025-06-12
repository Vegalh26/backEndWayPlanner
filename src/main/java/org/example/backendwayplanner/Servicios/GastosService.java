package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Gastos.VerGastosDTO;
import org.example.backendwayplanner.DTOs.Gastos.GastoDTO;
import org.example.backendwayplanner.DTOs.Gastos.GastosResumenDTO;
import org.example.backendwayplanner.Entidades.Gastos;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Repositorios.GastosRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


import java.time.LocalDate;
import java.util.List;

@Service
public class GastosService {

    @Autowired
    private final GastosRepository gastosRepository;

    @Autowired
    private final ViajeRepository viajeRepository;

    // Constructor para inyectar los repositorios
    public GastosService(GastosRepository gastosRepository, ViajeRepository viajeRepository) {
        this.gastosRepository = gastosRepository;
        this.viajeRepository = viajeRepository;
    }

    // Devuelve un resumen del viaje con total de ingresos, gastos y saldo
    public GastosResumenDTO obtenerResumenDeViaje(Long viajeId) {
        Viaje viaje = viajeRepository.findById(viajeId)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        List<Gastos> listaGastos = gastosRepository.findByViajeId(viajeId);

        // Calcular ingresos
        double totalIngresos = listaGastos.stream()
                .filter(Gastos::isEsIngreso)
                .mapToDouble(Gastos::getCantidad)
                .sum();

        // Calcular gastos
        double totalGastos = listaGastos.stream()
                .filter(g -> !g.isEsIngreso())
                .mapToDouble(Gastos::getCantidad)
                .sum();

        // Calcular saldo
        double saldo = totalIngresos - totalGastos;

        return new GastosResumenDTO(
                viaje.getId(),
                viaje.getNombre(),
                totalIngresos,
                totalGastos,
                saldo
        );
    }

    // Guarda un nuevo gasto o ingreso asociado a un viaje
    public Gastos guardarGasto(GastoDTO gastoDTO) {
        Viaje viaje = viajeRepository.findById(gastoDTO.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        Gastos gasto = new Gastos();
        gasto.setTitulo(gastoDTO.getTitulo());
        gasto.setCantidad(gastoDTO.getCantidad());
        gasto.setEsIngreso(gastoDTO.isEsIngreso());
        gasto.setCategoria(gastoDTO.getCategoria());
        gasto.setFecha(gastoDTO.getFecha() != null ? gastoDTO.getFecha() : LocalDate.now());
        gasto.setViaje(viaje);

        return gastosRepository.save(gasto);
    }

    // Devuelve una lista de fechas con los gastos o ingresos de cada día y sus detalles
    public List<VerGastosDTO> obtenerDiasConGastosOIngresosYDetalles(Long viajeId) {
        List<Gastos> gastos = gastosRepository.findByViajeId(viajeId);

        return gastos.stream()
                .collect(Collectors.groupingBy(Gastos::getFecha)) // Agrupa por fecha
                .entrySet()
                .stream()
                .map(entry -> new VerGastosDTO(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(gasto -> new GastoDTO(
                                        gasto.getId(),
                                        gasto.getTitulo(),
                                        gasto.getCantidad(),
                                        gasto.isEsIngreso(),
                                        gasto.getCategoria(),
                                        gasto.getFecha(),
                                        gasto.getViaje().getId()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    // Actualiza un gasto existente
    public Gastos actualizarGasto(Long id, GastoDTO gastoDTO) {
        Gastos gastoExistente = gastosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

        gastoExistente.setTitulo(gastoDTO.getTitulo());
        gastoExistente.setCantidad(gastoDTO.getCantidad());
        gastoExistente.setEsIngreso(gastoDTO.isEsIngreso());
        gastoExistente.setCategoria(gastoDTO.getCategoria());

        if (gastoDTO.getFecha() != null) {
            gastoExistente.setFecha(gastoDTO.getFecha());
        }

        return gastosRepository.save(gastoExistente);
    }

    // Elimina un gasto por su ID
    public void eliminarGasto(Long id) {
        Gastos gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        gastosRepository.delete(gasto);
    }

    // Obtiene los detalles de un gasto específico por ID
    public GastoDTO obtenerGastoPorId(Long id) {
        Gastos gasto = gastosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

        return new GastoDTO(
                gasto.getId(),
                gasto.getTitulo(),
                gasto.getCantidad(),
                gasto.isEsIngreso(),
                gasto.getCategoria(),
                gasto.getFecha(),
                gasto.getViaje().getId()
        );
    }
}
