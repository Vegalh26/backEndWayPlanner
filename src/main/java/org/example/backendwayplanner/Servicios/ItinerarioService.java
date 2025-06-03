package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Itinerarios.HorarioDTO;
import org.example.backendwayplanner.DTOs.Itinerarios.ItinerarioDTO;
import org.example.backendwayplanner.Entidades.Horario;
import org.example.backendwayplanner.Entidades.Itinerario;
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

        // que filtre por el booleano de cada itinerario de si aparece en itinerario o no
        // y que los ordene por el dia y la hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosEnRuta(Long viajeId) {

        // que filtre por el booleano de cada itinerario de si aparece en itinerario o no
        // y que los ordene por el dia y la hora
        List<Itinerario> itinerarios = itinerarioRepository.findByDia_Viaje_IdOrderByDia_NumeroDiaAscHoraAsc(viajeId);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.isEstaEnRuta()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }

    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosRutaYDia(Long viajeId, Long idDia) {

        // que filtre por el booleano de cada itinerario de si aparece en itinerario o no
        // y que los ordene por el dia y la hora
        System.out.printf("Obteniendo itinerarios para viajeId: %d y diaId: %d%n", viajeId, idDia);
        List<Itinerario> itinerarios = itinerarioRepository.buscarItinerariosPorDiaId(idDia);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.isEstaEnRuta()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }


    // Obtener todos los itinerarios por viaje y día
    @Transactional
    public List<ItinerarioDTO> obtenerItinerariosPorViajeIdYDia(Long viajeId, Long idDia) {

        System.out.printf("Obteniendo itinerarios para viajeId: %d y diaId: %d%n", viajeId, idDia);
        List<Itinerario> itinerarios = itinerarioRepository.buscarItinerariosPorDiaId(idDia);
        List<Itinerario> itinerariosOrdenados = new ArrayList<>();

        for (Itinerario itinerario : itinerarios) {
            if (itinerario.apareceEnItinerario()) {
                itinerariosOrdenados.add(itinerario);
            }
        }

        return transformarListaADTO(itinerariosOrdenados);

    }


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
        if (oid != null) {
            byte[] fotoBytes = leerImagenDesdeOid(oid);
            String base64 = Base64.getEncoder().encodeToString(fotoBytes);
            itinerarioDTO.setFoto(base64);
        } else {
            itinerarioDTO.setFoto(null);
        }
        return itinerarioDTO;
    }


    public Itinerario actualizarItinerario(ItinerarioDTO itinerario) {
        return itinerarioRepository.save(transformarSinDTO(itinerario));
    }


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
        itinerarioSinDTO.setDuracion(itinerario.getDuracion());
        itinerarioSinDTO.setDia(diaRepository.findById(itinerario.getIddia()).orElse(null));
        if (itinerario.getIdbillete() != null) {
            itinerarioSinDTO.setBillete(billeteRepository.findById(itinerario.getIdbillete()).orElse(null));
        } else {
            itinerarioSinDTO.setBillete(null);
        }
        return itinerarioSinDTO;
    }


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

    public void eliminarItinerarioEnItinerario(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id).orElse(null);
        if (itinerario != null) {
            itinerario.setApareceEnItinerario(false);
            itinerarioRepository.save(itinerario);
        }
    }

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

    public byte[] leerImagenDesdeOid(Long oid) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false); // Desactiva auto-commit
            org.postgresql.PGConnection pgConn = connection.unwrap(org.postgresql.PGConnection.class);
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();

            LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
            byte[] data = new byte[obj.size()];
            obj.read(data, 0, obj.size());
            obj.close();

            connection.commit(); // Opcional, para finalizar la transacción
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo imagen por OID", e);
        }
    }

}
