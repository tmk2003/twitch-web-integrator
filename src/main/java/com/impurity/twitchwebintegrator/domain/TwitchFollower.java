package com.impurity.twitchwebintegrator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchFollower {
    @JsonProperty("id")
    private String fromId;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("to_id")
    private String toId;
    @JsonProperty("to_name")
    private String toName;
    @JsonProperty("followed_at")
    private String followedAt;
}
