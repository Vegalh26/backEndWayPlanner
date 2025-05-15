package org.example.backendwayplanner.Servicios;

import jakarta.persistence.EntityNotFoundException;
import org.example.backendwayplanner.DTOs.NotificacionListaDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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


    @Scheduled(cron = "0 * * * * ?")
    public void enviarNotificacionesPorHora() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = LocalDate.now();
        LocalTime horaActual = ahora.toLocalTime();

        List<Viaje> viajes = viajeRepository.findAll();

        for (Viaje viaje : viajes) {
            Usuario usuario = viaje.getUsuario();
            LocalTime horaUsuario = usuario.getHoraNotificacion() != null
                    ? usuario.getHoraNotificacion()
                    : LocalTime.of(8, 0);

            long diasHastaViaje = java.time.temporal.ChronoUnit.DAYS.between(
                    hoy,
                    viaje.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            );
            if (diasHastaViaje >= 0 && diasHastaViaje <= 3) {
                long diferenciaMinutos = Math.abs(java.time.Duration.between(horaActual, horaUsuario).toMinutes());

                if (diferenciaMinutos < 2) {
                    boolean yaExiste = notificacionRepository.existsByUsuarioIdAndViajeId(usuario.getId(), viaje.getId());
                    boolean yaDescartada = notificacionDescartadaRepository.existsByUsuarioIdAndViajeId(usuario.getId(), viaje.getId());

                    if (!yaExiste && !yaDescartada) {
                        Notificacion notificacion = new Notificacion();
                        notificacion.setMensaje("Tu viaje a " + viaje.getDestino() + " es en " + diasHastaViaje + " día(s)");
                        notificacion.setTipoNotificacion(TipoNotificacion.RECORDATORIO);
                        notificacion.setEstado(EstadoNotificacion.PENDIENTE);
                        notificacion.setFechaEnvio(ahora);
                        notificacion.setUsuario(usuario);
                        notificacion.setViaje(viaje);

                        notificacionRepository.save(notificacion);
                    }
                }

            }
        }
    }

    public void eliminarNotificacion(Long idNotificacion) {
        Optional<Notificacion> notificacionOptional = notificacionRepository.findById(idNotificacion);

        if (notificacionOptional.isPresent()) {
            Notificacion notificacion = notificacionOptional.get();

            // Guardar como descartada
            NotificacionDescartada descartada = new NotificacionDescartada();
            descartada.setUsuarioId(notificacion.getUsuario().getId());
            descartada.setViajeId(notificacion.getViaje().getId());
            descartada.setFecha(LocalDate.now());

            notificacionDescartadaRepository.save(descartada);

            notificacionRepository.deleteById(idNotificacion);
        } else {
            throw new EntityNotFoundException("Notificación no encontrada con ID: " + idNotificacion);
        }
    }
}
