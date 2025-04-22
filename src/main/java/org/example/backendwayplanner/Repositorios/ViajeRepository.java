package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    Viaje findByNombre(String nombre);
    Optional<Viaje> findById(Long id);

}
