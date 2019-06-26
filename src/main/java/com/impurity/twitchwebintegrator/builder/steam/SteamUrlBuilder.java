package com.impurity.twitchwebintegrator.builder.steam;

import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tmk2003
 */
public class SteamUrlBuilder {
    private SteamUrlBuilder() {}

    public static UriComponentsBuilder buildLibraryURL(@NonNull final String steamKey, @NonNull final String steamID) {
        return UriComponentsBuilder
                .fromHttpUrl("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/")
                .queryParam("key", steamKey)
                .queryParam("steamid", steamID)
                .queryParam("include_appinfo", 1);
    }
}
