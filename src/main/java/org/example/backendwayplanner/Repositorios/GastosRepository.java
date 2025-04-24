package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long>, JpaSpecificationExecutor<Gastos> {
    List<Gastos> findByViajeId(Long viajeId);
}
