package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {
    // Listar horarios por el id de un itinerario
    List<Horario> findByItinerario_Id(Long idItinerario);

}
