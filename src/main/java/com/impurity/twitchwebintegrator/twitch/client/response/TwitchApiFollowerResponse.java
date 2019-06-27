package com.impurity.twitchwebintegrator.twitch.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchFollower;
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