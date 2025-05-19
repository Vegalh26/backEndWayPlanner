package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndViajeId(Long usuarioId, Long viajeId);

}
