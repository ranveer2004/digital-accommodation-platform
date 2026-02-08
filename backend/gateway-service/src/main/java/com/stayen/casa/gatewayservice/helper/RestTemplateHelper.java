package com.stayen.casa.gatewayservice.helper;

import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.HeaderConstant;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Component
public class RestTemplateHelper {

    private final RestTemplate restTemplate;
    private final String internalServiceAuthKey;
//    private final EnvConstant envConstant;

    @Autowired
    public RestTemplateHelper(RestTemplate restTemplate, EnvConstant envConstant) {
        this.restTemplate = restTemplate;
        this.internalServiceAuthKey = envConstant.getInternalServiceAuthKey();
//        this.envConstant = envConstant;
    }

    public <T> ResponseEntity<T> GET(String url, @Nullable Map<String, String> addRequestHeader, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HeaderConstant.AUTH_KEY, internalServiceAuthKey);

        if(addRequestHeader != null) {
            addRequestHeader
                    .forEach((key, value) -> headers.set(key, value));
        }

        HttpEntity<T> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);
    }

    public <T, B> ResponseEntity<T> POST(String url, B body, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HeaderConstant.AUTH_KEY, internalServiceAuthKey);

        HttpEntity<B> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, entity, responseType);
    }

    public <T, B> ResponseEntity<T> PUT(String url, @Nullable Map<String, String> addRequestHeader, B body, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HeaderConstant.AUTH_KEY, internalServiceAuthKey);
        if(addRequestHeader != null) {
            addRequestHeader
                    .forEach((key, value) -> headers.set(key, value));
        }

        HttpEntity<B> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(uri, HttpMethod.PUT, entity, responseType);
    }

    public <T, B> ResponseEntity<T> PATCH(String url, B body, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HeaderConstant.AUTH_KEY, internalServiceAuthKey);

        HttpEntity<B> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(uri, HttpMethod.PATCH, entity, responseType);
    }

    public <T> ResponseEntity<T> DELETE(String url, @Nullable Map<String, String> addRequestHeader, Class<T> responseType) {
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HeaderConstant.AUTH_KEY, internalServiceAuthKey);
        if(addRequestHeader != null) {
            addRequestHeader
                    .forEach((key, value) -> headers.set(key, value));
        }

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.DELETE, entity, responseType);
    }
}
