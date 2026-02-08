package com.stayen.casa.propertyservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.propertyservice.constant.EnvConstant;
import com.stayen.casa.propertyservice.filter.ApiGatewayAccessFilter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());

        return mapper;
    }

}
