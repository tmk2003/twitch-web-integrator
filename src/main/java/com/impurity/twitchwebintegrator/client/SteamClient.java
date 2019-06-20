package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.client.response.SteamApiLibraryResponse;
import com.impurity.twitchwebintegrator.exception.RestTemplateClientException;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryCreationException;
import com.impurity.twitchwebintegrator.properties.SteamProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;

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
     * Convert a app id & image hash to an image url
     * @param appId the app id for the steam game
     * @param imageHash the image hash for the steam game
     * @return the proper url to get the image
     */
    public String imageHashToUrl(@NonNull final Long appId, @NotBlank final String imageHash) {
        return "http://media.steampowered.com/steamcommunity/public/images/apps/" + appId + "/" + imageHash + ".jpg";
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param steamID - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<SteamApiLibraryResponse> getLibrary(@NotBlank final String steamID) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/")
                .queryParam("key", steamProperties.getKey())
                .queryParam("steamid", steamID)
                .queryParam("include_appinfo", 1);

        try {
            return getRequest(builder.toUriString(), HttpMethod.GET, new HttpEntity(this.getHeaders()), SteamApiLibraryResponse.class);
        } catch (RestTemplateClientException ex) {
            log.error("Steam Client Issues: {}", ex.getMessage());
            throw new SteamLibraryCreationException("Cannot get library", ex);
        }
    }
}
