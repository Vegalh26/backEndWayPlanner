package org.example.backendwayplanner.Servicios;

import org.example.backendwayplanner.Dtos.Maletas.CrearObjetoDTO;
import org.example.backendwayplanner.Dtos.Maletas.ListarObjetosMaletasDTO;
import org.example.backendwayplanner.Dtos.Maletas.MasCantidadObjetoDTO;
import org.example.backendwayplanner.Dtos.Maletas.ObjetoSeleccionadoDTO;
import org.example.backendwayplanner.Entidades.Maleta;
import org.example.backendwayplanner.Entidades.ObjetoMaleta;
import org.example.backendwayplanner.Enums.CategoriaObjeto;
import org.example.backendwayplanner.Repositorios.MaletaRepository;
import org.example.backendwayplanner.Repositorios.ObjetoMaletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ObjetoMaletaService {
    @Autowired
    private ObjetoMaletaRepository objetoMaletaRepository;

    @Autowired
    private MaletaRepository maletaRepository;

    // Obtener todos los objetos de una maleta por su ID
    public List<ListarObjetosMaletasDTO> getObjetosByMaletaId(Long maletaId) {
        Maleta maleta = maletaRepository.findById(maletaId)
                .orElseThrow(() -> new RuntimeException("Maleta not found"));

        List<ObjetoMaleta> objetosMaleta = objetoMaletaRepository.findByMaletaId(maleta);

        // Invertir el orden
        objetosMaleta.sort(Comparator.comparing(ObjetoMaleta::getNombre));

        return objetosMaleta.stream()
                .map(objetoMaleta -> new ListarObjetosMaletasDTO(
                        objetoMaleta.getId(),
                        objetoMaleta.getNombre(),
                        objetoMaleta.getCantidad(),
                        objetoMaleta.getCategoria().toString(),
                        objetoMaleta.getIsSelected(),
                        objetoMaleta.getMaletaId().getId()))
                .toList();
    }

    // CRUD Objeto Maleta
    // ---------------------------------------
    // Crear un nuevo objeto en una maleta
    public List<ListarObjetosMaletasDTO> crearObjetoMaleta(CrearObjetoDTO crearObjetoDTO) {
        ObjetoMaleta objetoMaleta = new ObjetoMaleta();
        objetoMaleta.setNombre(crearObjetoDTO.getNombre());
        objetoMaleta.setCantidad(crearObjetoDTO.getCantidad());

        CategoriaObjeto categoria = CategoriaObjeto.valueOf(crearObjetoDTO.getCategoria());
        objetoMaleta.setCategoria(categoria);

        objetoMaleta.setIsSelected(false);

        Maleta maletaId = maletaRepository.findById(crearObjetoDTO.getMaletaId())
                .orElseThrow(() -> new RuntimeException("Maleta not found"));
        objetoMaleta.setMaletaId(maletaId);

        objetoMaletaRepository.save(objetoMaleta);

        return getObjetosByMaletaId(crearObjetoDTO.getMaletaId());
    }

    // Actualizar un objeto de una maleta
    public List<ListarObjetosMaletasDTO> actualizarObjetoMaleta(Long id, CrearObjetoDTO crearObjetoDTO) {
        ObjetoMaleta objetoMaletaExistente = objetoMaletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        objetoMaletaExistente.setNombre(crearObjetoDTO.getNombre());
        objetoMaletaExistente.setCantidad(crearObjetoDTO.getCantidad());
        objetoMaletaExistente.setCategoria(CategoriaObjeto.valueOf(crearObjetoDTO.getCategoria()));

        objetoMaletaRepository.save(objetoMaletaExistente);

        Long maletaId = objetoMaletaExistente.getMaletaId().getId();
        return getObjetosByMaletaId(maletaId);
    }

    // Eliminar un objeto de una maleta por su ID
    public List<ListarObjetosMaletasDTO> eliminarObjetoMaleta(Long id) {
        // Obtener el objeto para extraer el maletaId
        ObjetoMaleta objetoMaleta = objetoMaletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        Long maletaId = objetoMaleta.getMaletaId().getId(); // obtener ID de la maleta

        // Eliminar el objeto
        objetoMaletaRepository.deleteById(id);

        // Devolver la lista actualizada de objetos de esa maleta
        return getObjetosByMaletaId(maletaId);
    }
    // -----------------------------------------

    // Marcar un objeto como seleccionado o no
    public List<ListarObjetosMaletasDTO> seleccionarObjetoMaleta(Long id, ObjetoSeleccionadoDTO objetoSeleccionadoDTO) {
        ObjetoMaleta objetoMaleta = objetoMaletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        // Actualizar el estado de selección
        objetoMaleta.setIsSelected(objetoSeleccionadoDTO.getIsSelected());

        objetoMaletaRepository.save(objetoMaleta);

        // Obtener el ID de la maleta para devolver la lista actualizada
        Long maletaId = objetoMaleta.getMaletaId().getId();
        return getObjetosByMaletaId(maletaId);
    }

    // Cambiar la cantidad de un objeto en una maleta
    public List<ListarObjetosMaletasDTO> cambiarCantidadObjetoMaleta(Long id, MasCantidadObjetoDTO masCantidadObjetoDTO) {
        ObjetoMaleta objetoMaleta = objetoMaletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        // Actualizar la cantidad del objeto
        objetoMaleta.setCantidad(masCantidadObjetoDTO.getCantidad());

        objetoMaletaRepository.save(objetoMaleta);

        // Obtener el ID de la maleta para devolver la lista actualizada
        Long maletaId = objetoMaleta.getMaletaId().getId();
        return getObjetosByMaletaId(maletaId);
    }
}