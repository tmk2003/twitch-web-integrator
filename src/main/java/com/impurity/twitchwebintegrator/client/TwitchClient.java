package com.impurity.twitchwebintegrator.client;

import com.impurity.twitchwebintegrator.model.TwitchUser;
import com.impurity.twitchwebintegrator.properties.TwitchProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import static com.impurity.twitchwebintegrator.constant.TwitchKeys.*;

/**
 * @author tmk2003
 */
public class TwitchClient extends RestTemplateClient {

    private final TwitchProperties _twitchProperties;

    /**
     * Create the Twitch Client
     * @param twitchProperties - required properties
     */
    public TwitchClient(TwitchProperties twitchProperties) {
        this._twitchProperties = twitchProperties;
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch User
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetUser(String channel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", _twitchProperties.getClientId());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(_twitchProperties.getUserUrl())
                .queryParam(LOGIN_KEY, channel);

        return makeRequest(uriComponentsBuilder.toUriString(), HttpMethod.GET, entity);
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Stream
     *
     * @param channel - Name of the channel to get information on
     * @return The response of the rest call
     */
    public String sendGetStream(String channel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", _twitchProperties.getClientId());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(_twitchProperties.getStreamUrl())
                .queryParam(USER_LOGIN_KEY, channel);

        return makeRequest(uriComponentsBuilder.toUriString(), HttpMethod.GET, entity);
    }

    /**
     * Perform a Get on the twitch API to attempt to retrieve a Twitch Users Followers
     *
     * @param twitchUser - The Twitch User to perform the look up on
     * @return The response of the rest call
     */
    public String sendGetFollowers(TwitchUser twitchUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", _twitchProperties.getClientId());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(_twitchProperties.getFollowersUrl())
                .queryParam(TO_ID_KEY, twitchUser.getId());

        return makeRequest(builder.toUriString(), HttpMethod.GET, entity);
    }
}
