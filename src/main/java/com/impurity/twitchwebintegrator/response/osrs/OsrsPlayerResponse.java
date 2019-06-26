package com.impurity.twitchwebintegrator.response.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibraryGame;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsPlayerResponse {
    @JsonProperty("player")
    private String player;
}
