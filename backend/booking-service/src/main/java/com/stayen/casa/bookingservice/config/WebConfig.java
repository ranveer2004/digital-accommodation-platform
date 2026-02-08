package com.stayen.casa.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class enables global CORS configuration for the Booking Service.
 * This allows the frontend to make HTTP requests to the backend without being blocked by CORS policy.
 */

@Configuration
public class WebConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:5173")
				.allowedMethods("GET","POST","PUT","DELETE","PATCH")
				.allowedHeaders("*")
				.allowCredentials(true);
			}
		};
	}
}
