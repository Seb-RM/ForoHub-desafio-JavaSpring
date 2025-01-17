package com.aluracursos.ForoHub_desafio_JavaSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTAuthService {

    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @Autowired
    public JWTAuthService(TokenService tokenService, @Lazy UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    public Authentication getAuthentication(String token) {
        // Validar el token y obtener el email asociado
        String email = tokenService.validarToken(token);

        if (email != null) {
            // Cargar los detalles del usuario desde el servicio
            UserDetails userDetails = usuarioService.loadUserByUsername(email);

            // Crear el objeto Authentication
            return new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
        }
        return null; // Retorna null si el token no es válido
    }
}




