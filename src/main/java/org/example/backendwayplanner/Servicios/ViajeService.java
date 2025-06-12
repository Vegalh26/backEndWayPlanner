package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTO.ViajeDTO;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    // Crea y guarda un nuevo viaje
    public Viaje crearViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    // Elimina un viaje existente
    public void eliminarViaje(Viaje viaje) {
        viajeRepository.delete(viaje);
    }

    // Lista los viajes de un usuario por su ID
    public List<ViajeDTO> listarViajesporUsuarioId(Long usuarioId) {
        return viajeRepository.ViajesporUsuarioId(usuarioId)
                .stream()
                .map(this::convertirAViajeDTO)
                .collect(Collectors.toList());
    }

    // Obtiene un viaje por su ID
    public Optional<ViajeDTO> obtenerViajePorId(Long id) {
        return viajeRepository.findById(id).map(this::convertirAViajeDTO);
    }

    // Actualiza un viaje existente con los datos del DTO
    public Viaje actualizarViaje(ViajeDTO viaje) {
        Optional<Viaje> viajeExistente = viajeRepository.findById(viaje.getId());
        if (viajeExistente.isPresent()) {
            Viaje viajeActualizado = viajeExistente.get();
            viajeActualizado.setNombre(viaje.getNombre());
            viajeActualizado.setDestino(viaje.getDestino());
            viajeActualizado.setFechaInicio(viaje.getFechaInicio());
            viajeActualizado.setFechaFin(viaje.getFechaFin());
            viajeActualizado.setDescripcion(viaje.getDescripcion());
            return viajeRepository.save(viajeActualizado);
        }
        return null;
    }

    // Convierte una entidad Viaje a DTO
    private ViajeDTO convertirAViajeDTO(Viaje viaje) {
        return new ViajeDTO(
                viaje.getId(),
                viaje.getNombre(),
                viaje.getFechaInicio(),
                viaje.getFechaFin(),
                viaje.getDestino(),
                viaje.getDescripcion()
        );
    }
}
