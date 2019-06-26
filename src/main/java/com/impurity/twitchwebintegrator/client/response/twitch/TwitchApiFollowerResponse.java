package com.impurity.twitchwebintegrator.client.response.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchFollower;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiFollowerResponse {
    @JsonProperty("total")
    private Long total;
    @JsonProperty("data")
    private TwitchFollower[] followers;
}