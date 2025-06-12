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

    // Crea horarios a partir de una lista de HorarioDTO
    public List<Horario> crearHorarios (List<HorarioDTO> horarios) {
        return horarioRepository.saveAll(pasarHorariosDTOsinDTO(horarios));
    }

    // Crea un horario a partir de un HorarioDTO
    public Horario crearHorario (HorarioDTO horarioDTO) {
        Horario horario = new Horario();
        horario.setDia(horarioDTO.getDia());
        horario.setHoraInicio(horarioDTO.getHoraInicio());
        horario.setHoraFin(horarioDTO.getHoraFin());
        horario.setClosed(horarioDTO.isClosed());
        horario.setItinerario(itinerarioRepository.findById(horarioDTO.getIdItinerario()).orElse(null));
        return horarioRepository.save(horario);
    }

    // Convierte una lista de HorarioDTO a una lista de Horario
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

    // Actualizar horarios a partir de una lista de HorarioDTO
    public void actualizarHorario(List<HorarioDTO> horarios) {

        List<Horario> listaHorarios = new ArrayList<>();

        for(HorarioDTO horarioDTO : horarios) {
            if (horarioDTO.getId() == null || horarioDTO.getId() == 0) {
                crearHorario(horarioDTO);
                continue;
            }

            Horario horario = new Horario();
            horario.setId(horarioDTO.getId());
            horario.setDia(horarioDTO.getDia());
            horario.setHoraInicio(horarioDTO.getHoraInicio());
            horario.setHoraFin(horarioDTO.getHoraFin());
            horario.setClosed(horarioDTO.isClosed());
            horario.setItinerario(itinerarioRepository.findById(horarioDTO.getIdItinerario()).orElse(null));
            listaHorarios.add(horario);
        }

        horarioRepository.saveAll(listaHorarios);
    }

    // Eliminar un horario por su ID
    public void eliminarHorario(Long idHorario) {
        horarioRepository.deleteById(idHorario);
    }

}
