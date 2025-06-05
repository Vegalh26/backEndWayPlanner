package org.example.backendwayplanner.Servicios;
import jakarta.persistence.EntityNotFoundException;

import org.example.backendwayplanner.Dtos.Login.LoginDTO;
import org.example.backendwayplanner.Dtos.Login.RegistroDTO;
import org.example.backendwayplanner.Dtos.Login.RespuestaDTO;
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

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(Email).orElse(null);
    }

    private String generarCodigoVerificacion() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public Usuario registrarUsuario(RegistroDTO dto){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setContrasena(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setTelefono(dto.getTelefono());
        nuevoUsuario.setFechaRegistro(dto.getFechaRegistro());
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setFechaNacimiento(dto.getFechaNacimiento());

        String codigoVerificacion = generarCodigoVerificacion();
        nuevoUsuario.setVerifiCodi(codigoVerificacion);
        nuevoUsuario.setVerificado(false);

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        emailService.envEmail(dto.getEmail(), codigoVerificacion);

        return usuarioRepository.save(nuevoUsuario);

    }

    public boolean verificarCodigo(String email, String codigo) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getVerifiCodi().equals(codigo)) {
            usuario.get().setVerificado(true);
            usuarioRepository.save(usuario.get());
            return true;
        }
        return false;
    }

    public ResponseEntity<RespuestaDTO> login(LoginDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();

            if (!usuario.isVerificado()) {
                RespuestaDTO respuesta = new RespuestaDTO();
                respuesta.setEstado(HttpStatus.FORBIDDEN.value());
                respuesta.setMensaje("Por favor verifica tu cuenta primero");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(respuesta);
            }

            if (passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
                String token = jwtService.generateToken(usuario);

                RespuestaDTO respuesta = new RespuestaDTO();
                respuesta.setEstado(HttpStatus.OK.value());
                respuesta.setMensaje("Inicio de sesión exitoso");
                respuesta.setToken(token);

                return ResponseEntity.ok(respuesta);
            } else {
                throw new BadCredentialsException("Contraseña incorrecta");
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);


        if (usuarioOpcional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpcional.get();
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(passwordEncoder.encode(dto.getPassword()));

        return usuarioRepository.save(usuario);
    }


    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado para el usuario con ID: " + id));

    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuario);
    }

    public boolean reenviarCodigo(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            String codigoVerificacion = usuario.get().getVerifiCodi();
            if (codigoVerificacion == null || codigoVerificacion.isEmpty()) {
                codigoVerificacion = generarCodigoVerificacion();
                usuario.get().setVerifiCodi(codigoVerificacion);
                usuarioRepository.save(usuario.get());
            }
            emailService.envEmail(email, codigoVerificacion);
            return true;
        }
        return false;
    }


}
