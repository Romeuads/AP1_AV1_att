
package com.example.demo.security;

import com.example.demo.enums.ProfileEnum;
import com.example.demo.models.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioSpringSecurity implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<ProfileEnum> profiles;

    public UsuarioSpringSecurity(Usuario usuario) {
        this.id = usuario.getIdUsuario();
        this.username = usuario.getEmail();
        this.password = usuario.getSenha();

        this.profiles = usuario.getProfiles() == null
                ? Collections.emptySet()
                : Set.copyOf(usuario.getProfiles());
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority>
            getAuthorities() {

        return profiles.stream()
                .map(perfil ->
                    new SimpleGrantedAuthority(
                        perfil.getDescricao()
                    )
                )
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(ProfileEnum profile) {
        return profiles.contains(profile);
    }
}
