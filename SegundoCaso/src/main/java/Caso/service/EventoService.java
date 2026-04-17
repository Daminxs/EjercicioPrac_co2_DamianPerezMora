/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.service;

/**
 *
 * @author Damin
 */

import Caso.domain.Evento;
import Caso.repository.EventoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // Listar eventos
    @Transactional(readOnly = true)
    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    // Buscar evento por id
    @Transactional(readOnly = true)
    public Optional<Evento> getEvento(Long id) {
        return eventoRepository.findById(id);
    }

    // Crear o editar evento
    @Transactional
    public void save(Evento evento) {
        eventoRepository.save(evento);
    }

    // Eliminar evento
    @Transactional
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    // Consultas

    @Transactional(readOnly = true)
    public List<Evento> eventosActivos() {
        return eventoRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarPorNombre(String nombre) {
        return eventoRepository.findByNombreContaining(nombre);
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarPorFechas(LocalDate inicio, LocalDate fin) {
        return eventoRepository.findByFechaBetween(inicio, fin);
    }
}