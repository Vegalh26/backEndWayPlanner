package org.example.backendwayplanner.Controladores;

import lombok.AllArgsConstructor;
import org.example.backendwayplanner.Dtos.Login.LoginDTO;
import org.example.backendwayplanner.Dtos.Login.RegistroDTO;
import org.example.backendwayplanner.Dtos.Login.RespuestaDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private final UsuarioService service;


    @PostMapping("/registro/perfil")
    public Usuario registro(@RequestBody RegistroDTO registroDTO){
        return service.registrarUsuario(registroDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaDTO> registro(@RequestBody LoginDTO dto){
        return service.login(dto);
    }

    @PostMapping("/verificar")
    public ResponseEntity<RespuestaDTO> verificarCodigo(@RequestParam String email, @RequestParam String codigo) {
        boolean verificado = service.verificarCodigo(email, codigo);
        RespuestaDTO respuesta = new RespuestaDTO();

        if (verificado) {
            respuesta.setEstado(HttpStatus.OK.value());
            respuesta.setMensaje("Código verificado correctamente");
            return ResponseEntity.ok(respuesta);
        }

        respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
        respuesta.setMensaje("Código de verificación incorrecto");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }


}