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

import Caso.service.EventoService;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final EventoService eventoService;

    public ConsultaController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // no cargar todo al entrar
    @GetMapping
    public String inicio() {
        return "consultas/listado";
    }

    // Eventos activos
    @GetMapping("/activos")
    public String activos(Model model) {
        model.addAttribute("eventos", eventoService.eventosActivos());
        model.addAttribute("modo", "activos");
        return "consultas/listado";
    }

    // Buscar por nombre
    @PostMapping("/nombre")
    public String buscarNombre(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("eventos", eventoService.buscarPorNombre(nombre));
        model.addAttribute("modo", "nombre");
        return "consultas/listado";
    }

    // Buscar por fechas busqueda limitada
    @PostMapping("/fechas")
    public String buscarFechas(
            @RequestParam("inicio") String inicio,
            @RequestParam("fin") String fin,
            Model model) {

        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);

        model.addAttribute("eventos",
                eventoService.buscarPorFechas(fechaInicio, fechaFin));

        model.addAttribute("modo", "fechas");
        return "consultas/listado";
    }

    // mostrar todos (botón)
    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("eventos", eventoService.findAll());
        model.addAttribute("modo", "todos");
        return "consultas/listado";
    }
}