package com.impurity.twitchwebintegrator.domain.steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamLibrary {
    @JsonProperty("game_count")
    private Long gameCount;
    @JsonProperty("games")
    private SteamLibraryGame[] games;
}
