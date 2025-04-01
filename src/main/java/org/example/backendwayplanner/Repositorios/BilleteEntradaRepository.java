package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.BilleteEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteEntradaRepository extends JpaRepository<BilleteEntrada, Long> {

    BilleteEntrada findByNombre(String nombre);
}
