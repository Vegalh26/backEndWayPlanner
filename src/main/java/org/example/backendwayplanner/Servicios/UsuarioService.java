package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.LoginDTO;
import org.example.backendwayplanner.Dtos.RegistroDTO;
import org.example.backendwayplanner.Dtos.RespuestaDTO;

import org.example.backendwayplanner.Dtos.UsuarioDTO;
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

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(Email).orElse(null);
    }

    public Usuario registrarUsuario(RegistroDTO dto){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setContraseña(passwordEncoder.encode(dto.getContraseña()));
        nuevoUsuario.setTelefono(dto.getTelefono());
        nuevoUsuario.setFechaRegistro(dto.getFechaRegistro());
        nuevoUsuario.setNombre(dto.getNombre());

        return usuarioRepository.save(nuevoUsuario);

    }

    public ResponseEntity<RespuestaDTO> login(LoginDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();

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
        usuario.setContraseña(passwordEncoder.encode(dto.getContraseña()));

        return usuarioRepository.save(usuario);
    }


}
