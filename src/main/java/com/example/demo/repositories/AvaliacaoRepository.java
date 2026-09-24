package com.example.demo.repositories;

import com.example.demo.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.List;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    Optional<Avaliacao> findByAgendamentoIdAgendamento(Integer idAgendamento);
    List<Avaliacao> findByAvaliadoIdUsuario(Integer idUsuario);
}