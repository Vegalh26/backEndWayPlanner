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

    @PostMapping("/crear")
    public Viaje crearViaje(@RequestBody Viaje viaje) {
        return viajeService.crearViaje(viaje);
    }

    @GetMapping("/listarPorUsuario/{usuarioId}")
    public List<ViajeDTO> listarViajesPorUsuario(@PathVariable Long usuarioId) {
        return viajeService.listarViajesporUsuarioId(usuarioId);
    }

    @GetMapping("/viajePorId/{id}")
    public ViajeDTO obtenerViajePorId(@PathVariable Long id) {
        return viajeService.obtenerViajePorId(id).orElse(null);
    }

    @PutMapping("/actualizar/{id}")
    public Viaje actualizarViaje(@PathVariable Long id, @RequestBody Viaje viaje) {
        viaje.setId(id);
        return viajeService.actualizarViaje(viaje);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarViaje(@PathVariable Long id) {
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
