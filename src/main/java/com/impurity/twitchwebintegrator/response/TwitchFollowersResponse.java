package com.impurity.twitchwebintegrator.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.TwitchFollower;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchFollowersResponse {
    @JsonProperty("followers")
    private TwitchFollower[] followers;
}
