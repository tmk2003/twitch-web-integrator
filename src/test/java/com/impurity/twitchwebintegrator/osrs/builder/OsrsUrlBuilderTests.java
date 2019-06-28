package com.impurity.twitchwebintegrator.osrs.builder;


import com.impurity.twitchwebintegrator.steam.builder.SteamUrlBuilder;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OsrsUrlBuilderTests extends AbstractTest {

    @Test
    @DisplayName("When player url playName is null, throw null pointer")
    public void osrsUrl_player_null_playerName() {
        assertThrows(NullPointerException.class, () -> OsrsUrlBuilder.buildPlayerURL(null));
    }

    @Test
    @DisplayName("When grandExchange url itemId is null, throw null pointer")
    public void osrsUrl_item_null_itemId() {
        assertThrows(NullPointerException.class, () -> OsrsUrlBuilder.buildGrandExchangeURL(null));
    }

    @Test
    @DisplayName("When player name supplied, player query param is valid")
    public void osrsUrl_player_playerNameIsValid() {
        String playerName = "1234";
        assertEquals(playerName, OsrsUrlBuilder.buildPlayerURL(playerName).build().getQueryParams().get("player").get(0));
    }

    @Test
    @DisplayName("When item id supplied, item query param is valid")
    public void osrsUrl_item_itemIdIsValid() {
        Long itemId = 1234L;
        assertEquals(itemId, (Long) Long.parseLong(OsrsUrlBuilder.buildGrandExchangeURL(itemId).build().getQueryParams().get("item").get(0)));
    }

}
