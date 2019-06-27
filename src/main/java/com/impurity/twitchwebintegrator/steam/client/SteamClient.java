package com.impurity.twitchwebintegrator.steam.client;

import com.impurity.twitchwebintegrator.client.RestTemplateClient;
import com.impurity.twitchwebintegrator.steam.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import com.impurity.twitchwebintegrator.steam.exception.SteamClientLibraryHttpRequestException;
import com.impurity.twitchwebintegrator.steam.properties.SteamProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.impurity.twitchwebintegrator.steam.builder.SteamUrlBuilder.buildLibraryURL;

/**
 * @author tmk2003
 */
@Slf4j
@Component
public class SteamClient extends RestTemplateClient {
    @Autowired
    private SteamProperties steamProperties;

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
                    buildLibraryURL(steamProperties.getKey(), steamID).toUriString(),
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
