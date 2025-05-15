package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.Dtos.Billetes.CategoriasBilleteDTO;
import org.example.backendwayplanner.Dtos.Billetes.CrearBilleteDTO;
import org.example.backendwayplanner.Dtos.Billetes.ListarBilletesDTO;
import org.example.backendwayplanner.Entidades.Billete;
import org.example.backendwayplanner.Enums.CategoriaBillete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.backendwayplanner.Servicios.BilleteService;

import java.util.List;

@RestController
@RequestMapping("/billetes")
public class BilleteController {

    @Autowired
    private BilleteService billeteService;

    // Ver todos los billetes de un viaje en su categoría
    @GetMapping("/viaje/{viajeId}/categoria/{categoria}")
    public List<ListarBilletesDTO> obtenerPorCategoriaYViaje(
            @PathVariable Long viajeId,
            @PathVariable CategoriaBillete categoria) {
        return billeteService.obtenerBilletesPorCategoriaYViaje(categoria, viajeId);
    }

    // Ver todas las Categorías de Billetes y su cantidad
    @GetMapping("/categorias")
    public List<CategoriasBilleteDTO> listarCategoriasConCantidad() {
        return billeteService.obtenerCategoriasConCantidad();
    }


    // CRUD Billete
    // ---------------------------------------
    // Crear un billete
    @PostMapping("/nuevo_billete")
    public List<ListarBilletesDTO> crearBillete(@RequestBody CrearBilleteDTO crearBilleteDTO) {
       return billeteService.crearBillete(crearBilleteDTO);
    }

    // Actualizar un billete
    @PutMapping("/actualizar_billete/{billeteId}")
    public List<ListarBilletesDTO> actualizarBillete(
            @PathVariable Long billeteId,
            @RequestBody CrearBilleteDTO crearBilleteDTO) {
        return billeteService.actualizarBillete(billeteId, crearBilleteDTO);
    }

    // Eliminar un billete
    @DeleteMapping("/eliminar_billete/{billeteId}")
    public List<ListarBilletesDTO> eliminarBillete(@PathVariable Long billeteId) {
        return billeteService.eliminarBillete(billeteId);
    }

}
