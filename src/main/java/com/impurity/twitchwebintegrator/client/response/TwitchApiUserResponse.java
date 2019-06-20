package com.impurity.twitchwebintegrator.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.TwitchPagination;
import com.impurity.twitchwebintegrator.domain.TwitchStream;
import com.impurity.twitchwebintegrator.domain.TwitchUser;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class TwitchApiUserResponse {
    @JsonProperty("data")
    private TwitchUser[] users;
}