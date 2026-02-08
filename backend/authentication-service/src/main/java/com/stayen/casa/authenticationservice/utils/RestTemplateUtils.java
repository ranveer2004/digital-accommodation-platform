package com.stayen.casa.authenticationservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Component
public class RestTemplateUtils {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> POST(String url, Map<String, String> headerMap, Map<String, Object> requestBody, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headerMap.entrySet()
                .stream()
                .forEach((entry) -> {
                    headers.set(entry.getKey(), entry.getValue());
                });

        HttpEntity<Map> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, entity, responseType);
    }

}
