package com.stayen.casa.gatewayservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stayen.casa.gatewayservice.security.filter.JwtFilter;
import com.stayen.casa.gatewayservice.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;


@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        /**
         * To allow Jackson to use Java 8 DateTime API
         * and to stop Jackson default deserialization of DateTime API into milliseconds(e.g., 1722163800000)
         * instead show DateTime as human-readable format
         */
        mapper
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    @Bean
    public JwtFilter jwtFilter(JwtUtils jwtUtils, ObjectMapper mapper, AntPathMatcher antPathMatcher) {
        return new JwtFilter(jwtUtils, mapper, antPathMatcher);
    }

    @Bean
    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
