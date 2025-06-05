package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Maletas.CrearObjetoDTO;
import org.example.backendwayplanner.DTOs.Maletas.ListarObjetosMaletasDTO;
import org.example.backendwayplanner.Entidades.ObjetoMaleta;
import org.example.backendwayplanner.Servicios.ObjetoMaletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemsMaleta")
@CrossOrigin(origins = "*")
public class ObjetoMaletaController {
    @Autowired
    private ObjetoMaletaService objetoMaletaService;

    // Ver todos los objetos de una maleta
    @GetMapping("/todos_los_objetos/{id}")
    public List<ListarObjetosMaletasDTO> getObjetosByMaletaId(@PathVariable Long id) {
        return objetoMaletaService.getObjetosByMaletaId(id);
    }

    // CRUD Objeto Maleta
    // ---------------------------------------
    // Crear un objeto en una maleta
    @PostMapping("/nuevo_objeto")
    public List<ListarObjetosMaletasDTO> crearObjetoMaleta(@RequestBody CrearObjetoDTO crearObjetoDTO) {
        return objetoMaletaService.crearObjetoMaleta(crearObjetoDTO);
    }

    // Actualizar un objeto de una maleta
    @PutMapping("/actualizar_objeto/{id}")
    public List<ListarObjetosMaletasDTO> actualizarObjetoMaleta(@PathVariable Long id, @RequestBody CrearObjetoDTO crearObjetoDTO) {
        return objetoMaletaService.actualizarObjetoMaleta(id, crearObjetoDTO);
    }

    // Eliminar un objeto de una maleta
    @DeleteMapping("/eliminar_objeto/{id}")
    public List<ListarObjetosMaletasDTO> eliminarObjetoMaleta(@PathVariable Long id) {
        return objetoMaletaService.eliminarObjetoMaleta(id);
    }
    // -----------------------------------------

    // Marcar un objeto como seleccionado o no
    @PutMapping("/seleccionar_objeto/{id}")
    public List<ListarObjetosMaletasDTO> seleccionarObjetoMaleta(@PathVariable Long id, @RequestBody ObjetoSeleccionadoDTO objetoSeleccionadoDTO) {
        return objetoMaletaService.seleccionarObjetoMaleta(id, objetoSeleccionadoDTO);
    }

    // Cambiar la cantidad de un objeto en una maleta
    @PutMapping("/cambiar_cantidad_objeto/{id}")
    public List<ListarObjetosMaletasDTO> cambiarCantidadObjetoMaleta(@PathVariable Long id, @RequestBody MasCantidadObjetoDTO masCantidadObjetoDTO) {
        return objetoMaletaService.cambiarCantidadObjetoMaleta(id, masCantidadObjetoDTO);
    }
}
