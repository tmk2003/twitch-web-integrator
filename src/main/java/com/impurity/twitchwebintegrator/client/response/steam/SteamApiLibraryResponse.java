package com.impurity.twitchwebintegrator.client.response.steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.steam.SteamLibrary;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamApiLibraryResponse {
    @JsonProperty("response")
    private SteamLibrary response;
}