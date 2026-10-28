package com.example.demo.controllers;

import com.example.demo.models.Agendamento;
import com.example.demo.models.Servico;
import com.example.demo.models.Usuario;
import com.example.demo.services.AgendamentoService;
import com.example.demo.services.ServicoService;
import com.example.demo.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ServicoService servicoService;
    private final AgendamentoService agendamentoService;

    public UsuarioController(
            UsuarioService usuarioService,
            ServicoService servicoService,
            AgendamentoService agendamentoService) {

        this.usuarioService = usuarioService;
        this.servicoService = servicoService;
        this.agendamentoService = agendamentoService;
    }

    // POST /usuario
    @PostMapping
    public ResponseEntity<Usuario> criar(
            @RequestBody Usuario usuario) {

        Usuario novoUsuario = usuarioService.criar(usuario);

        return ResponseEntity.status(201).body(novoUsuario);
    }

    // GET /usuario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Integer id) {

        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /usuario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Integer id,
            @RequestBody Usuario usuario) {

        if (usuarioService.atualizar(id, usuario).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    // DELETE /usuario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        if (usuarioService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // GET /usuario/{id}/servicos
    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<Servico>> listarServicos(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                servicoService.listarPorUsuario(id)
        );
    }

    // GET /usuario/{id}/agendamentos/prestador
    @GetMapping("/{id}/agendamentos/prestador")
    public ResponseEntity<List<Agendamento>> listarComoPrestador(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                agendamentoService.listarPorPrestador(id)
        );
    }

    // GET /usuario/{id}/agendamentos/solicitante
    @GetMapping("/{id}/agendamentos/solicitante")
    public ResponseEntity<List<Agendamento>> listarComoSolicitante(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                agendamentoService.listarPorSolicitante(id)
        );
    }
}