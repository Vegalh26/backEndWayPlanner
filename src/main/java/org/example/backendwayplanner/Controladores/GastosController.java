package org.example.backendwayplanner.Controladores;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.DTOs.VerGastosDTO;
import org.example.backendwayplanner.Dtos.GastoDTO;
import org.example.backendwayplanner.Dtos.GastosResumenDTO;
import org.example.backendwayplanner.Entidades.Gastos;
import org.example.backendwayplanner.Servicios.GastosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor

public class GastosController {
    private GastosService gastosService;

    @GetMapping("/resumen/{viajeId}")
    public ResponseEntity<GastosResumenDTO> getResumenGastos(@PathVariable Long viajeId) {
        return ResponseEntity.ok(gastosService.obtenerResumenDeViaje(viajeId));
    }

    @PostMapping("/crear")
    public ResponseEntity<Gastos> crearGasto(@RequestBody GastoDTO gastoDTO) {
        Gastos gastoCreado = gastosService.guardarGasto(gastoDTO);
        return ResponseEntity.ok(gastoCreado);
    }
    @GetMapping("/dias/{viajeId}")
    public ResponseEntity<List<VerGastosDTO>> obtenerDiasConGastosOIngresosYDetalles(@PathVariable Long viajeId) {
        return ResponseEntity.ok(gastosService.obtenerDiasConGastosOIngresosYDetalles(viajeId));
    }
}
