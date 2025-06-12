package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTO.ViajeDTO;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Servicios.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    // Crea un nuevo viaje con los datos recibidos en el cuerpo de la petición
    @PostMapping("/crear")
    public Viaje crearViaje(@RequestBody Viaje viaje) {
        return viajeService.crearViaje(viaje);
    }

    // Lista todos los viajes asociados a un usuario específico
    @GetMapping("/listarPorUsuario/{usuarioId}")
    public List<ViajeDTO> listarViajesPorUsuario(@PathVariable Long usuarioId) {
        return viajeService.listarViajesporUsuarioId(usuarioId);
    }

    // Devuelve los datos de un viaje específico por su ID
    @GetMapping("/viajePorId/{id}")
    public ViajeDTO obtenerViajePorId(@PathVariable Long id) {
        return viajeService.obtenerViajePorId(id).orElse(null);
    }

    // Actualiza un viaje existente con los nuevos datos enviados
    @PutMapping("/actualizar/{id}")
    public Viaje actualizarViaje(@PathVariable Long id, @RequestBody ViajeDTO viaje) {
        viaje.setId(id); // Se asegura que el ID sea el correcto para actualizar
        return viajeService.actualizarViaje(viaje);
    }

    // Elimina un viaje por su ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminarViaje(@PathVariable Long id) {
        // Se busca el viaje y se transforma en entidad para poder eliminarlo
        Viaje viaje = viajeService.obtenerViajePorId(id).map(v -> {
            Viaje viajeEntidad = new Viaje();
            viajeEntidad.setId(v.getId());
            return viajeEntidad;
        }).orElse(null);

        if (viaje != null) {
            viajeService.eliminarViaje(viaje);
        }
    }
}
