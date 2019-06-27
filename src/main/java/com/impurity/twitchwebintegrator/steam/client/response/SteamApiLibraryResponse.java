package com.impurity.twitchwebintegrator.steam.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.steam.domain.SteamLibrary;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamApiLibraryResponse {
    @JsonProperty("response")
    private SteamLibrary response;
}