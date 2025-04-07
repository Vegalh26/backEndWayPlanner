package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT v FROM Viaje v WHERE v.fechaInicio BETWEEN :inicio AND :fin")
    List<Viaje> findByFechaInicioBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
