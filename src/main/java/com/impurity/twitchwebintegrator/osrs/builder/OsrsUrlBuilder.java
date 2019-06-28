package com.impurity.twitchwebintegrator.osrs.builder;

import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tmk2003
 */
public class OsrsUrlBuilder {
    private OsrsUrlBuilder() {}

    public static UriComponentsBuilder buildPlayerURL(@NonNull final String playerName) {
        return UriComponentsBuilder
                .fromHttpUrl("https://services.runescape.com/m=hiscore_oldschool/index_lite.ws")
                .queryParam("player", playerName);
    }

    public static UriComponentsBuilder buildGrandExchangeURL(@NonNull final Long itemId) {
        return UriComponentsBuilder
                .fromHttpUrl("https://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json")
                .queryParam("item", itemId);
    }
}
