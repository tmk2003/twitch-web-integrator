package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;

/**
 * @author Tyler Kokoszka
 */
public class TwitchClient {

    @Autowired
    private TwitchProperties _twitchProperties;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetUser(String channel) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(_twitchProperties.getGetUserUrl())
                .queryParam(LOGIN_KEY, channel);

        return sendTwitchRequest(uriComponentsBuilder.toUriString());
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Users Followers
     *
     * @param twitchUser - The Twitch User to perform the look up on
     * @return The response of the rest call
     */
    public String sendGetFollowers(TwitchUser twitchUser) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(_twitchProperties.getGetFollowersUrl())
                .queryParam(TO_ID_KEY, twitchUser.getId());

        return sendTwitchRequest(builder.toUriString());
    }

    /**
     * Generate the response body from twitch based off the uri
     *
     * @param uri - Generated uri based off the request
     * @return - Response body
     */
    private String sendTwitchRequest(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", _twitchProperties.getClientId());

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
