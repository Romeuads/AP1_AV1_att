package com.example.demo.services;

import com.example.demo.models.Agendamento;
import com.example.demo.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Integer id) {
        return agendamentoRepository.findById(id);
    }

    public void excluir(Integer id) {
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> listarPorServico(Integer idServico) {
    return agendamentoRepository.findByServicoIdServico(idServico);
    }

    public List<Agendamento> listarPorPrestador(Integer idUsuario) {
    return agendamentoRepository.findByUsuarioPrestadorIdUsuario(idUsuario);
    }

    public List<Agendamento> listarPorSolicitante(Integer idUsuario) {
    return agendamentoRepository.findByUsuarioSolicitanteIdUsuario(idUsuario);
    }
}