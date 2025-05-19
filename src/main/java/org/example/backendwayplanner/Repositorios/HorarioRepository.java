package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar horarios por actividad o por día
    // Listar por el id de itinerario
    List<Horario> findByItinerario_Id(Long idItinerario);
}
