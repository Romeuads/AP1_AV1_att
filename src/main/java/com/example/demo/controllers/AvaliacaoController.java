package com.example.demo.controllers;

import com.example.demo.models.Avaliacao;
import com.example.demo.services.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoService.salvar(avaliacao);
        return ResponseEntity.status(201).body(novaAvaliacao);
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodos() {
        return ResponseEntity.ok(avaliacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Avaliacao avaliacao) {

        if (avaliacaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        avaliacao.setIdAvaliacao(id);
        avaliacaoService.salvar(avaliacao);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        if (avaliacaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        avaliacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}