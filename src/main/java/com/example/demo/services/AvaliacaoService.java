package com.example.demo.services;

import com.example.demo.models.Avaliacao;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repositories.UsuarioRepository;

@Service
public class AvaliacaoService {

   private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public AvaliacaoService(
        AvaliacaoRepository avaliacaoRepository,
        UsuarioRepository usuarioRepository) {

    this.avaliacaoRepository = avaliacaoRepository;
    this.usuarioRepository = usuarioRepository;
    }
    
    public Avaliacao salvar(Avaliacao avaliacao) {

        Avaliacao salva = avaliacaoRepository.save(avaliacao);

        Integer idUsuarioAvaliado =
                salva.getAvaliado().getIdUsuario();

        List<Avaliacao> avaliacoes =
                avaliacaoRepository.findByAvaliadoIdUsuario(idUsuarioAvaliado);

        double soma = 0;

        for (Avaliacao a : avaliacoes) {
        soma += a.getNota();
        }

        double media = soma / avaliacoes.size();

        Usuario usuario = usuarioRepository
                .findById(idUsuarioAvaliado)
                .orElseThrow();

        usuario.setAvaliacaoMedia(media);

        usuarioRepository.save(usuario);

        return salva;
    }

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Integer id) {
        return avaliacaoRepository.findById(id);
    }

    public void excluir(Integer id) {
        avaliacaoRepository.deleteById(id);
    }

    public Optional<Avaliacao> buscarPorAgendamento(Integer idAgendamento) {
    return avaliacaoRepository.findByAgendamentoIdAgendamento(idAgendamento);
    }
}