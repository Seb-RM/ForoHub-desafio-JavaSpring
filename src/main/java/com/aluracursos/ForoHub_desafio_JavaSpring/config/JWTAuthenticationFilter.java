package com.aluracursos.ForoHub_desafio_JavaSpring.config;

import com.aluracursos.ForoHub_desafio_JavaSpring.service.JWTAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTAuthService jwtAuthService;

    @Autowired
    public JWTAuthenticationFilter(@Lazy JWTAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Authentication authentication = jwtAuthService.getAuthentication(token);

            if (authentication != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Configurar directamente el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuar con el resto del filtro
        filterChain.doFilter(request, response);
    }
}


