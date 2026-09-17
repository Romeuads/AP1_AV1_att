package com.example.demo.repositories;

import com.example.demo.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByUsuarioIdUsuario(Integer idUsuario);
}