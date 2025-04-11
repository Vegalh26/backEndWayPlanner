package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.Dtos.FechasDTO;
import org.example.backendwayplanner.Dtos.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Servicios.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itinearios")
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping("/viaje/{id}")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(@PathVariable Long id) {
        return itinerarioService.obtenerItinerariosPorViajeId(id);
    }

    @GetMapping("/viaje/{id}/fechas")
    public List<ItinerarioDTO> obtenerItinerariosPorFecha(@PathVariable Long id, @RequestBody FechasDTO fechasDTO) {
        return itinerarioService.obtenerItinerariosPorFecha(id, fechasDTO.getFechaInicio(), fechasDTO.getFechaFin());
    }

    @PostMapping("/crear")
    public Itinerario crearItinerario(@RequestBody ItinerarioDTO itinerario) {
        return itinerarioService.crearItinerario(itinerario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarItinerario(@PathVariable Long id) {
        itinerarioService.borrarItinerario(id);
    }

}
