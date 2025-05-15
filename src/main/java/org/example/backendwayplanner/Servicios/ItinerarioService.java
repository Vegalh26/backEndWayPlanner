package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Repositorios.BilleteRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BilleteRepository billeteEntradaRepository;

    // Obtener todos los itinerarios en orden por viajeId
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(Long viajeId) {

        List<Itinerario> itinerariosOrdenados = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);

        return transformarListaADTO(itinerariosOrdenados);

    }

    // Obtener todos los itinerarios por viaje y día
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(Long viajeId, LocalDate fecha) {

        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdAndDia_Fecha(viajeId, fecha);

        return transformarListaADTO(itinerarios);

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
            itinerarioDTO.setLatitud(i.getLatitud());
            itinerarioDTO.setLongitud(i.getLongitud());
            itinerarioDTO.setEstaEnRuta(i.isEstaEnRuta());
            itinerarioDTO.setMedioTransporte(i.getMedioTransporte());
            itinerarioDTO.setDuracion(String.valueOf(i.getDuracion()));
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
        itinerarioSinDTO.setLatitud(itinerario.getLatitud());
        itinerarioSinDTO.setLongitud(itinerario.getLongitud());
        itinerarioSinDTO.setEstaEnRuta(itinerario.isEstaEnRuta());
        itinerarioSinDTO.setMedioTransporte(itinerario.getMedioTransporte());
        itinerarioSinDTO.setDuracion(LocalDateTime.parse(itinerario.getDuracion()));
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
