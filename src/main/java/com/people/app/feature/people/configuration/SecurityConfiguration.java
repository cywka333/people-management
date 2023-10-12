package com.people.app.feature.people.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests
                                        .requestMatchers(antMatcher(HttpMethod.OPTIONS, "/**"))
                                        .permitAll()
                                        .requestMatchers(antMatcher("/api/v1/auth/login"))
                                        .permitAll()
                                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/people/search"))
                                        .permitAll()
                                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/positions"))
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/people/add").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/people/edit/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/api/import").hasAnyRole("ADMIN", "IMPORTER")
                                        .requestMatchers(HttpMethod.POST, "/api/employees/**/positions").hasAnyRole("ADMIN", "EMPLOYEE")
                                        .anyRequest()
                                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}