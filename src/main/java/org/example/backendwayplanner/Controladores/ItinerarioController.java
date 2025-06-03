package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Itinerarios.FechasDTO;
import org.example.backendwayplanner.DTOs.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Servicios.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/itinerarios")
@CrossOrigin(origins = "*")
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping("/viaje/{id}")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(@PathVariable Long id) {
        return itinerarioService.obtenerItinerariosPorViajeId(id);
    }

    @PostMapping("/viaje/dia")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(@RequestBody FechasDTO fechas){
        return itinerarioService.obtenerItinerariosPorViajeIdYDia(fechas.getIdViaje(), fechas.getIdDia());
    }

    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItinerarioDTO crearItinerario(
            @RequestPart("itinerario") ItinerarioDTO itinerarioDTO,
            @RequestPart("foto") MultipartFile foto
    ) throws Exception {
        Long oid = itinerarioService.guardarFotoComoLargeObject(foto);
        return itinerarioService.crearItinerarioConFoto(itinerarioDTO, oid);
    }


    @GetMapping("/rutas/{id}")
    public List<ItinerarioDTO> obtenerItinerariosEnRuta(@PathVariable Long id) {
        return itinerarioService.obtenerItinerariosEnRuta(id);
    }

    @PostMapping("/rutas/dias")
    public List<ItinerarioDTO> obtenerItinerariosEnRutaPorDias(@RequestBody FechasDTO fechas) {
        return itinerarioService.obtenerItinerariosRutaYDia(fechas.getIdViaje(), fechas.getIdDia());
    }

    @DeleteMapping("/rutas/eliminarRuta/{id}")
    public void eliminarItinerariosEnRuta(@PathVariable Long id) {
        itinerarioService.eliminarItinerariosEnRuta(id);
    }

    @DeleteMapping("/eliminarEnItinerario/{id}")
    public void eliminarItinerarioBooleano(@PathVariable Long id) {
        itinerarioService.eliminarItinerarioEnItinerario(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarItinerario(@PathVariable Long id) {
        itinerarioService.borrarItinerario(id);
    }

}
