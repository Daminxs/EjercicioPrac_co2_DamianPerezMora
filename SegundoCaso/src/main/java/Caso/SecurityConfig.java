/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso;

/**
 *
 * @author Damin
 */

// La clase ocupa lo siguiente:

// Login y logout  

// Restricción de acceso según rol: 
// Rol 
// ADMIN 
// ORGANIZADOR 
// CLIENTE 
// Redirección según rol después del login

// Permisos 
// Gestiona usuarios, roles y eventos 
// Gestiona eventos 
// Visualiza eventos

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filtros(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((request) -> request

                // Para todos.
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**")
                    .permitAll()

                // ADMIN SOLO
                .requestMatchers("/usuario/**", "/rol/**")
                    .hasRole("ADMIN")

                // CONSULTAS SOLO ADMIN
                .requestMatchers("/consultas/**")
                    .hasRole("ADMIN")

                // EVENTOS CRUD
                .requestMatchers("/evento/nuevo").hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/evento/guardar").hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/evento/editar/**").hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/evento/eliminar/**").hasAnyRole("ADMIN", "ORGANIZADOR")

                // SOLO LISTADO (TODOS PUEDEN VER)
                .requestMatchers("/evento/listado")
                    .hasAnyRole("ADMIN", "ORGANIZADOR", "CLIENTE")

                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/login")
                .successHandler(verificadorRol())
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler verificadorRol() {
        return (request, response, authentication) -> {

            var roles = authentication.getAuthorities();

            if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/");

            } else if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ORGANIZADOR"))) {
                response.sendRedirect("/");

            } else if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_CLIENTE"))) {
                response.sendRedirect("/");

            } else {
                response.sendRedirect("/");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}