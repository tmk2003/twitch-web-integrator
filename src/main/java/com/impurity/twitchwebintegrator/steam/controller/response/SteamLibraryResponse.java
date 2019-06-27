package com.impurity.twitchwebintegrator.steam.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.steam.domain.SteamLibraryGame;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamLibraryResponse {
    @JsonProperty("games")
    private SteamLibraryGame[] games;
}
