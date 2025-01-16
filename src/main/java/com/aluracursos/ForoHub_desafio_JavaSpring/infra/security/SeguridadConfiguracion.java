package com.aluracursos.ForoHub_desafio_JavaSpring.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/login").permitAll() // Permitir acceso sin autenticación al endpoint de login
                                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para simplificar
                .httpBasic(); // Usa autenticación básica para el desarrollo inicial

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception {
        return new ProviderManager(new DaoAuthenticationProvider() {{
            setUserDetailsService(userDetailsService);
            setPasswordEncoder(passwordEncoder());
        }});
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar codificación segura de contraseñas
    }
}
