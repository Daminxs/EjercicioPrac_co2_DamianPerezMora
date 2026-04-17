/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.domain;

/**
 *
 * @author Damin
 */


// La clase rol ocupa:

// id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
// nombre VARCHAR(100) NOT NULL UNIQUE

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    private String nombre;

}