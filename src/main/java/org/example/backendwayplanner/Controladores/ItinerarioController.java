package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Itinerarios.FechasDTO;
import org.example.backendwayplanner.DTOs.Itinerarios.ItinerarioDTO;
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

    // Listar todos los itinerarios por el id de un viaje
    @GetMapping("/viaje/{id}")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(@PathVariable Long id) {
        return itinerarioService.obtenerItinerariosPorViajeId(id);
    }

    // Listar itinerarios por el id de un viaje y el id de un dia
    @PostMapping("/viaje/dia")
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(@RequestBody FechasDTO fechas){
        return itinerarioService.obtenerItinerariosPorViajeIdYDia(fechas.getIdViaje(), fechas.getIdDia());
    }

    // Crear un itinerario con foto, que usamos el objeto ItinerarioDTO y un MultipartFile para la foto
    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItinerarioDTO crearItinerario(
            @RequestPart("itinerario") ItinerarioDTO itinerarioDTO,
            @RequestPart(value="foto", required = false) MultipartFile foto
    ) throws Exception {
        if (foto == null) {
            return itinerarioService.crearItinerario(itinerarioDTO);
        }
        Long oid = itinerarioService.guardarFotoComoLargeObject(foto);
        return itinerarioService.crearItinerarioConFoto(itinerarioDTO, oid);
    }

    // Actualizar un itinerario con foto, que usamos el objeto ItinerarioDTO y un MultipartFile para la foto
    @PutMapping(value = "/actualizar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItinerarioDTO actualizarItinerario(@RequestPart("itinerario") ItinerarioDTO itinerarioDTO, @RequestPart(value="foto", required = false ) MultipartFile foto) {
        try {
            Long oid = itinerarioService.guardarFotoComoLargeObject(foto);
            return itinerarioService.actualizarItinerarioConFoto(itinerarioDTO, oid);
        } catch (Exception e) {
            return itinerarioService.actualizarItinerario(itinerarioDTO);
        }
    }

    // Obtener todos los itinerarios en las rutas
    @GetMapping("/rutas/{id}")
    public List<ItinerarioDTO> obtenerItinerariosEnRuta(@PathVariable Long id) {
        return itinerarioService.obtenerItinerariosEnRuta(id);
    }

    // Obtener itinerarios en una ruta por días
    @PostMapping("/rutas/dias")
    public List<ItinerarioDTO> obtenerItinerariosEnRutaPorDias(@RequestBody FechasDTO fechas) {
        return itinerarioService.obtenerItinerariosRutaYDia(fechas.getIdViaje(), fechas.getIdDia());
    }

    // Borrar itinerarios en una ruta por id de itinerario
    @DeleteMapping("/rutas/eliminarRuta/{id}")
    public void eliminarItinerariosEnRuta(@PathVariable Long id) {
        itinerarioService.eliminarItinerariosEnRuta(id);
    }

    // Eliminar un itinerario solo de itinerario, es decir, ponemos el booleano en false
    @DeleteMapping("/eliminarEnItinerario/{id}")
    public void eliminarItinerarioBooleano(@PathVariable Long id) {
        itinerarioService.eliminarItinerarioEnItinerario(id);
    }

    // Eliminar un itinerario por id de itinerario
    @DeleteMapping("/eliminar/{id}")
    public void eliminarItinerario(@PathVariable Long id) {
        itinerarioService.borrarItinerario(id);
    }

}
