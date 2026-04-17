/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.controller;

/**
 *
 * @author Damin
 */

import Caso.domain.Rol;
import Caso.service.RolService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Listado de roles
    @GetMapping("/listado")
    public String inicio(Model model) {
        var roles = rolService.getRoles();
        model.addAttribute("roles", roles);

        model.addAttribute("rol", new Rol());

        return "/rol/listado";
    }

    // Formulario nuevo rol
    @GetMapping("/nuevo")
    public String nuevoRol(Rol rol) {
        return "/rol/modifica";
    }

    // Guardar rol
    @PostMapping("/guardar")
    public String guardar(Rol rol) {
        rolService.save(rol);
        return "redirect:/rol/listado";
    }

    // Eliminar rol
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        rolService.delete(id);
        return "redirect:/rol/listado";
    }

    // Editar rol
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Rol> rol = rolService.getRol(id);

        if (rol.isPresent()) {
            model.addAttribute("rol", rol.get());
        }

        return "/rol/modifica";
    }

}