package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.DTOs.Notificaciones.HoraNotificacionDTO;
import org.example.backendwayplanner.DTOs.Notificaciones.NotificacionListaDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Repositorios.UsuarioRepository;
import org.example.backendwayplanner.Servicios.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Devuelve la lista de notificaciones de un usuario
    @GetMapping("/listar/{idUsuario}")
    public List<NotificacionListaDTO> listarNotificaciones(@PathVariable Long idUsuario) {
        return notificacionService.listarNotificaciones(idUsuario);
    }

    // Actualiza la hora preferida para recibir notificaciones de un usuario
    @PutMapping("/establecer-hora/{id}")
    public ResponseEntity<?> actualizarHoraNotificacion(@PathVariable Long id, @RequestBody HoraNotificacionDTO horaDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Se guarda la nueva hora de notificación para el usuario
            usuario.setHoraNotificacion(horaDTO.getHora());
            usuarioRepository.save(usuario);

            return ResponseEntity.ok().build();
        } else {
            // Usuario no encontrado
            return ResponseEntity.notFound().build();
        }
    }

    // Ejecuta manualmente el envío de notificaciones (misma lógica que el scheduled)
    @PostMapping("/enviar")
    public void enviarNotificacion() {
        notificacionService.enviarNotificacionesPorHora();
    }

    // Devuelve la hora configurada de notificación para un usuario
    @GetMapping("/hora-notificacion/{id}")
    public ResponseEntity<HoraNotificacionDTO> obtenerHoraNotificacion(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            LocalTime hora = usuarioOptional.get().getHoraNotificacion();
            HoraNotificacionDTO dto = new HoraNotificacionDTO();
            dto.setHora(hora);
            return ResponseEntity.ok(dto);
        } else {
            // Usuario no encontrado
            return ResponseEntity.notFound().build();
        }
    }

    // Elimina una notificación específica por su ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminarNotificacion(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
    }
}
