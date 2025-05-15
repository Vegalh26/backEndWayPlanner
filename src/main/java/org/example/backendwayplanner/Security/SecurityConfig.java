package org.example.backendwayplanner.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilterChain;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtFilter jwtFilterChain, AuthenticationProvider authenticationProvider) {
        this.jwtFilterChain = jwtFilterChain;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("auth/login").permitAll()
                        .requestMatchers("auth/registro/perfil").permitAll()
                        .requestMatchers("/gastos/**").permitAll()

                        .requestMatchers("/usuario/usuarioPorId/**").permitAll()
                        .requestMatchers("/viajes/listarPorUsuario/**").permitAll()
                        .requestMatchers("/viajes/crear/**").permitAll()
                        .requestMatchers("/viajes/viajePorId/**").permitAll()
                        .requestMatchers("/viajes/eliminar/**").permitAll()
                        .requestMatchers("/viajes/actualizar/**").permitAll()
                        .requestMatchers("notificaciones/enviar/**").permitAll()
                        .requestMatchers("notificaciones/hora-notificacion/**").permitAll()
                        .requestMatchers("notificaciones/establecer-hora/**").permitAll()
                        .requestMatchers("notificaciones/listar/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception.accessDeniedHandler(accessDeniedHandler()))
        ;
        return http.build();
    }
}

