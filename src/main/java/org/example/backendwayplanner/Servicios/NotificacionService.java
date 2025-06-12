package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Notificaciones.NotificacionListaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.example.backendwayplanner.Entidades.Notificacion;
import org.example.backendwayplanner.Entidades.NotificacionDescartada;
import org.example.backendwayplanner.Repositorios.notificacionDescartadaRepository;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Enums.EstadoNotificacion;
import org.example.backendwayplanner.Enums.TipoNotificacion;
import org.example.backendwayplanner.Repositorios.NotificacionRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private notificacionDescartadaRepository notificacionDescartadaRepository;

    // Lista todas las notificaciones de un usuario y las convierte a DTO
    public List<NotificacionListaDTO> listarNotificaciones(Long idUsuario) {
        List<Notificacion> notificaciones = notificacionRepository.findByUsuarioId(idUsuario);

        return notificaciones.stream()
                .map(n -> new NotificacionListaDTO(
                        n.getIdNotificacion(),
                        n.getMensaje(),
                        n.getTipoNotificacion(),
                        n.getEstado(),
                        n.getFechaEnvio()
                ))
                .collect(Collectors.toList());
    }

    // Método que se ejecuta automáticamente cada minuto (cron: cada 0 segundos)
    @Scheduled(cron = "0 * * * * ?")
    public void enviarNotificacionesPorHora() {
        // Hora actual para comparar
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = LocalDate.now();
        LocalTime horaActual = ahora.toLocalTime();

        // Se obtienen todos los viajes
        List<Viaje> viajes = viajeRepository.findAll();

        for (Viaje viaje : viajes) {
            Usuario usuario = viaje.getUsuario();

            // Si el usuario no tiene configurada una hora, se usa 8:00 AM como predeterminada
            LocalTime horaUsuario = usuario.getHoraNotificacion() != null
                    ? usuario.getHoraNotificacion()
                    : LocalTime.of(8, 0);

            // Se calcula cuántos días faltan para el viaje
            long diasHastaViaje = java.time.temporal.ChronoUnit.DAYS.between(hoy, viaje.getFechaInicio());

            // Solo se notifican viajes que ocurren hoy, mañana o hasta en 3 días
            if (diasHastaViaje >= 0 && diasHastaViaje <= 3) {
                // Se verifica si estamos dentro de una ventana de ±2 minutos con respecto a la hora configurada

                if (horaActual.truncatedTo(ChronoUnit.MINUTES).equals(horaUsuario)) {

                    // Evitar notificaciones duplicadas o ya descartadas por el usuario
                    boolean yaExiste = notificacionRepository.existsByUsuarioIdAndViajeId(usuario.getId(), viaje.getId());
                    boolean yaDescartada = notificacionDescartadaRepository.existsByUsuarioIdAndViajeId(usuario.getId(), viaje.getId());

                    if (!yaExiste && !yaDescartada) {
                        // Se construye una nueva notificación recordando el viaje
                        Notificacion notificacion = new Notificacion();
                        notificacion.setMensaje("Tu viaje a " + viaje.getDestino() + " es en " + diasHastaViaje + " día(s)");
                        notificacion.setTipoNotificacion(TipoNotificacion.RECORDATORIO);
                        notificacion.setEstado(EstadoNotificacion.PENDIENTE);
                        notificacion.setFechaEnvio(ahora);
                        notificacion.setUsuario(usuario);
                        notificacion.setViaje(viaje);

                        // Se guarda la notificación en la base de datos
                        notificacionRepository.save(notificacion);
                    }
                }
            }
        }
    }

    // Elimina una notificación y guarda el descarte para no volver a generarla
    public void eliminarNotificacion(Long idNotificacion) {
        Optional<Notificacion> notificacionOptional = notificacionRepository.findById(idNotificacion);

        if (notificacionOptional.isPresent()) {
            Notificacion notificacion = notificacionOptional.get();

            // Se registra que el usuario descartó esta notificación para evitar reenvíos automáticos
            NotificacionDescartada descartada = new NotificacionDescartada();
            descartada.setUsuarioId(notificacion.getUsuario().getId());
            descartada.setViajeId(notificacion.getViaje().getId());
            descartada.setFecha(LocalDate.now());

            notificacionDescartadaRepository.save(descartada);

            // Se elimina la notificación activa
            notificacionRepository.deleteById(idNotificacion);
        } else {
            // Si no se encuentra, se lanza una excepción
            throw new EntityNotFoundException("Notificación no encontrada con ID: " + idNotificacion);
        }
    }
}
