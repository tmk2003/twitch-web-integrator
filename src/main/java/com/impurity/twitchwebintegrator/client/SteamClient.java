package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryHttpRequestException;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static com.impurity.twitchwebintegrator.factory.SteamUrlFactory.getLibraryURL;

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
    public SteamClient(@NonNull final SteamProperties steamProperties) {
        this.steamProperties = steamProperties;
    }

    @Override
    protected HttpHeaders getHeaders() {
        return new HttpHeaders();
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param steamID - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<SteamApiLibraryResponse> getLibrary(@NonNull final String steamID) {
        try {
            return getRequest(
                    getLibraryURL(steamProperties.getKey(), steamID),
                    HttpMethod.GET,
                    new HttpEntity(this.getHeaders()),
                    SteamApiLibraryResponse.class
            );
        } catch (RestTemplateClientException ex) {
            log.error("Steam Client Issues: {}", ex.getMessage());
            throw new SteamClientLibraryHttpRequestException("Cannot get library", ex.getStatus(), ex);
        }
    }
}
