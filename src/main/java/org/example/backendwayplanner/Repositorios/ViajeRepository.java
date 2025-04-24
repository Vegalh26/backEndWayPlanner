package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Entidades.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long>, JpaSpecificationExecutor<Viaje> {
}
