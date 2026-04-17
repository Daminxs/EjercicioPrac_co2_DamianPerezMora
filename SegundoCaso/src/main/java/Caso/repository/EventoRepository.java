/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Caso.repository;

/**
 *
 * @author Damin
 */

import Caso.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    List<Evento> findByActivoTrue();

    List<Evento> findByNombreContaining(String nombre);

    List<Evento> findByFechaBetween(LocalDate inicio, LocalDate fin);
}