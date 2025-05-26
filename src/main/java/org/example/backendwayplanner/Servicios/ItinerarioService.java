package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Repositorios.BilleteRepository;
import org.example.backendwayplanner.Repositorios.DiaRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BilleteRepository billeteRepository;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener todos los itinerarios en orden por viajeId
    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(Long viajeId) {

        // que filtre por el booleano de cada itinerario de si aparece en itinerario o no
        // y que los ordene por el dia y la hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosEnRuta(Long viajeId) {

        // que filtre por el booleano de cada itinerario de si aparece en itinerario o no
        // y que los ordene por el dia y la hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.isEstaEnRuta()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }





    // Obtener todos los itinerarios por viaje y día
    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(Long viajeId, LocalDate fecha) {

        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdAndDia_Fecha(viajeId, fecha);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }


    public Itinerario crearItinerario(ItinerarioDTO itinerario) {
        return itinerarioRepository.save(transformarSinDTO(itinerario));
    }

    public Itinerario actualizarItinerario(ItinerarioDTO itinerario) {
        return itinerarioRepository.save(transformarSinDTO(itinerario));
    }



    public List<ItinerarioDTO> transformarListaADTO(List<Itinerario> itinerarios) {
        List<ItinerarioDTO> itinerarioDTOS = new ArrayList<>();

        for (Itinerario i : itinerarios) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setId(i.getId());
            dto.setActividad(i.getActividad());
            dto.setLatitud(i.getLatitud());
            dto.setLongitud(i.getLongitud());
            dto.setEstaEnRuta(i.isEstaEnRuta());
            dto.setApareceEnItinerario(i.apareceEnItinerario());
            dto.setMedioTransporte(i.getMedioTransporte());
            dto.setDuracion(i.getDuracion());
            dto.setIddia(i.getDia().getId());
            dto.setIdbillete(i.getBillete().getId());
            dto.setHorarios(null);
            dto.setCategoria(i.getCategoria());
            dto.setHora(i.getHora());

            // Convertir OID a Base64 (si existe)
            if (i.getFoto() != null) {
                dto.setFoto(Base64.getEncoder().encodeToString(i.getFoto()));
            } else {
                dto.setFoto(null);
            }


            itinerarioDTOS.add(dto);
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
        itinerarioSinDTO.setFoto(Base64.getDecoder().decode(itinerario.getFoto()));
        itinerarioSinDTO.setHorarios(null);
        return itinerarioSinDTO;
    }

    public void borrarItinerario(Long id) {
        itinerarioRepository.deleteById(id);
    }


}
