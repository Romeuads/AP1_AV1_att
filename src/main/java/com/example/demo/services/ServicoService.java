package com.example.demo.services;

import com.example.demo.models.Servico;
import com.example.demo.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Integer id) {
        return servicoRepository.findById(id);
    }

    public void excluir(Integer id) {
        servicoRepository.deleteById(id);
    }

    public List<Servico> listarPorUsuario(Integer idUsuario) {
        return servicoRepository.findByUsuarioIdUsuario(idUsuario);
    }
}