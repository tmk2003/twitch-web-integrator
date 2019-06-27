package com.impurity.twitchwebintegrator.twitch.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchFollower;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchFollowersResponse {
    @JsonProperty("followers")
    private TwitchFollower[] followers;
}
