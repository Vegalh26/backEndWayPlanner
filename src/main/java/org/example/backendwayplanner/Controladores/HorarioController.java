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

    // Crear horarios
    @PostMapping("/crear")
    public void crearHorarios(@RequestBody List<HorarioDTO> horarios) {
        horarioService.crearHorarios(horarios);
    }


    @GetMapping("/listar/{idItinerario}")
    public List<HorarioDTO> listarHorarios(@PathVariable Long idItinerario) {
         return horarioService.listarHorarios(idItinerario);
    }

    @PutMapping ("/actualizar")
    public void actualizarHorario(@RequestBody List<HorarioDTO> horarios) {
        horarioService.actualizarHorario(horarios);
    }

    @DeleteMapping("/eliminar/{idHorario}")
    public void eliminarHorario(@PathVariable Long idHorario) {
        horarioService.eliminarHorario(idHorario);
    }

}
