package com.impurity.twitchwebintegrator.osrs.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsPlayer;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsPlayerResponse {
    @JsonProperty("player")
    private OsrsPlayer player;
}
