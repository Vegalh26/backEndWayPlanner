package org.example.backendwayplanner.Servicios;
import jakarta.persistence.EntityNotFoundException;

import org.example.backendwayplanner.DTOs.Login.LoginDTO;
import org.example.backendwayplanner.DTOs.Login.RegistroDTO;
import org.example.backendwayplanner.DTOs.Login.RespuestaDTO;
import org.example.backendwayplanner.DTOs.Login.UsuarioDTO;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Repositorios.UsuarioRepository;
import org.example.backendwayplanner.Security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    // Constructor con inyecciones de dependencias necesarias
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    // Método de Spring Security para cargar un usuario por su email
    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(Email).orElse(null);
    }

    // Genera un código de verificación aleatorio de 6 dígitos
    private String generarCodigoVerificacion() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    // Registra un nuevo usuario con los datos recibidos desde el frontend
    public Usuario registrarUsuario(RegistroDTO dto) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setContrasena(passwordEncoder.encode(dto.getPassword())); // Se cifra la contraseña
        nuevoUsuario.setTelefono(dto.getTelefono());
        nuevoUsuario.setFechaRegistro(dto.getFechaRegistro());
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setFechaNacimiento(dto.getFechaNacimiento());

        // Se genera y guarda el código de verificación
        String codigoVerificacion = generarCodigoVerificacion();
        nuevoUsuario.setVerifiCodi(codigoVerificacion);
        nuevoUsuario.setVerificado(false); // Se marca como no verificado aún

        // Se guarda el usuario en la base de datos y se envía el correo
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        emailService.envEmail(dto.getEmail(), codigoVerificacion);

        // Se guarda una segunda vez por si se requiere actualizar después del envío
        return usuarioRepository.save(nuevoUsuario);
    }

    // Verifica el código ingresado por el usuario
    public boolean verificarCodigo(String email, String codigo) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getVerifiCodi().equals(codigo)) {
            usuario.get().setVerificado(true); // Se marca como verificado
            usuarioRepository.save(usuario.get()); // Se actualiza en la base de datos
            return true;
        }
        return false;
    }

    // Inicia sesión validando credenciales y estado de verificación
    public ResponseEntity<RespuestaDTO> login(LoginDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioOpcional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpcional.get();

        // Si no está verificado, se rechaza el inicio de sesión
        if (!usuario.isVerificado()) {
            RespuestaDTO respuesta = new RespuestaDTO();
            respuesta.setEstado(HttpStatus.FORBIDDEN.value());
            respuesta.setMensaje("Por favor verifica tu cuenta primero");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(respuesta);
        }

        // Si la contraseña no coincide, se lanza excepción
        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        // Si todo es correcto, se genera el JWT
        String token = jwtService.generateToken(usuario);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setEstado(HttpStatus.OK.value());
        respuesta.setMensaje("Inicio de sesión exitoso");
        respuesta.setToken(token);

        return ResponseEntity.ok(respuesta);
    }

    // Actualiza los datos de un usuario existente
    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);

        if (usuarioOpcional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpcional.get();
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(passwordEncoder.encode(dto.getPassword())); // Se vuelve a cifrar
        usuario.setFechaNacimiento(dto.getFechaNacimiento());

        return usuarioRepository.save(usuario);
    }

    // Devuelve los datos de un usuario por su ID
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado para el usuario con ID: " + id));
        return new UsuarioDTO(usuario); // Convierte la entidad en DTO
    }

    // Elimina un usuario por su ID
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuario);
    }

    // Reenvía el código de verificación por email si es necesario
    public boolean reenviarCodigo(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            String codigoVerificacion = usuario.get().getVerifiCodi();

            // Si no hay código, se genera uno nuevo
            if (codigoVerificacion == null || codigoVerificacion.isEmpty()) {
                codigoVerificacion = generarCodigoVerificacion();
                usuario.get().setVerifiCodi(codigoVerificacion);
                usuarioRepository.save(usuario.get());
            }

            // Se reenvía por correo
            emailService.envEmail(email, codigoVerificacion);
            return true;
        }
        return false;
    }
}
