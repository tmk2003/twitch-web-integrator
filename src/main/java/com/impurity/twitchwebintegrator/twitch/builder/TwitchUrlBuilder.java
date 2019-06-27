package com.impurity.twitchwebintegrator.twitch.builder;

import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tmk2003
 */
public class TwitchUrlBuilder {
    private TwitchUrlBuilder() {}

    public static UriComponentsBuilder buildUserURL(@NonNull final String channel){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users")
                .queryParam("login", channel);
    }

    public static UriComponentsBuilder buildStreamURL(@NonNull final String channel){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/streams")
                .queryParam("user_login", channel);
    }

    public static UriComponentsBuilder buildFollowersURL(@NonNull final String twitchUserId){
        return UriComponentsBuilder
                .fromHttpUrl("https://api.twitch.tv/helix/users/follows")
                .queryParam("to_id", twitchUserId);
    }
}
