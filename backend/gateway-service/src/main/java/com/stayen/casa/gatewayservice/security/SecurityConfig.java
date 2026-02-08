package com.stayen.casa.gatewayservice.security;

import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.security.filter.JwtFilter;
import jakarta.servlet.http.HttpServletRequest;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter, EnvConstant envConstant) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .httpBasic((basicSecurity) -> basicSecurity.disable())
                .formLogin((formLogin) -> formLogin.disable())
                .cors((corsHandler) -> {
                    corsHandler.configurationSource(new CorsConfigurationSource() {
                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of(envConstant.getFrontendDomain()));  // "http://localhost:5173"
                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                            config.setAllowedHeaders(List.of("*"));
                            config.setAllowCredentials(true);
                            return config;
                        }
                    });
                })
                .sessionManagement((sessionManager) -> {
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests((requests) -> {
                    String[] whitelistedUrlArray = JwtFilter.PATH_AND_METHOD_EXCLUDED_FROM_JWT_FILTER
                            .keySet()
                            .toArray(new String[0]);

                    requests
                            .requestMatchers(whitelistedUrlArray).permitAll()
                            .anyRequest().authenticated();

//                    requests
//                            .requestMatchers(
//                                    "api/v1/auth/test", "api/v1/users/test",
//                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.LOGIN),  // "/api/v1/auth/login",
//                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP_OTP),  // "/api/v1/auth/signup-otp",
//                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.SIGNUP),  // "/api/v1/auth/signup",
//                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.FORGOT_PASSWORD),  // "/api/v1/auth/forgot-password",
//                                    (Endpoints.Auth.BASE_URL + Endpoints.Auth.VERIFY_AND_CHANGE_PASSWORD),  // "/api/v1/auth/change-password",
//                                    "/swagger-ui/**", "/swagger-ui.html",
//                            ).permitAll()
//                            .anyRequest().authenticated();
////                            .anyRequest().permitAll();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
