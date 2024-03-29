package com.example.jwtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authprovider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception{
        hs.csrf().disable().
                authorizeHttpRequests().requestMatchers("/api/**","/api/authentication"
                        ).permitAll().anyRequest().authenticated().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authenticationProvider(authprovider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return hs.build();

    }
}
