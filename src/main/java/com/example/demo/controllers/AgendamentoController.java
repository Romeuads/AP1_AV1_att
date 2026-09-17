package com.example.demo.controllers;

import com.example.demo.models.Agendamento;
import com.example.demo.models.Avaliacao;
import com.example.demo.services.AgendamentoService;
import com.example.demo.services.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AvaliacaoService avaliacaoService;

    public AgendamentoController(
            AgendamentoService agendamentoService,
            AvaliacaoService avaliacaoService) {

        this.agendamentoService = agendamentoService;
        this.avaliacaoService = avaliacaoService;
    }

    // POST /agendamento
    @PostMapping
    public ResponseEntity<Agendamento> criar(
            @RequestBody Agendamento agendamento) {

        Agendamento novoAgendamento = agendamentoService.salvar(agendamento);

        return ResponseEntity.status(201).body(novoAgendamento);
    }

    // GET /agendamento
    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodos() {

        return ResponseEntity.ok(
                agendamentoService.listarTodos()
        );
    }

    // GET /agendamento/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(
            @PathVariable Integer id) {

        return agendamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /agendamento/{idAgendamento}/avaliacao
    @GetMapping("/{idAgendamento}/avaliacao")
    public ResponseEntity<Avaliacao> buscarAvaliacao(
            @PathVariable Integer idAgendamento) {

        return avaliacaoService.buscarPorAgendamento(idAgendamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /agendamento/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Agendamento agendamento) {

        if (agendamentoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamento.setIdAgendamento(id);
        agendamentoService.salvar(agendamento);

        return ResponseEntity.noContent().build();
    }

    // DELETE /agendamento/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        if (agendamentoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}