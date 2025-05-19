package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.Dtos.Itinerarios.FechasDTO;
import org.example.backendwayplanner.Dtos.Itinerarios.ItinerarioDTO;
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

    @GetMapping("/viaje/{id}/dia")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(@RequestBody FechasDTO fechas){
        return itinerarioService.obtenerItinerariosPorViajeIdYDia(fechas.getIdViaje(), fechas.getFecha());
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
