package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Itinerarios.HorarioDTO;
import org.example.backendwayplanner.Servicios.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
@CrossOrigin(origins = "*")
public class HorarioController {


    @Autowired
    private HorarioService horarioService;

    // Crear horarios,  aprtir de una lista de horarios
    @PostMapping("/crear")
    public void crearHorarios(@RequestBody List<HorarioDTO> horarios) {
        horarioService.crearHorarios(horarios);
    }

    // Listar los horarios de un itinerario en específico
    @GetMapping("/listar/{idItinerario}")
    public List<HorarioDTO> listarHorarios(@PathVariable Long idItinerario) {
         return horarioService.listarHorarios(idItinerario);
    }

    // Actualizar toda una lista de horarios
    @PutMapping ("/actualizar")
    public void actualizarHorario(@RequestBody List<HorarioDTO> horarios) {
        horarioService.actualizarHorario(horarios);
    }

    // Borrar un horario de un itinerario por su id
    @DeleteMapping("/eliminar/{idHorario}")
    public void eliminarHorario(@PathVariable Long idHorario) {
        horarioService.eliminarHorario(idHorario);
    }

}
