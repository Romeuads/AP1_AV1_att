package com.example.demo.models;

import com.example.demo.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario {


    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "user_profiles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "profile")
    private Set<ProfileEnum> profiles = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private Double saldoHoras;

    private Double avaliacaoMedia;

    public Usuario() {
        this.profiles = new HashSet<>();
        this.profiles.add(ProfileEnum.USER);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getSaldoHoras() {
        return saldoHoras;
    }

    public void setSaldoHoras(Double saldoHoras) {
        this.saldoHoras = saldoHoras;
    }

    public Double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(Double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public Set<ProfileEnum> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<ProfileEnum> profiles) {
        this.profiles = profiles;
    }

}