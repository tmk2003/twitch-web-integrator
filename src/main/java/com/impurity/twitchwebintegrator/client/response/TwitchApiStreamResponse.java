package com.impurity.twitchwebintegrator.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchPagination;
import com.impurity.twitchwebintegrator.domain.twitch.TwitchStream;
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