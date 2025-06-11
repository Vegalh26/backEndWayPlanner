package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Billetes.CategoriasBilleteDTO;
import org.example.backendwayplanner.DTOs.Billetes.CrearBilleteDTO;
import org.example.backendwayplanner.DTOs.Billetes.ListarBilletesDTO;
import org.example.backendwayplanner.Entidades.Billete;
import org.example.backendwayplanner.Entidades.Viaje;
import org.example.backendwayplanner.Enums.CategoriaBillete;
import org.example.backendwayplanner.Repositorios.BilleteRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BilleteService {

    @Autowired
    private BilleteRepository billeteRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private javax.sql.DataSource dataSource;

    // Obtener billetes por viaje y categoría
    public List<ListarBilletesDTO> obtenerBilletesPorCategoriaYViaje(CategoriaBillete categoria, Long viajeId) {
        List<Billete> billetes = billeteRepository.findByCategoriaAndViajeId(categoria, viajeId);
        return billetes.stream()
                .map(b -> new ListarBilletesDTO(b.getId(), b.getNombre(), null, null)) // Solo se usan id y nombre según tu DTO
                .collect(Collectors.toList());
    }

    // Obtener todas las categorías de billetes que hay y su cantidad
    public List<CategoriasBilleteDTO> obtenerCategoriasConCantidad(Long viajeId) {
        List<Object[]> resultados = billeteRepository.contarBilletesPorCategoria(viajeId);

        return resultados.stream()
                .map(obj -> {
                    String nombre = ((CategoriaBillete) obj[0]).name();
                    Long cantidad = (Long) obj[1];
                    return new CategoriasBilleteDTO(nombre, cantidad);
                })
                .collect(Collectors.toList());
    }

    // CRUD Billete
    // -------------------------
    // Crear un billete
    public List<ListarBilletesDTO> crearBillete(CrearBilleteDTO crearBilleteDTO, Long OID) {
        // Buscar el Viaje por ID
        Viaje viaje = viajeRepository.findById(crearBilleteDTO.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        // Crear y guardar el nuevo billete
        Billete billete = new Billete();
        billete.setNombre(crearBilleteDTO.getNombre());
        billete.setCategoria(CategoriaBillete.valueOf(crearBilleteDTO.getCategoria()));

        billete.setPdf(OID); // Asignar el OID del PDF

        billete.setViaje(viaje);

        billeteRepository.save(billete);

        // Devolver la lista actualizada de billetes del viaje
        return obtenerBilletesPorCategoriaYViaje(billete.getCategoria(), viaje.getId());
    }

    public Long guardarPdfComoLargeObject(MultipartFile file) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            LargeObjectManager lobj = conn.unwrap(PGConnection.class).getLargeObjectAPI();

            long oid = lobj.createLO(LargeObjectManager.WRITE);
            LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);
            obj.write(file.getBytes());
            obj.close();

            conn.commit();
            return oid;
        }
    }

    public byte[] leerPdfDesdeOid(Long oid) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Desactiva auto-commit
            org.postgresql.PGConnection pgConn = connection.unwrap(org.postgresql.PGConnection.class);
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
            byte[] data = new byte[obj.size()];
            obj.read(data, 0, obj.size());
            obj.close();

            connection.commit(); // Opcional, para finalizar la transacciÃ³n
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo imagen por OID", e);
        }
    }


    /* Actualizar un billete
    public List<ListarBilletesDTO> actualizarBillete(Long billeteId, CrearBilleteDTO crearBilleteDTO) {
        // Buscar el billete por ID
        Billete billete = billeteRepository.findById(billeteId)
                .orElseThrow(() -> new RuntimeException("Billete no encontrado"));

        // Actualizar los atributos del billete
        billete.setNombre(crearBilleteDTO.getNombre());
        billete.setCategoria(CategoriaBillete.valueOf(crearBilleteDTO.getCategoria()));
        billete.setPdf(crearBilleteDTO.getPdf());

        // Guardar el billete actualizado
        billeteRepository.save(billete);

        // Devolver la lista actualizada de billetes del viaje
        return obtenerBilletesPorCategoriaYViaje(billete.getCategoria(), billete.getViaje().getId());
    }

     */

    // Eliminar un billete
    public List<ListarBilletesDTO> eliminarBillete(Long idBillete) {
        // Buscar el billete por ID
        Billete billete = billeteRepository.findById(idBillete)
                .orElseThrow(() -> new RuntimeException("Billete no encontrado"));

        // Eliminar el billete
        billeteRepository.delete(billete);

        // Devolver la lista actualizada de billetes del viaje
        return obtenerBilletesPorCategoriaYViaje(billete.getCategoria(), billete.getViaje().getId());
    }

}
