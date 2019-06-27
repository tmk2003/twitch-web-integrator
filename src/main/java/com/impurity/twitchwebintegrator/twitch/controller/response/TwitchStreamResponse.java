package com.impurity.twitchwebintegrator.twitch.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchStream;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchStreamResponse {
    @JsonProperty("stream")
    private TwitchStream stream;
}
