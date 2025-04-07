package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
