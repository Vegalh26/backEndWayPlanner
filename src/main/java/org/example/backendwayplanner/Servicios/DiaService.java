package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.Itinerarios.DiaDTO;
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

    public List<Dia> obtenerDiasPorViajeId(Long viajeId) {
        return diaRepository.findByViaje_Id(viajeId);
    }

    public Dia crearDia(DiaDTO dia) {
        return diaRepository.save(transformarSinDTO(dia));
    }

    public void eliminarDia(Long id) {
        diaRepository.deleteById(id);
    }

    public DiaDTO transformarADTO(Dia dia) {
        DiaDTO diaDTO = new DiaDTO();
        diaDTO.setId(dia.getId());
        diaDTO.setFecha(dia.getFecha());
        diaDTO.setNumeroDia(dia.getNumeroDia());
        diaDTO.setidViaje((dia.getViaje().getId()));
        return diaDTO;
    }

    public Dia transformarSinDTO(DiaDTO diaDTO) {
        Dia dia = new Dia();
        dia.setId(diaDTO.getId());
        dia.setFecha(diaDTO.getFecha());
        dia.setNumeroDia(diaDTO.getNumeroDia());
        dia.setViaje(viajeRepository.findById(diaDTO.getIdViaje())
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado con ID: " + diaDTO.getIdViaje())));
        return dia;
    }


}
