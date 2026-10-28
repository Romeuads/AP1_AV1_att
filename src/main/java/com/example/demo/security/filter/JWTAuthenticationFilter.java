package com.example.demo.security.filter;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil,
            ObjectMapper objectMapper) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    public record LoginRequest(String username, String password) {
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        if (!isLoginRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        LoginRequest login;

        try {
            login = objectMapper.readValue(
                request.getInputStream(), LoginRequest.class
            );
        } catch (Exception e) {
            escreverErro(response, HttpServletResponse.SC_BAD_REQUEST,
                "Corpo da requisição inválido");
            return;
        }

        try {
            Authentication authentication =
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        login.username(), login.password()
                    )
                );

            String token = jwtUtil.generateToken(authentication.getName());

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Authorization", "Bearer " + token);
            response.getWriter().write("{\"token\":\"" + token + "\"}");

        } catch (AuthenticationException e) {
            escreverErro(response, HttpServletResponse.SC_UNAUTHORIZED,
                "Usuário ou senha inválidos");
        }
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod())
                && "/login".equals(request.getServletPath());
    }

    private void escreverErro(
            HttpServletResponse response,
            int status,
            String mensagem) throws IOException {

        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            "{\"status\":" + status + ",\"erro\":\"" + mensagem + "\"}"
        );
    }
}
