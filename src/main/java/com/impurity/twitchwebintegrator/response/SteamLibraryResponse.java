package com.impurity.twitchwebintegrator.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibraryGame;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamLibraryResponse {
    @JsonProperty("games")
    private SteamLibraryGame[] games;
}
