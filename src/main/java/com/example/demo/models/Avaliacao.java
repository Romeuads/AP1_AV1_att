package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;

    private Double nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "id_agendamento", nullable = false, unique = true)
    private Agendamento agendamento;

    @ManyToOne
    @JoinColumn(name = "id_avaliador", nullable = false)
    private Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "id_avaliado", nullable = false)
    private Usuario avaliado;

    public Avaliacao() {
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
    }
}