package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.NotificacionDescartada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface notificacionDescartadaRepository extends JpaRepository<NotificacionDescartada, Long> {
    boolean existsByUsuarioIdAndViajeId(Long usuarioId, Long viajeId);

}
