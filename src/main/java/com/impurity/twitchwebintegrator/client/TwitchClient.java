package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class TwitchClient {

    @Autowired
    private TwitchProperties twitchProperties;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientID = "Client-ID";

    /**
     * Generate the response body from twitch based off the uri
     * @param uri - Generated uri based off the request
     * @return - Response body
     */
    public String sendTwitchRequest(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(clientID, clientID);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET,
                entity, String.class);

        if (response == null) throw new IllegalArgumentException("Twitch Response was Null");
        String responseBody = response.getBody();
        if (responseBody == null) throw new IllegalArgumentException("Twitch Response Body was Null");

        return responseBody;
    }

}
