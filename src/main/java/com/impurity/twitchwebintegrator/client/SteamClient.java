package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.constant.SteamKeys;
import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.SteamKeys.*;

public class SteamClient {

    @Autowired
    private SteamProperties _steamProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param steamID - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetLibrary(String steamID) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(_steamProperties.getGetLibraryUrl())
                .queryParam(KEY, _steamProperties.getKey())
                .queryParam(STEAM_ID, steamID)
                .queryParam(INCLUDE_APPINFO, 1);

        return sendSteamRequest(builder.toUriString());
    }

    /**
     * Generate the response body from steam based off the uri
     *
     * @param uri - Generated uri based off the request
     * @return - Response body
     */
    private String sendSteamRequest(String uri) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET,
                entity, String.class);

        if (response == null) throw new IllegalArgumentException("Steam Response was Null");
        String responseBody = response.getBody();
        if (responseBody == null) throw new IllegalArgumentException("Steam Response Body was Null");

        return responseBody;
    }

}
