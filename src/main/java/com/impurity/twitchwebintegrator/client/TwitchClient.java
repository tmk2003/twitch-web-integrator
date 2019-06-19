package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.domain.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
    public String sendGetUser(String channel) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(twitchProperties.getUserUrl())
                .queryParam(LOGIN_KEY, channel);

        return makeRequest(uriComponentsBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()));
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Stream
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetStream(String channel) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(twitchProperties.getStreamUrl())
                .queryParam(USER_LOGIN_KEY, channel);

        return makeRequest(uriComponentsBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()));
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Users Followers
     *
     * @param twitchUser - The Twitch User to perform the look up on
     * @return The response of the rest call
     */
    public String sendGetFollowers(TwitchUser twitchUser) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(twitchProperties.getFollowersUrl())
                .queryParam(TO_ID_KEY, twitchUser.getId());

        return makeRequest(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()));
    }
}
