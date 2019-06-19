package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public abstract class RestTemplateClient {
    @Getter
    private final RestTemplate restTemplate;

    /**
     * Create the rest template client
     */
    public RestTemplateClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Create the header with the required header fields
     * @return the header with the required fields added
     */
    protected abstract HttpHeaders getHeaders();

    /**
     * Generate the response body from steam based off the uri
     *
     * @param uri - Generated uri based off the request
     * @return - Response body
     */
    protected String makeRequest(String uri, HttpMethod httpMethod, HttpEntity entity) {
        HttpEntity<String> response = Optional.ofNullable(
                restTemplate.exchange(
                        uri, httpMethod, entity, String.class
                )
        ).orElseThrow(
                () -> new RestTemplateClientException("Response was Null")
        );
        return Optional.ofNullable(response.getBody()).orElseThrow(
                () -> new RestTemplateClientException("Response Body was Null")
        );
    }

    // TODO
    protected <T> ResponseEntity<T> getRequest(String uri, HttpEntity entity, Class<T> clazz) {
        return restTemplate.getForEntity(uri, clazz, entity);
    }
}
