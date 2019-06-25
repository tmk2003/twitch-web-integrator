package com.impurity.twitchwebintegrator.factory;

import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;

/**
 * @author tmk2003
 */
public class TwitchUrlFactory {
    private TwitchUrlFactory() {}

    public static String getUserURL(@NotNull final String channel){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users")
                .queryParam("login", channel)
                .toUriString();
    }

    public static String getStreamURL(@NotNull final String channel){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/streams")
                .queryParam("user_login", channel)
                .toUriString();
    }

    public static String getFollowersURL(@NotNull final String twitchUserId){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users/follows")
                .queryParam("to_id", twitchUserId)
                .toUriString();
    }
}
