package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Billetes.CategoriasBilleteDTO;
import org.example.backendwayplanner.DTOs.Billetes.CrearBilleteDTO;
import org.example.backendwayplanner.DTOs.Billetes.ListarBilletesDTO;
import org.example.backendwayplanner.DTOs.Billetes.VerBilleteDTO;
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
import java.util.Base64;
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
                .map(b -> {
                    byte[] pdfBytes = leerPdfDesdeOid(b.getPdf());
                    String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);
                    return new ListarBilletesDTO(b.getId(), b.getNombre(), b.getCategoria().toString(), base64Pdf, b.getViaje().getId());
                })
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

    // Obtener un billete por ID
    public Billete obtenerBilletePorId(Long id) {
        return billeteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billete no encontrado"));
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

    public void eliminarLargeObject(Long oid) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Desactiva auto-commit
            org.postgresql.PGConnection pgConn = connection.unwrap(org.postgresql.PGConnection.class);
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            lobj.delete(oid); // Elimina el Large Object

            connection.commit(); // Opcional, para finalizar la transacción
        } catch (Exception e) {
            throw new RuntimeException("Error eliminando Large Object", e);
        }
    }


    public List<ListarBilletesDTO> actualizarBillete(VerBilleteDTO billeteActualizado, MultipartFile nuevoPdf) {
        // Buscar el billete original desde la base de datos
        Billete billete = billeteRepository.findById(billeteActualizado.getId())
                .orElseThrow(() -> new RuntimeException("Billete no encontrado"));

        // Actualizar campos permitidos
        billete.setNombre(billeteActualizado.getNombre());
        billete.setCategoria(CategoriaBillete.valueOf(billeteActualizado.getCategoria()));

        if (nuevoPdf != null && !nuevoPdf.isEmpty()) {
            try {
                eliminarLargeObject(billete.getPdf()); // opcional, para limpiar el anterior
                Long nuevoOid = guardarPdfComoLargeObject(nuevoPdf);
                billete.setPdf(nuevoOid);
            } catch (Exception e) {
                throw new RuntimeException("Error actualizando PDF", e);
            }
        }

        billeteRepository.save(billete);

        return obtenerBilletesPorCategoriaYViaje(billete.getCategoria(), billete.getViaje().getId());
    }


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
