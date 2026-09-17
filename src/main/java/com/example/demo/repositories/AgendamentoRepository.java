package com.example.demo.repositories;

import com.example.demo.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByServicoIdServico(Integer idServico);

    List<Agendamento> findByUsuarioPrestadorIdUsuario(Integer idUsuario);

    List<Agendamento> findByUsuarioSolicitanteIdUsuario(Integer idUsuario);
}