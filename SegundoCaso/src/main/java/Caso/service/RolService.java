/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.service;

/**
 *
 * @author Damin
 */

import Caso.domain.Rol;
import Caso.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Listar roles
    @Transactional(readOnly = true)
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    // Buscar rol por id
    @Transactional(readOnly = true)
    public Optional<Rol> getRol(Long id) {
        return rolRepository.findById(id);
    }

    // Crear o editar rol
    @Transactional
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    // Eliminar rol
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

}