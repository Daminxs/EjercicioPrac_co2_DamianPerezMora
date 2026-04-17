/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.service;

/**
 *
 * @author Damin
 */

import Caso.domain.Usuario;
import Caso.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender mailSender;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          JavaMailSender mailSender) {
        this.usuarioRepository = usuarioRepository;
        this.mailSender = mailSender;
    }

    // Listar usuarios
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    // Ver detalle usuario por id
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    // Crear y editar usuario
    @Transactional
    public void save(Usuario usuario) {

        boolean esNuevo = (usuario.getId() == null);

        usuarioRepository.save(usuario);

        // Enviar correo SOLO si es nuevo
        if (esNuevo) {
            enviarCorreo(usuario);
        }
    }

    // Eliminar usuario
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para enviar correo
    private void enviarCorreo(Usuario usuario) {

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(usuario.getEmail());
        mensaje.setSubject("Bienvenido al Sistema de Eventos");
        mensaje.setText("Usuario creado correctamente");

        mailSender.send(mensaje);
    }
}