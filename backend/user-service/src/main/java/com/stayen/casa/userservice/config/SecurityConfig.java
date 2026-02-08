package com.stayen.casa.userservice.config;

import com.stayen.casa.userservice.constant.EnvConstant;
import com.stayen.casa.userservice.filter.ApiGatewayAccessFilter;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final EnvConstant envConstant;

    @Autowired
    SecurityConfig(EnvConstant envConstant) {
        this.envConstant = envConstant;
    }

    /**
     * SecurityFilter Bean
     * for intercepting all requests and
     * authorising user
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, ApiGatewayAccessFilter apiGatewayAccessFilter) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .httpBasic((basicAuth) -> basicAuth.disable())
                .sessionManagement((sessionManager) -> {
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
//                .cors((corsConfig) -> {
//                    corsConfig.configurationSource(new CorsConfigurationSource() {
//                        @Override
//                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                            CorsConfiguration config = new CorsConfiguration();
//                            config.setAllowedOrigins(List.of(envConstant.getApiGatewayServiceBaseUrl()));
//                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
//                            config.setAllowedHeaders(List.of("User-Email", "Content-Type"));
//                            config.setAllowCredentials(true);
//                            return config;
//                        }
//                    });
//                })
                .authorizeHttpRequests((requests) -> {
                    requests
                            .anyRequest().permitAll();
                })
                .addFilterBefore(apiGatewayAccessFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
