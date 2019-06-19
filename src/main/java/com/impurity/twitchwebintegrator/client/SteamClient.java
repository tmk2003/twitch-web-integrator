package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.client.response.SteamServerLibraryResponse;
import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryCreationException;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.SteamKeys.*;

/**
 * @author tmk2003
 */
@Slf4j
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
    public ResponseEntity<SteamServerLibraryResponse> getLibrary(String steamID) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(steamProperties.getLibraryUrl())
                .queryParam(KEY, steamProperties.getKey())
                .queryParam(STEAM_ID, steamID)
                .queryParam(INCLUDE_APPINFO, 1);

        try {
            return getRequest(builder.toUriString(), new HttpEntity<>(getHeaders()), SteamServerLibraryResponse.class);
        } catch (RestTemplateClientException ex) {
            log.error("Steam Client Issues: {}", ex.getMessage());
            throw new SteamLibraryCreationException("Cannot get library", ex);
        }
    }
}
