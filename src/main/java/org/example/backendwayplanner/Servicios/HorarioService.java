package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Itinerarios.HorarioDTO;
import org.example.backendwayplanner.Entidades.Horario;
import org.example.backendwayplanner.Repositorios.HorarioRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    public List<Horario> crearHorarios (List<HorarioDTO> horarios) {
        return horarioRepository.saveAll(pasarHorariosDTOsinDTO(horarios));
    }

    public List<Horario> pasarHorariosDTOsinDTO (List<HorarioDTO> horarios) {

        List<Horario> listaHorarios = new ArrayList<>();
        for(HorarioDTO horarioDTO : horarios) {
            Horario horario = new Horario();;
            horario.setDia(horarioDTO.getDia());
            horario.setDia(horarioDTO.getDia());
            horario.setHoraInicio(horarioDTO.getHoraInicio());
            horario.setHoraFin(horarioDTO.getHoraFin());
            horario.setClosed(horarioDTO.isClosed());
            horario.setItinerario(itinerarioRepository.findById(horarioDTO.getIdItinerario()).orElse(null));
            listaHorarios.add(horario);
        }

        return listaHorarios;
    }

    //Listar horarios de un itinerario
    public List<HorarioDTO> listarHorarios(Long idItinerario) {
        List<Horario> horarios = horarioRepository.findByItinerario_Id(idItinerario);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for (Horario horario : horarios) {
            HorarioDTO horarioDTO = new HorarioDTO(horario.getId(), horario.getItinerario().getId(), horario.getDia(), horario.getHoraInicio(), horario.getHoraFin(), horario.isClosed());
            horariosDTO.add(horarioDTO);
        }

        return horariosDTO;
    }

}
