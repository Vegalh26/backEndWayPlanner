package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Dia;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {

    List<Dia> findByViaje_Id(Long viajeId);

}
