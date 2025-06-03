package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    // Esta consulta dará la lista de itinerarios en orden, es decir, primero por número de día y luego por hora.
    List<Itinerario> findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(Long viajeId);

    @Query("SELECT i FROM Itinerario i WHERE i.dia.id = :idDia")
    List<Itinerario> buscarItinerariosPorDiaId(@Param("idDia") Long idDia);




}
