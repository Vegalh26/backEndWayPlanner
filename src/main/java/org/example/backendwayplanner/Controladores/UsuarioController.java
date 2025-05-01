package org.example.backendwayplanner.Controladores;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.DTO.UsuarioDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Servicios.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @GetMapping("usuarioPorId/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
