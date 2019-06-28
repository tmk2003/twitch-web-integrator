package com.impurity.twitchwebintegrator.osrs.builder;


import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.osrs.builder.OsrsPlayerBuilder.buildPlayer;
import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static com.impurity.twitchwebintegrator.osrs.test.utils.OsrsFactory.getValidOsrsPlayerClientResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OsrsPlayerBuilderTests extends AbstractTest {

    @Test
    @DisplayName("When player built, name is correct")
    public void osrsPlayer_name() {
        String name = "apples";
        assertEquals(name, buildPlayer(name, "", getValidOsrsPlayerClientResponse()).getName());
    }

    @Test
    @DisplayName("When player built, type is correct")
    public void osrsPlayer_type() {
        String type = "apples";
        assertEquals(type, buildPlayer("", type, getValidOsrsPlayerClientResponse()).getType());
    }

    @Test
    @DisplayName("When player hi scores null, throw null pointer")
    public void osrsPlayer_null_playerHiScores() {
        assertThrows(NullPointerException.class, () -> buildPlayer("", "", null));
    }

    @Test
    @DisplayName("When player type null, throw null pointer")
    public void osrsPlayer_null_type() {
        assertThrows(NullPointerException.class, () -> buildPlayer("", null, null));
    }

    @Test
    @DisplayName("When player name null, throw null pointer")
    public void osrsPlayer_null_name() {
        assertThrows(NullPointerException.class, () -> buildPlayer(null, "", ""));
    }

    @Test
    @DisplayName("When player hiscores invalid, throw illegal argument")
    public void osrsPlayer_hiscores_invalidLength() {
        assertThrows(IllegalArgumentException.class, () -> buildPlayer("", "", "123\n123\n123"));
    }

}
