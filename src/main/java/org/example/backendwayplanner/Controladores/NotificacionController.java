package org.example.backendwayplanner.Controladores;

import org.example.backendwayplanner.Dtos.Notificaciones.HoraNotificacionDTO;
import org.example.backendwayplanner.Dtos.Notificaciones.NotificacionListaDTO;
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

    @GetMapping("/listar/{idUsuario}")
    public List<NotificacionListaDTO> listarNotificaciones(@PathVariable Long idUsuario) {
        return notificacionService.listarNotificaciones(idUsuario);
    }

    @PutMapping("/establecer-hora/{id}")
    public ResponseEntity<?> actualizarHoraNotificacion(@PathVariable Long id, @RequestBody HoraNotificacionDTO horaDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            usuario.setHoraNotificacion(horaDTO.getHora());
            usuarioRepository.save(usuario);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/enviar")
    public void enviarNotificacion() {
        notificacionService.enviarNotificacionesPorHora();
    }


    @GetMapping("/hora-notificacion/{id}")
    public ResponseEntity<HoraNotificacionDTO> obtenerHoraNotificacion(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            LocalTime hora = usuarioOptional.get().getHoraNotificacion();
            HoraNotificacionDTO dto = new HoraNotificacionDTO();
            dto.setHora(hora);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
