package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Maleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaletaRepository extends JpaRepository<Maleta, Long> {
    // Encontrar viaje por ID
    List<Maleta> findByViajeId(Long viajeId);
}
