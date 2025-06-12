package Excepciones;

import org.example.backendwayplanner.DTOs.Login.RespuestaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RespuestaDTO> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setEstado(HttpStatus.NOT_FOUND.value());
        respuesta.setMensaje("No existe una cuenta con este correo electrónico");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RespuestaDTO> handleBadCredentialsException(BadCredentialsException ex) {
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setEstado(HttpStatus.UNAUTHORIZED.value());
        respuesta.setMensaje("Contraseña incorrecta");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }
}