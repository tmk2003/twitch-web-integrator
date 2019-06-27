package com.impurity.twitchwebintegrator.osrs.builder;

import lombok.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tmk2003
 */
public class OsrsUrlBuilder {
    private OsrsUrlBuilder() {}

    public static UriComponentsBuilder buildPlayerURL(@NonNull final String player) {
        return UriComponentsBuilder
                .fromHttpUrl("https://services.runescape.com/m=hiscore_oldschool/index_lite.ws")
                .queryParam("player", player);
    }

    public static UriComponentsBuilder buildGrandExchangeURL(final Long itemId) {
        return UriComponentsBuilder
                .fromHttpUrl("https://services.runescape.com/m=hiscore_oldschool/index_lite.ws")
                .queryParam("item", itemId);
    }
}
