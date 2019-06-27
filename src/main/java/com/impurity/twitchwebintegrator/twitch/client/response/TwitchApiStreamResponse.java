package com.impurity.twitchwebintegrator.twitch.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchPagination;
import com.impurity.twitchwebintegrator.twitch.domain.TwitchStream;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiStreamResponse {
    @JsonProperty("data")
    private TwitchStream[] streams;
    @JsonProperty("pagination")
    private TwitchPagination pagination;
}