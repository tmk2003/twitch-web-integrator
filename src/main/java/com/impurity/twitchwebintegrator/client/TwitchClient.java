package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.client.response.TwitchApiFollowerResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiStreamResponse;
import com.impurity.twitchwebintegrator.client.response.TwitchApiUserResponse;
import com.impurity.twitchwebintegrator.domain.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;

/**
 * @author tmk2003
 */
@Slf4j
public class TwitchClient extends RestTemplateClient {

    private final TwitchProperties twitchProperties;

    /**
     * Create the Twitch Client
     * @param twitchProperties - required properties
     */
    public TwitchClient(TwitchProperties twitchProperties) {
        this.twitchProperties = twitchProperties;
    }

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", twitchProperties.getClientId());
        return headers;
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiUserResponse> getUser(String channel) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users")
                .queryParam(LOGIN_KEY, channel);

        return getRequest(uriComponentsBuilder.toUriString(), new HttpEntity<>(getHeaders()), TwitchApiUserResponse.class);
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Stream
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiStreamResponse> getStream(String channel) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/streams")
                .queryParam(USER_LOGIN_KEY, channel);

        return getRequest(uriComponentsBuilder.toUriString(), new HttpEntity<>(getHeaders()), TwitchApiStreamResponse.class);
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Users Followers
     *
     * @param twitchUserId - The Twitch User id to perform the look up on
     * @return The response of the rest call
     */
    public ResponseEntity<TwitchApiFollowerResponse> getFollowers(String twitchUserId) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users/follows")
                .queryParam(TO_ID_KEY, twitchUserId);

        return getRequest(builder.toUriString(), new HttpEntity<>(getHeaders()), TwitchApiFollowerResponse.class);
    }
}
