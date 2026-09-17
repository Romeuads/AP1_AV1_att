package com.example.demo.services;

import com.example.demo.models.Administrador;
import com.example.demo.repositories.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador salvar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> buscarPorId(Integer id) {
        return administradorRepository.findById(id);
    }

    public void excluir(Integer id) {
        administradorRepository.deleteById(id);
    }
}