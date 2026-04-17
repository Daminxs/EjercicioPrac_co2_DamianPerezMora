package Caso.service;

/**
 *
 * @author Damin
 */

import Caso.domain.Usuario;
import Caso.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()))
        );
    }
}