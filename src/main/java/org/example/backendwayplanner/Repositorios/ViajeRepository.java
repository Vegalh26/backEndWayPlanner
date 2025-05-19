package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT v FROM Viaje v WHERE v.usuario.id = :id")
    List<Viaje> ViajesporUsuarioId(@Param("id") Long id);

    Viaje findByNombre(String nombre);
    Optional<Viaje> findById(Long id);

    List<Viaje> findByUsuarioId(Long id);

}
