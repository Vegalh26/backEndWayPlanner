// Paquete donde se encuentra este controlador
package org.example.backendwayplanner.Controladores;

// Importaciones necesarias
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


    @Autowired // Inyección automática de dependencias
    private GastosService gastosService; // Servicio que contiene la lógica relacionada con los gastos

    // Endpoint para obtener un resumen de gastos de un viaje específico
    @GetMapping("/resumen/{viajeId}")
    public ResponseEntity<GastosResumenDTO> getResumenGastos(@PathVariable Long viajeId) {
        return ResponseEntity.ok(gastosService.obtenerResumenDeViaje(viajeId));
    }

    // Endpoint para crear un nuevo gasto (o ingreso)
    @PostMapping("/crear")
    public ResponseEntity<Gastos> crearGasto(@RequestBody GastoDTO gastoDTO) {
        Gastos gastoCreado = gastosService.guardarGasto(gastoDTO);
        return ResponseEntity.ok(gastoCreado);
    }

    // Endpoint para obtener los días que tienen al menos un gasto o ingreso, con sus detalles
    @GetMapping("/dias/{viajeId}")
    public ResponseEntity<List<VerGastosDTO>> obtenerDiasConGastosOIngresosYDetalles(@PathVariable Long viajeId) {
        return ResponseEntity.ok(gastosService.obtenerDiasConGastosOIngresosYDetalles(viajeId));
    }

    // Endpoint para actualizar un gasto existente por su ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Gastos> actualizarGasto(@PathVariable Long id, @RequestBody GastoDTO gastoDTO) {
        Gastos gastoActualizado = gastosService.actualizarGasto(id, gastoDTO);
        return ResponseEntity.ok(gastoActualizado);
    }

    // Endpoint para eliminar un gasto por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarGasto(@PathVariable Long id) {
        gastosService.eliminarGasto(id);
        return ResponseEntity.noContent().build(); // 204 No Content si se elimina correctamente
    }

    // Endpoint para obtener un gasto específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<GastoDTO> obtenerGastoPorId(@PathVariable Long id) {
        GastoDTO gastoDTO = gastosService.obtenerGastoPorId(id);
        return ResponseEntity.ok(gastoDTO);
    }
}
