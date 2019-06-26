package com.impurity.twitchwebintegrator.client.response.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchUser;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiUserResponse {
    @JsonProperty("data")
    private TwitchUser[] users;
}