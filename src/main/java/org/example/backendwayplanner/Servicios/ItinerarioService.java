package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Itinerarios.HorarioDTO;
import org.example.backendwayplanner.DTOs.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Horario;
import org.example.backendwayplanner.Entidades.Itinerario;
import org.example.backendwayplanner.Enums.Transporte;
import org.example.backendwayplanner.Repositorios.BilleteRepository;
import org.example.backendwayplanner.Repositorios.DiaRepository;
import org.example.backendwayplanner.Repositorios.HorarioRepository;
import org.example.backendwayplanner.Repositorios.ItinerarioRepository;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.postgresql.largeobject.LargeObjectManager;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BilleteRepository billeteRepository;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DataSource dataSource;

    // Obtener todos los itinerarios en orden por viajeId
    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosPorViajeId(Long viajeId) {

        // Obtiene todos los itinerarios del viaje ordenados por día y hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            // Filtra los itinerarios que aparecen en los itinerarios
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosEnRuta(Long viajeId) {

        // Obtiene todos los itinerarios del viaje ordenados por día y hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            // Filtra los itinerarios que están en ruta
            if (itinerario.isEstaEnRuta()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosRutaYDia(Long viajeId, Long idDia) {

        // Obtiene todos los itinerarios por el Día
        List<Itinerario> itinerarios = itinerarioRepository.buscarItinerariosPorDiaId(idDia);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            // Filtra los itinerarios que están en ruta
            if (itinerario.isEstaEnRuta()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }


    // Obtener todos los itinerarios por viaje y día
    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(Long viajeId, Long idDia) {

        // Obtiene todos los itinerarios por el Día
        List<Itinerario> itinerarios = itinerarioRepository.buscarItinerariosPorDiaId(idDia);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();


        for (Itinerario itinerario : itinerarios) {
            // Filtra los itinerarios que aparecen en los itinerarios
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    // Crear un nuevo itinerario con foto, el cual espera un Object id (oid) que representa el OID de la foto almacenada en la base de datos
    public ItinerarioDTO crearItinerarioConFoto(ItinerarioDTO dto, Long oid) {
        Itinerario i = transformarSinDTO(dto);
        i.setFoto(oid);

        Itinerario itinerarioGuardado = itinerarioRepository.save(i);
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();

        itinerarioDTO.setId(itinerarioGuardado.getId());
        itinerarioDTO.setActividad(itinerarioGuardado.getActividad());
        itinerarioDTO.setLatitud(itinerarioGuardado.getLatitud());
        itinerarioDTO.setLongitud(itinerarioGuardado.getLongitud());
        itinerarioDTO.setEstaEnRuta(itinerarioGuardado.isEstaEnRuta());
        itinerarioDTO.setApareceEnItinerario(itinerarioGuardado.apareceEnItinerario());
        itinerarioDTO.setDuracion(itinerarioGuardado.getDuracion());
        itinerarioDTO.setIddia(itinerarioGuardado.getDia().getId());
        if (itinerarioGuardado.getBillete() != null) {
            itinerarioDTO.setIdbillete(itinerarioGuardado.getBillete().getId());
        } else {
            itinerarioDTO.setIdbillete(null);
        }
        itinerarioDTO.setCategoria(itinerarioGuardado.getCategoria());
        itinerarioDTO.setHora(itinerarioGuardado.getHora());
        itinerarioDTO.setMedioTransporte(itinerarioGuardado.getMedioTransporte());
        if (oid != null) {
            // Leer la imagen desde el OID y convertirla a Base64
            byte[] fotoBytes = leerImagenDesdeOid(oid);
            String base64 = Base64.getEncoder().encodeToString(fotoBytes);
            itinerarioDTO.setFoto(base64);
        } else {
            itinerarioDTO.setFoto(null);
        }
        return itinerarioDTO;
    }

    // Crear un nuevo itinerario sin foto
    public ItinerarioDTO crearItinerario(ItinerarioDTO dto) {
        Itinerario i = transformarSinDTO(dto);
        i.setFoto(null);

        Itinerario itinerarioGuardado = itinerarioRepository.save(i);
        return transformarADTO(itinerarioGuardado);
    }

    // Actualizar un itinerario sin su foto por si hace una sin ella
    public ItinerarioDTO actualizarItinerario(ItinerarioDTO itinerario) {
        Itinerario existente = itinerarioRepository.findById(itinerario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerario no encontrado con ID: " + itinerario.getId()));

        existente.setActividad(itinerario.getActividad());
        existente.setLatitud(itinerario.getLatitud());
        existente.setLongitud(itinerario.getLongitud());
        existente.setEstaEnRuta(itinerario.isEstaEnRuta());
        existente.setApareceEnItinerario(itinerario.isApareceEnItinerario());
        existente.setHora(itinerario.getHora());
        existente.setMedioTransporte(itinerario.getMedioTransporte());
        existente.setDuracion(itinerario.getDuracion());
        existente.setCategoria(itinerario.getCategoria());
        existente.setFoto(existente.getFoto());
        existente.setDia(diaRepository.findById(itinerario.getIddia()).orElse(null));

        // Guardar y retornar
        Itinerario guardado = itinerarioRepository.save(existente);
        return transformarADTO(guardado);
    }

    // Actualizar un itinerario con foto, el cual espera un Object id (oid) que representa el OID de la foto almacenada en la base de datos
    public ItinerarioDTO actualizarItinerarioConFoto(ItinerarioDTO itinerario, Long oid) {
        Itinerario existente = itinerarioRepository.findById(itinerario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerario no encontrado con ID: " + itinerario.getId()));

        existente.setActividad(itinerario.getActividad());
        existente.setLatitud(itinerario.getLatitud());
        existente.setLongitud(itinerario.getLongitud());
        existente.setEstaEnRuta(itinerario.isEstaEnRuta());
        existente.setApareceEnItinerario(itinerario.isApareceEnItinerario());
        existente.setHora(itinerario.getHora());
        existente.setMedioTransporte(itinerario.getMedioTransporte());
        existente.setDuracion(itinerario.getDuracion());
        existente.setCategoria(itinerario.getCategoria());
        existente.setFoto(oid); // ✅ Solo cambia la foto

        // Actualizar relaciones
        if (itinerario.getIdbillete() != null) {
            existente.setBillete(billeteRepository.findById(itinerario.getIdbillete()).orElse(null));
        } else {
            existente.setBillete(null);
        }

        if (itinerario.getIddia() != null) {
            existente.setDia(diaRepository.findById(itinerario.getIddia()).orElse(null));
        }

        Itinerario guardado = itinerarioRepository.save(existente);
        return transformarADTO(guardado);
    }


    // Transformar una lista de itinerarios a DTOs
    @Transactional
    public List<ItinerarioDTO> transformarListaADTO(List<Itinerario> itinerarios) {
        List<ItinerarioDTO> itinerarioDTOS = new ArrayList<>();

        for (Itinerario i : itinerarios) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setId(i.getId());
            dto.setActividad(i.getActividad());
            dto.setLatitud(i.getLatitud());
            dto.setLongitud(i.getLongitud());
            dto.setEstaEnRuta(i.isEstaEnRuta());
            dto.setApareceEnItinerario(i.apareceEnItinerario());
            dto.setDuracion(i.getDuracion());
            dto.setIddia(i.getDia().getId());

            if (i.getBillete() != null) {
                Long id = i.getBillete().getId();
                dto.setIdbillete(id);
            } else {
                Long id = null;
                dto.setIdbillete(id);
            }
            dto.setCategoria(i.getCategoria());
            dto.setHora(i.getHora());
            dto.setMedioTransporte(i.getMedioTransporte());
            dto.setHorarios(transformarHorariosADTO(horarioRepository.findByItinerario_Id(i.getId())));

            if (i.getFoto() != null) {
                byte[] fotoBytes = leerImagenDesdeOid(i.getFoto());
                String base64 = Base64.getEncoder().encodeToString(fotoBytes);
                dto.setFoto(base64);
            }

            itinerarioDTOS.add(dto);
        }

        return itinerarioDTOS;
    }

    // Transformar una lista de horarios a DTOs
    public List<HorarioDTO> transformarHorariosADTO(List<Horario> horarios) {
        List<HorarioDTO> horarioDTOS = new ArrayList<>();

        for (Horario h : horarios) {
            HorarioDTO dto = new HorarioDTO();
            dto.setId(h.getId());
            dto.setIdItinerario(h.getItinerario().getId());
            dto.setDia(h.getDia());
            dto.setHoraInicio(h.getHoraInicio());
            dto.setHoraFin(h.getHoraFin());
            dto.setClosed(h.isClosed());

            horarioDTOS.add(dto);
        }

        return horarioDTOS;
    }

    // Transformar un DTO de itinerario a entidad sin foto
    public Itinerario transformarSinDTO(ItinerarioDTO itinerario) {


        Itinerario itinerarioSinDTO = new Itinerario();
        itinerarioSinDTO.setId(itinerario.getId());
        itinerarioSinDTO.setActividad(itinerario.getActividad());
        itinerarioSinDTO.setLatitud(itinerario.getLatitud());
        itinerarioSinDTO.setLongitud(itinerario.getLongitud());
        itinerarioSinDTO.setEstaEnRuta(itinerario.isEstaEnRuta());
        itinerarioSinDTO.setApareceEnItinerario(itinerario.isApareceEnItinerario());
        itinerarioSinDTO.setCategoria(itinerario.getCategoria());
        itinerarioSinDTO.setHora(itinerario.getHora());
        itinerarioSinDTO.setMedioTransporte(itinerario.getMedioTransporte());
        itinerarioSinDTO.setDuracion(itinerario.getDuracion());
        itinerarioSinDTO.setDia(diaRepository.findById(itinerario.getIddia()).orElse(null));
        if (itinerario.getIdbillete() != null) {
            itinerarioSinDTO.setBillete(billeteRepository.findById(itinerario.getIdbillete()).orElse(null));
        } else {
            itinerarioSinDTO.setBillete(null);
        }
        return itinerarioSinDTO;
    }

    // Transformar un solo itinerario a DTO
    public ItinerarioDTO transformarADTO(Itinerario itinerario) {
        ItinerarioDTO dto = new ItinerarioDTO();
        dto.setId(itinerario.getId());
        dto.setActividad(itinerario.getActividad());
        dto.setLatitud(itinerario.getLatitud());
        dto.setLongitud(itinerario.getLongitud());
        dto.setEstaEnRuta(itinerario.isEstaEnRuta());
        dto.setApareceEnItinerario(itinerario.apareceEnItinerario());
        dto.setDuracion(itinerario.getDuracion());
        dto.setIddia(itinerario.getDia().getId());

        if (itinerario.getBillete() != null) {
            Long id = itinerario.getBillete().getId();
            dto.setIdbillete(id);
        } else {
            Long id = null;
            dto.setIdbillete(id);
        }
        dto.setCategoria(itinerario.getCategoria());
        dto.setHora(itinerario.getHora());
        dto.setMedioTransporte(itinerario.getMedioTransporte());

        if (itinerario.getFoto() != null) {
            byte[] fotoBytes = leerImagenDesdeOid(itinerario.getFoto());
            String base64 = Base64.getEncoder().encodeToString(fotoBytes);
            dto.setFoto(base64);
        }

        return dto;
    }

    // Borrar un itinerario por ID
    public void borrarItinerario(Long id) {
        itinerarioRepository.deleteById(id);
    }

    public void eliminarItinerariosEnRuta(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id).orElse(null);
        if (itinerario != null) {
            itinerario.setEstaEnRuta(false);
            itinerarioRepository.save(itinerario);
        }
    }

    // Eliminar un itinerario de los itinerarios (el booleano)
    public void eliminarItinerarioEnItinerario(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id).orElse(null);
        if (itinerario != null) {
            itinerario.setApareceEnItinerario(false);
            itinerarioRepository.save(itinerario);
        }
    }

    // Guardar una foto como Large Object en PostgreSQL y devolver su OID
    public Long guardarFotoComoLargeObject(MultipartFile file) throws Exception {
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

    // Leer una imagen desde un OID de Large Object en PostgreSQL
    public byte[] leerImagenDesdeOid(Long oid) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            // Usamos PGConnection para acceder a LargeObjectManager, que no viene por defecto en JDBC
            org.postgresql.PGConnection pgConn = connection.unwrap(org.postgresql.PGConnection.class);
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
            byte[] data = new byte[obj.size()];
            obj.read(data, 0, obj.size());
            obj.close();

            connection.commit();
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo imagen por OID", e);
        }
    }

}
