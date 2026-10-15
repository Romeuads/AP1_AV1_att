
package com.example.demo.services;

import com.example.demo.enums.ProfileEnum;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(Usuario usuario) {

        if (usuario.getIdUsuario() == null) {

            // Criptografa a senha no cadastro
            usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
            );

            // Define o perfil padrão
            usuario.setProfiles(
                new HashSet<>(Set.of(ProfileEnum.USER))
            );

        } else if (usuario.getSenha() != null
                && !usuario.getSenha().isBlank()) {

            // Criptografa uma nova senha informada
            usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
            );
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
