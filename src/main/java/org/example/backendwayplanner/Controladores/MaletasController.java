package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.Dtos.Maletas.CrearMaletaDTO;
import org.example.backendwayplanner.Entidades.Maleta;
import org.example.backendwayplanner.Dtos.Maletas.ListarMaletasDTO;
import org.example.backendwayplanner.Servicios.MaletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maletas")
@CrossOrigin(origins = "*")
public class MaletasController {

    @Autowired
    private MaletaService maletaService;

    // Ver todas las maletas de un viaje
    @GetMapping("/todas_las_maletas/{id}")
    public List<ListarMaletasDTO> getMaletasByViajeId(@PathVariable Long id) {
        return maletaService.getMaletasByViajeId(id);
    }

    // Ver una maleta por ID
    @GetMapping("/maleta/{id}")
    public ListarMaletasDTO getMaletaById(@PathVariable Long id) {
        return maletaService.getMaletaById(id);
    }

    // CRUD Maleta
    // ---------------------------------------
    // Crear una maleta
    @PostMapping("/nueva_maleta")
    public List<ListarMaletasDTO> crearMaleta(@RequestBody CrearMaletaDTO crearMaletaDTO) {
        return maletaService.crearMaleta(crearMaletaDTO);
    }

    // Actualizar una maleta
    @PutMapping("/actualizar_maleta/{id}")
    public List<ListarMaletasDTO> actualizarMaleta(@PathVariable Long id, @RequestBody Maleta maleta) {
        return maletaService.actualizarMaleta(id, maleta);
    }

    // Eliminar una maleta
    @DeleteMapping("/eliminar_maleta/{id}")
    public List<ListarMaletasDTO> eliminarMaleta(@PathVariable Long id) {
        return maletaService.eliminarMaleta(id);
    }
    // ---------------------------------------

}
