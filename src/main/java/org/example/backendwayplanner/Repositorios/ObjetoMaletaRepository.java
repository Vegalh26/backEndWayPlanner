package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.DTOs.Maletas.ListarObjetosMaletasDTO;
import org.example.backendwayplanner.Entidades.Maleta;
import org.example.backendwayplanner.Entidades.ObjetoMaleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetoMaletaRepository extends JpaRepository<ObjetoMaleta, Long> {
    // Listar todos los objetos de una maleta por su ID
    List<ObjetoMaleta> findByMaletaId(Maleta maleta);


}
