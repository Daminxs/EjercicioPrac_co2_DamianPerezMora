/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Caso.controller;

/**
 *
 * @author Damin
 */

// La clase ocupa:

// Listar usuarios  
// Crear usuario  
// Editar usuario
// Eliminar usuario  
// Ver detalle del usuario

import Caso.domain.Usuario;
import Caso.service.RolService;
import Caso.service.UsuarioService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService,
            RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    // Listado de usuarios
    @GetMapping("/listado")
    public String inicio(Model model) {
        var usuarios = usuarioService.getUsuarios();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.getRoles());

        return "/usuario/listado";
    }

    // Formulario nuevo usuario
    @GetMapping("/nuevo")
    public String nuevoUsuario(Usuario usuario, Model model) {
        model.addAttribute("roles", rolService.getRoles());
        return "/usuario/modifica";
    }

    // Guardar usuario
    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/listado";
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return "redirect:/usuario/listado";
    }

    // Editar usuario
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.getUsuario(id);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("roles", rolService.getRoles());
        }

        return "/usuario/modifica";
    }

}