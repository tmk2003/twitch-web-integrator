package com.impurity.twitchwebintegrator.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchStream;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchStreamResponse {
    @JsonProperty("stream")
    private TwitchStream stream;
}
