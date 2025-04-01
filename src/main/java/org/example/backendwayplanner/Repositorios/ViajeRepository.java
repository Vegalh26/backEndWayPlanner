package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

}
