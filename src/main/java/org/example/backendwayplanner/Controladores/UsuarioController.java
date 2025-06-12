// Paquete del controlador
package org.example.backendwayplanner.Controladores;

// Importaciones necesarias
import org.example.backendwayplanner.DTOs.Login.UsuarioDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Servicios.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService; // Servicio que maneja la lógica de negocio de usuarios

    // Constructor para inyectar el servicio
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para actualizar un usuario específico según su ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioActualizado); // Devuelve el usuario actualizado con código 200 OK
    }

    // Endpoint para obtener un usuario por su ID
    @GetMapping("usuarioPorId/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Devuelve el usuario con código 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }
    }

    // Endpoint para eliminar un usuario por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id); // Llama al servicio para eliminar el usuario
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content si se elimina correctamente
    }
}
