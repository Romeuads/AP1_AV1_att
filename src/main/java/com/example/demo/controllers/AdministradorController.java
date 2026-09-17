package com.example.demo.controllers;

import com.example.demo.models.Administrador;
import com.example.demo.services.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostMapping
    public ResponseEntity<Administrador> criar(@RequestBody Administrador administrador) {
        Administrador novoAdministrador = administradorService.salvar(administrador);
        return ResponseEntity.status(201).body(novoAdministrador);
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listarTodos() {
        return ResponseEntity.ok(administradorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable Integer id) {
        return administradorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Administrador administrador) {

        if (administradorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        administrador.setIdAdmin(id);
        administradorService.salvar(administrador);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        if (administradorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        administradorService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}