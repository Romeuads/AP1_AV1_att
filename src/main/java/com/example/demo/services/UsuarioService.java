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

    public Usuario criar(Usuario usuario) {
        usuario.setIdUsuario(null);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setProfiles(new HashSet<>(Set.of(ProfileEnum.USER)));

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizar(Integer id, Usuario dados) {
        return usuarioRepository.findById(id).map(existente -> {

            if (dados.getNome() != null) {
                existente.setNome(dados.getNome());
            }

            if (dados.getEmail() != null) {
                existente.setEmail(dados.getEmail());
            }

            if (dados.getSenha() != null && !dados.getSenha().isBlank()) {
                existente.setSenha(passwordEncoder.encode(dados.getSenha()));
            }

            if (dados.getBio() != null) {
                existente.setBio(dados.getBio());
            }

            if (dados.getSaldoHoras() != null) {
                existente.setSaldoHoras(dados.getSaldoHoras());
            }

            if (dados.getAvaliacaoMedia() != null) {
                existente.setAvaliacaoMedia(dados.getAvaliacaoMedia());
            }

            return usuarioRepository.save(existente);
        });
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
