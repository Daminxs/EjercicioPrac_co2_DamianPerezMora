/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.controller;

/**
 *
 * @author Damin
 */


// La clase ocupa hacer:

// Listar eventos  
// Crear eventos  
// Editar eventos  
// Eliminar eventos  

import Caso.domain.Evento;
import Caso.service.EventoService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Listado de eventos
    @GetMapping("/listado")
    public String inicio(Model model) {
        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);

        model.addAttribute("evento", new Evento());

        return "/evento/listado";
    }

    // Formulario nuevo evento
    @GetMapping("/nuevo")
    public String nuevoEvento(Evento evento) {
        return "/evento/modifica";
    }

    // Guardar evento
    @PostMapping("/guardar")
    public String guardar(Evento evento) {
        eventoService.save(evento);
        return "redirect:/evento/listado";
    }

    // Eliminar evento
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        eventoService.delete(id);
        return "redirect:/evento/listado";
    }

    // Editar evento
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Evento> evento = eventoService.getEvento(id);

        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get());
        }

        return "/evento/modifica";
    }

}