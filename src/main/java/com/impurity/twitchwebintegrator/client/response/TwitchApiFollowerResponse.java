package com.impurity.twitchwebintegrator.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.TwitchFollower;
import com.impurity.twitchwebintegrator.domain.TwitchUser;
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