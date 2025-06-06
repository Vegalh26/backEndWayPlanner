package org.example.backendwayplanner.Controladores;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.DTOs.Gastos.VerGastosDTO;
import org.example.backendwayplanner.DTOs.Gastos.GastoDTO;
import org.example.backendwayplanner.DTOs.Gastos.GastosResumenDTO;
import org.example.backendwayplanner.Entidades.Gastos;
import org.example.backendwayplanner.Servicios.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class GastosController {
    @Autowired
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
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Gastos> actualizarGasto(@PathVariable Long id, @RequestBody GastoDTO gastoDTO) {
        Gastos gastoActualizado = gastosService.actualizarGasto(id, gastoDTO);
        return ResponseEntity.ok(gastoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarGasto(@PathVariable Long id) {
        gastosService.eliminarGasto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoDTO> obtenerGastoPorId(@PathVariable Long id) {
        GastoDTO gastoDTO = gastosService.obtenerGastoPorId(id);
        return ResponseEntity.ok(gastoDTO);
    }
}
