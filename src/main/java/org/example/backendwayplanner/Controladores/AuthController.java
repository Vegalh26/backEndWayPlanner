package org.example.backendwayplanner.Controladores;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.DTOs.Login.LoginDTO;
import org.example.backendwayplanner.DTOs.Login.RegistroDTO;
import org.example.backendwayplanner.DTOs.Login.RespuestaDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private UsuarioService service;

    // Constructor que inyecta el servicio de usuarios
    public AuthController(UsuarioService service) {
        this.service = service;
    }

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registro/perfil")
    public Usuario registro(@RequestBody RegistroDTO registroDTO){
        // Llama al servicio para registrar el usuario con los datos enviados desde el frontend
        return service.registrarUsuario(registroDTO);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<RespuestaDTO> registro(@RequestBody LoginDTO dto){
        // Llama al servicio para validar el login y devolver respuesta con token
        return service.login(dto);
    }

    // Endpoint para verificar el código que el usuario recibió por correo
    @PostMapping("/verificar")
    public ResponseEntity<RespuestaDTO> verificarCodigo(@RequestParam String email, @RequestParam String codigo) {
        boolean verificado = service.verificarCodigo(email, codigo);
        RespuestaDTO respuesta = new RespuestaDTO();

        if (verificado) {
            // Si el código es correcto, se devuelve 200 OK
            respuesta.setEstado(HttpStatus.OK.value());
            respuesta.setMensaje("Código verificado correctamente");
            return ResponseEntity.ok(respuesta);
        }

        // Si el código es incorrecto, se devuelve 400 Bad Request
        respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
        respuesta.setMensaje("Código de verificación incorrecto");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // Endpoint para reenviar el código de verificación por correo
    @PostMapping("/reenviar-codigo")
    public ResponseEntity<String> reenviarCodigo(@RequestParam String email) {
        boolean reenviado = service.reenviarCodigo(email);
        if (reenviado) {
            // Si se reenvía correctamente, se devuelve mensaje 200 OK
            return ResponseEntity.ok("El código de verificación ha sido reenviado a tu correo.");
        }
        // Si no se encuentra el usuario, se devuelve 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con el correo proporcionado.");
    }
}
