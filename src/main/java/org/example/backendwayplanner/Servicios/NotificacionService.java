package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.NotificacionListaDTO;
import org.example.backendwayplanner.Entidades.Notificacion;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ViajeRepository viajeRepository;

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

    public LocalDateTime establecerFechaEnvio(Usuario usuario) {
        LocalTime horaEnvio = usuario.getHoraNotificacion() != null
                ? usuario.getHoraNotificacion()
                : LocalTime.of(8, 0);

        return LocalDate.now().plusDays(1).atTime(horaEnvio);
    }

    @Scheduled(cron = "0 * * * * ?") // Cada minuto
    public void enviarNotificacionesPorHora() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = LocalDate.now();
        LocalTime horaActual = ahora.toLocalTime();

        List<Viaje> viajes = viajeRepository.findAll();

        for (Viaje viaje : viajes) {
            Usuario usuario = viaje.getUsuario();
            LocalTime horaUsuario = usuario.getHoraNotificacion() != null
                    ? usuario.getHoraNotificacion()
                    : LocalTime.of(8, 0); // valor por defecto

            long diasHastaViaje = java.time.temporal.ChronoUnit.DAYS.between(
                    hoy,
                    viaje.getFechaInicio()
            );
            if (diasHastaViaje >= 0 && diasHastaViaje <= 3) {
                long diferenciaMinutos = Math.abs(java.time.Duration.between(horaActual, horaUsuario).toMinutes());

                if (diferenciaMinutos < 2) {
                    boolean yaExiste = notificacionRepository.existsByUsuarioIdAndViajeIdAndFechaEnvioBetween(
                            usuario.getId(),
                            viaje.getId(),
                            ahora.minusMinutes(2),
                            ahora.plusMinutes(2)
                    );

                    if (!yaExiste) {
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
}
