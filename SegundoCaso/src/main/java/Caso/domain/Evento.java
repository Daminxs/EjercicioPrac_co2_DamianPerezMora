/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.domain;

/**
 *
 * @author Damin
 */


// La clase Evento ocupa:

// id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
// nombre VARCHAR(150), 
// descripcion TEXT, 
// fecha DATE, 
// capacidad INT, 
// activo BOOLEAN DEFAULT TRUE 

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDate fecha;

    private Integer capacidad;

    private boolean activo = true;

}
