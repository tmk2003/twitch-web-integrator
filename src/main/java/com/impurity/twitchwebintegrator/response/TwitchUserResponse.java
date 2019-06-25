package com.impurity.twitchwebintegrator.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchUser;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchUserResponse {
    @JsonProperty("user")
    private TwitchUser user;
}
