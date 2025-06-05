package org.example.backendwayplanner.Repositorios;

import org.example.backendwayplanner.Entidades.Billete;
import org.example.backendwayplanner.Enums.CategoriaBillete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilleteRepository extends JpaRepository<Billete, Long> {

    // Encontrar billete por nombre
    Billete findByNombre(String nombre);

    // Encontrar billetes por categoría y viaje
    List<Billete> findByCategoriaAndViajeId(CategoriaBillete categoria, Long viajeId);

    // Mostrar Categorías y la cantidad de billetes por categoría
    @Query("SELECT b.categoria, COUNT(b) FROM Billete b WHERE b.viaje.id = ?1 GROUP BY b.categoria")
    List<Object[]> contarBilletesPorCategoria(Long viajeId);

}
