package com.stayen.casa.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stayen.casa.userservice.constant.EnvConstant;
import com.stayen.casa.userservice.filter.ApiGatewayAccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
public class AppConfig {

    /**
     * Creating ObjectMapper Bean instance
     * with new Java 8 DateTime API
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    /**
     * Creating ApiGatewayAccess Filter Bean instance
     * @return ApiGatewayAccessFilter
     */
    @Bean
    public ApiGatewayAccessFilter apiGatewayAccessFilter(EnvConstant envConstant, ObjectMapper mapper) {
        return new ApiGatewayAccessFilter(envConstant, mapper);
    }

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

}
