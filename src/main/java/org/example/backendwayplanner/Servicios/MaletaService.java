package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.Maletas.CrearMaletaDTO;
import org.example.backendwayplanner.Entidades.Maleta;
import org.example.backendwayplanner.Dtos.Maletas.ListarMaletasDTO;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Enums.TipoMaleta;
import org.example.backendwayplanner.Repositorios.MaletaRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaletaService {
    @Autowired
    private MaletaRepository maletaRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    // Ver todas las maletas de un viaje
    public List<ListarMaletasDTO> getMaletasByViajeId(Long id) {
        List<Maleta> maletas = maletaRepository.findByViajeId(id);
        return maletas.stream()
                .map(maleta -> new ListarMaletasDTO(maleta.getId(), maleta.getNombre(), maleta.getPeso(), maleta.getTipoMaleta().toString()))
                .toList();
    }

    // CRUD Maleta
    // ---------------------------------------
    // Crear una maleta
    public List<ListarMaletasDTO> crearMaleta(CrearMaletaDTO crearMaletaDTO) {
        // Buscar el Viaje por ID
        Viaje viaje = viajeRepository.findById(crearMaletaDTO.getViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        // Crear y guardar la nueva maleta
        Maleta maleta = new Maleta();
        maleta.setNombre(crearMaletaDTO.getNombre());
        maleta.setPeso(crearMaletaDTO.getPeso());
        maleta.setTipoMaleta(TipoMaleta.valueOf(crearMaletaDTO.getTipoMaleta()));
        maleta.setViaje(viaje);

        maletaRepository.save(maleta);

        // Devolver la lista actualizada de maletas del viaje
        return getMaletasByViajeId(viaje.getId());
    }

    // Actualizar una maleta
    public List<ListarMaletasDTO> actualizarMaleta(Long id, Maleta maleta) {
        // Buscar la maleta por ID
        Maleta maletaExistente = maletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maleta no encontrada"));

        // Actualizar los campos de la maleta existente
        maletaExistente.setNombre(maleta.getNombre());
        maletaExistente.setPeso(maleta.getPeso());
        maletaExistente.setTipoMaleta(maleta.getTipoMaleta());

        // Guardar la maleta actualizada
        maletaRepository.save(maletaExistente);

        // Devolver la lista actualizada de maletas del viaje
        return getMaletasByViajeId(maletaExistente.getViaje().getId());
    }

    // Eliminar una maleta
    public List<ListarMaletasDTO> eliminarMaleta(Long id) {
        // Buscar la maleta por ID
        Maleta maleta = maletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maleta no encontrada"));

        // Eliminar la maleta
        maletaRepository.delete(maleta);

        // Devolver la lista actualizada de maletas del viaje
        return getMaletasByViajeId(maleta.getViaje().getId());
    }
    // ---------------------------------------


}
