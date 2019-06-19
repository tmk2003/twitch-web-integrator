package com.impurity.twitchwebintegrator.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.impurity.twitchwebintegrator.domain.SteamLibrary;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class SteamServerLibraryResponse {
    @JsonProperty("response")
    private SteamLibrary response;
}