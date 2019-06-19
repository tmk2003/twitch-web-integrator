package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.client.response.SteamLibraryResponse;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.SteamKeys.*;

/**
 * @author tmk2003
 */
public class SteamClient extends RestTemplateClient {
    private final SteamProperties steamProperties;

    /**
     * Create the Steam Client
     * @param steamProperties - Required properties
     */
    public SteamClient(final SteamProperties steamProperties) {
        this.steamProperties = steamProperties;
    }

    @Override
    protected HttpHeaders getHeaders() {
        return new HttpHeaders();
    }

    /**
     * Convert a app id & image hash to an image url
     * @param appId the app id for the steam game
     * @param imageHash the image hash for the steam game
     * @return the proper url to get the image
     */
    public String imageHashToUrl(Long appId, String imageHash) {
        return this.steamProperties.getImageUrl() + appId + "/" + imageHash + ".jpg";
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param steamID - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetLibrary(String steamID) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(steamProperties.getLibraryUrl())
                .queryParam(KEY, steamProperties.getKey())
                .queryParam(STEAM_ID, steamID)
                .queryParam(INCLUDE_APPINFO, 1);

        return makeRequest(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()));
    }

    // TODO
    public SteamLibraryResponse getLibrary(String steamID) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(steamProperties.getLibraryUrl())
                .queryParam(KEY, steamProperties.getKey())
                .queryParam(STEAM_ID, steamID)
                .queryParam(INCLUDE_APPINFO, 1);

        return getRequest(builder.toUriString(), new HttpEntity<>(getHeaders()), SteamLibraryResponse.class).getBody();
    }
}
