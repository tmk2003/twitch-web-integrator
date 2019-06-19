package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author tmk2003
 */
@Slf4j
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

    /**
     *  Perform the GET request to the provided URI
     * @param uri The URI to perform the request to
     * @param entity The entity we are sending to the URI
     * @param clazz The expected object type to receive back
     * @param <T> The Class type of the returned object
     * @return The Response from the GET request
     */
    protected <T> ResponseEntity<T> getRequest(String uri, HttpEntity entity, Class<T> clazz) {
        try {
            return restTemplate.getForEntity(uri, clazz, entity);
        } catch(HttpClientErrorException | HttpServerErrorException ex) {
            log.error("Could not complete request: {}", ex.getMessage());
            throw new RestTemplateClientException("Get Request Failure", ex);
        }
    }
}
