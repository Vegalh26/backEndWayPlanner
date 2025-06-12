package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {
    List<Horario> findByItinerario_Id(Long idItinerario);
    // Listar horarios donde el diaSemana de Dia sea igual al diaSemana del horario de un itinerario



}
