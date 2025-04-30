package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long>, JpaSpecificationExecutor<Gastos> {
    List<Gastos> findByViajeId(Long viajeId);

    @Query("SELECT DISTINCT g.fecha FROM Gastos g WHERE g.viaje.id = :viajeId ORDER BY g.fecha ASC")
    List<LocalDate> findfechasByViajeId(@Param("viajeId") Long viajeId);
}
