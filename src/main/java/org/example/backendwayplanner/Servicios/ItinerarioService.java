package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.ItinerarioDTO;
import org.example.backendwayplanner.Dtos.UbicacionItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Repositorios.BilleteEntradaRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BilleteEntradaRepository billeteEntradaRepository;

    // Obtener todos los itinerarios en orden por viajeId
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(Long viajeId) {

        List<Itinerario> itinerariosOrdenados = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);

        return transformarListaADTO(itinerariosOrdenados);

    }

    public UbicacionItinerarioDTO obtenerUbicacionItinerario(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id).get();
        UbicacionItinerarioDTO ubicacionItinerarioDTO = new UbicacionItinerarioDTO();
        ubicacionItinerarioDTO.setUbicacion(itinerario.getUbicacion());
        ubicacionItinerarioDTO.setIdDia(itinerario.getDia().getId());
        return ubicacionItinerarioDTO;
    }

    public Itinerario crearItinerario(ItinerarioDTO itinerario) {
        return itinerarioRepository.save(transformarSinDTO(itinerario));
    }

    public Itinerario actualizarItinerario(ItinerarioDTO itinerario) {
        return itinerarioRepository.save(transformarSinDTO(itinerario));
    }



    public List<ItinerarioDTO> transformarListaADTO(List<Itinerario> itinerarios) {

        List<ItinerarioDTO> itinerarioDTOS = new ArrayList<>();

        for(Itinerario i: itinerarios) {
            ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
            itinerarioDTO.setId(i.getId());
            itinerarioDTO.setActividad(i.getActividad());
            itinerarioDTO.setUbicacion(i.getUbicacion());
            itinerarioDTO.setHora(String.valueOf(i.getHora()));
            itinerarioDTO.setDia(i.getDia());
            itinerarioDTO.setNombreBillete(i.getBillete().getNombre());
            itinerarioDTOS.add(itinerarioDTO);
        }

        return itinerarioDTOS;
    }

    public Itinerario transformarSinDTO(ItinerarioDTO itinerario) {
        Itinerario itinerarioSinDTO = new Itinerario();
        itinerarioSinDTO.setId(itinerario.getId());
        itinerarioSinDTO.setActividad(itinerario.getActividad());
        itinerarioSinDTO.setUbicacion(itinerario.getUbicacion());
        itinerarioSinDTO.setHora(LocalTime.parse(itinerario.getHora()));
        itinerarioSinDTO.setDia(itinerario.getDia());
        itinerarioSinDTO.setBillete(billeteEntradaRepository.findByNombre(itinerario.getNombreBillete()));
        itinerarioSinDTO.setFoto(itinerario.getFoto());
        return itinerarioSinDTO;
    }

    public void borrarItinerario(Long id) {
        itinerarioRepository.deleteById(id);
    }

}
