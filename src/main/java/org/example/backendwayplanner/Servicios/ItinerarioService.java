package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Repositorios.BilleteRepository;
import org.example.backendwayplanner.Repositorios.DiaRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BilleteRepository billeteRepository;

    @Autowired
    private DiaRepository diaRepository;

    // Obtener todos los itinerarios en orden por viajeId
    @Transactional
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
            itinerarioDTO.setApareceEnItinerario(i.apareceEnItinerario());
            itinerarioDTO.setMedioTransporte(i.getMedioTransporte());
            itinerarioDTO.setDuracion(i.getDuracion());
            itinerarioDTO.setIddia(i.getDia().getId());
            itinerarioDTO.setIdbillete(i.getBillete().getId());
            itinerarioDTO.setFoto(i.getFoto());
            itinerarioDTO.setHorarios(null);
            itinerarioDTO.setCategoria(i.getCategoria());
            itinerarioDTO.setHora(i.getHora());

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
        itinerarioSinDTO.setApareceEnItinerario(itinerario.isApareceEnItinerario());
        itinerarioSinDTO.setCategoria(itinerario.getCategoria());
        itinerarioSinDTO.setHora(itinerario.getHora());
        itinerarioSinDTO.setMedioTransporte(itinerario.getMedioTransporte());
        itinerarioSinDTO.setDuracion(itinerario.getDuracion());
        itinerarioSinDTO.setDia(diaRepository.findById(itinerario.getIddia()).orElse(null));
        itinerarioSinDTO.setBillete(billeteRepository.findById(itinerario.getIdbillete()).orElse(null));
        itinerarioSinDTO.setFoto(itinerario.getFoto());
        itinerarioSinDTO.setHorarios(null);
        return itinerarioSinDTO;
    }

    public void borrarItinerario(Long id) {
        itinerarioRepository.deleteById(id);
    }

}
