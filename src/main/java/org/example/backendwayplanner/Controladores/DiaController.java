package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Itinerarios.DiaDTO;
import org.example.backendwayplanner.Entidades.Dia;
import org.example.backendwayplanner.Servicios.DiaService;
import org.example.backendwayplanner.Servicios.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dia")
@CrossOrigin(origins = "*")
public class DiaController {

    @Autowired
    private ItinerarioService itinerarioService;

    @Autowired
    private DiaService diaService;

    // Obtener dias por id del viajeId
    @GetMapping("/Obtenerdias/{id}")
    public List<DiaDTO> obtenerDiasPorViajeId(@PathVariable Long id) {
        List<Dia> dias = diaService.obtenerDiasPorViajeId(id);
        return dias.stream().map(diaService::transformarADTO).collect(Collectors.toList());
    }

    // Crear un nuevo dia
    @PostMapping("/CrearDia")
    public Dia crearDia(@RequestBody DiaDTO dia) {
        return diaService.crearDia(dia);
    }

    // Eliminar un dia por id
    @DeleteMapping("/EliminarDia/{id}")
    public void eliminarDia(@PathVariable Long id) {
        diaService.eliminarDia(id);
    }


}
