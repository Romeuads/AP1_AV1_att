package com.example.demo.services;

import com.example.demo.models.Avaliacao;
import com.example.demo.repositories.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
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