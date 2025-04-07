package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    public Viaje crearViaje(Viaje viaje) {
        viajeRepository.save(viaje);
        return viaje;
    }

    public void eliminarViaje(Viaje viaje) {
        viajeRepository.delete(viaje);
    }

    public List<Viaje> listarViajes() {
        return viajeRepository.findAll();
    }


    public Optional<Viaje> obtenerViajePorId(Long id) {
        return viajeRepository.findById(id);
    }


    public Viaje actualizarViaje(Viaje viaje) {
        Optional<Viaje> viajeExistente = viajeRepository.findById(viaje.getId());
        if (viajeExistente.isPresent()) {
            Viaje viajeActualizado = viajeExistente.get();
            viajeActualizado.setDestino(viaje.getDestino());
            viajeActualizado.setFechaInicio(viaje.getFechaInicio());
            viajeActualizado.setFechaFin(viaje.getFechaFin());
            viajeActualizado.setUsuario(viaje.getUsuario());
            return viajeRepository.save(viajeActualizado);
        }
        return null;
    }

}
