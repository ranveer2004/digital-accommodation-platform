package com.stayen.casa.propertyservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.propertyservice.constant.EnvConstant;
import com.stayen.casa.propertyservice.filter.ApiGatewayAccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            EnvConstant envConstant,
            ObjectMapper mapper
    ) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .httpBasic((basicSecurity) -> basicSecurity.disable())
                .formLogin((formLogin) -> formLogin.disable())
//                .cors((cors) -> {
//                })
                .sessionManagement((sessionManager) -> {
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests((request) -> {
                    request
                            .anyRequest().permitAll();
                })
                .addFilterBefore(
                        new ApiGatewayAccessFilter(envConstant, mapper),
                        UsernamePasswordAuthenticationFilter.class
                );

        return httpSecurity.build();
    }

}
