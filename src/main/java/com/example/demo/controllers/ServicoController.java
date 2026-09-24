package com.example.demo.controllers;

import com.example.demo.models.Agendamento;
import com.example.demo.models.Servico;
import com.example.demo.services.AgendamentoService;
import com.example.demo.services.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;
    private final AgendamentoService agendamentoService;

    public ServicoController(
            ServicoService servicoService,
            AgendamentoService agendamentoService) {

        this.servicoService = servicoService;
        this.agendamentoService = agendamentoService;
    }

    // POST /servico
    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody Servico servico) {

        Servico novoServico = servicoService.salvar(servico);

        return ResponseEntity
                .status(201)
                .body(novoServico);
    }

    // GET /servico
    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {

        return ResponseEntity.ok(
                servicoService.listarTodos()
        );
    }

    // GET /servico/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(
            @PathVariable Integer id) {

        return servicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        
    // PUT /servico/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Servico servico) {

        var servicoExistente = servicoService.buscarPorId(id);

        if (servicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Servico existente = servicoExistente.get();

        if (servico.getTitulo() != null) {
            existente.setTitulo(servico.getTitulo());
        }

        if (servico.getDescricao() != null) {
            existente.setDescricao(servico.getDescricao());
        }

        if (servico.getHorasEstimadas() != null) {
            existente.setHorasEstimadas(servico.getHorasEstimadas());
        }

        if (servico.getStatus() != null) {
            existente.setStatus(servico.getStatus());
        }

        // Mantém o usuário já associado ao serviço

        servicoService.salvar(existente);

        return ResponseEntity.noContent().build();
    }

    // DELETE /servico/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        if (servicoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        servicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // GET /servico/{idServico}/agendamentos
    @GetMapping("/{idServico}/agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(
            @PathVariable Integer idServico) {

        return ResponseEntity.ok(
                agendamentoService.listarPorServico(idServico)
        );
    }
}