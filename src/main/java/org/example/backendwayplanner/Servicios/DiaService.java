package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.DTOs.Itinerarios.DiaDTO;
import org.example.backendwayplanner.Entidades.Dia;
import org.example.backendwayplanner.Repositorios.DiaRepository;
import org.example.backendwayplanner.Repositorios.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaService {

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    // Listar los días por un viaje
    public List<Dia> obtenerDiasPorViajeId(Long viajeId) {
        return diaRepository.findByViaje_Id(viajeId);
    }

    // Crear un dia por un DTO
    public Dia crearDia(DiaDTO dia) {
        return diaRepository.save(transformarSinDTO(dia));
    }

    // Eliminar un día por ID
    public void eliminarDia(Long id) {
        diaRepository.deleteById(id);
    }

    // Métdo para transformar un objeto Dia a su forma en DTO
    public DiaDTO transformarADTO(Dia dia) {
        DiaDTO diaDTO = new DiaDTO();
        diaDTO.setId(dia.getId());
        diaDTO.setFecha(dia.getFecha());
        diaDTO.setNumeroDia(dia.getNumeroDia());
        diaDTO.setDiaSemana(dia.getDiaSemana());
        diaDTO.setidViaje((dia.getViaje().getId()));
        return diaDTO;
    }

    // Métdo para transformar un objeto DiaDTO a su forma en Dia
    public Dia transformarSinDTO(DiaDTO diaDTO) {
        Dia dia = new Dia();
        dia.setId(diaDTO.getId());
        dia.setFecha(diaDTO.getFecha());
        dia.setNumeroDia(diaDTO.getNumeroDia());
        dia.setDiaSemana(diaDTO.getDiaSemana());
        dia.setViaje(viajeRepository.findById(diaDTO.getIdViaje())
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado con ID: " + diaDTO.getIdViaje())));
        return dia;
    }


}
