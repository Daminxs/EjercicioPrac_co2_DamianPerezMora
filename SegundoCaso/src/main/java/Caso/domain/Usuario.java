/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso.domain;

/**
 *
 * @author Damin
 */


// La clase Usuario ocupa:

// id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
// nombre VARCHAR(150), 
// email VARCHAR(200) UNIQUE, 
// password VARCHAR(255), 
// rol_id BIGINT UNSIGNED, 
// activo BOOLEAN DEFAULT TRUE, 
// fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
// FOREIGN KEY (rol_id) REFERENCES rol(id) 

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Column(length = 200, unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(length = 255, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    private boolean activo = true;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
}