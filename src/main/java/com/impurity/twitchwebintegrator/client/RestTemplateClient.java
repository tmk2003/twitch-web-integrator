package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.exception.twitch.RestTemplateClientException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public abstract class RestTemplateClient {
    private final RestTemplate _restTemplate;

    /**
     * Create the rest template client
     */
    public RestTemplateClient() {
        this._restTemplate = new RestTemplate();
    }

    /**
     * Generate the response body from steam based off the uri
     *
     * @param uri - Generated uri based off the request
     * @return - Response body
     */
    protected String makeRequest(String uri, HttpMethod httpMethod, HttpEntity entity) {
        HttpEntity<String> response = Optional.ofNullable(
                _restTemplate.exchange(
                        uri, httpMethod, entity, String.class
                )
        ).orElseThrow(
                () -> new RestTemplateClientException("Response was Null")
        );
        return Optional.ofNullable(response.getBody()).orElseThrow(
                () -> new RestTemplateClientException("Response Body was Null")
        );
    }

}
