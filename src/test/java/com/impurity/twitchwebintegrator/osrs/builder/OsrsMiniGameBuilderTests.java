package com.impurity.twitchwebintegrator.osrs.builder;


import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static com.impurity.twitchwebintegrator.osrs.builder.OsrsMiniGameBuilder.buildMiniGames;
import static com.impurity.twitchwebintegrator.osrs.test.utils.OsrsFactory.getInvalidMinigameHiscores;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OsrsMiniGameBuilderTests extends AbstractTest {

    @Test
    @DisplayName("When minigames hiscore is null, throw null pointer")
    public void osrsMiniGames_null_hiscores() {
        assertThrows(NullPointerException.class, () -> buildMiniGames(null));
    }

    @Test
    @DisplayName("When minigames hiscore is invalid, throw illegal argument")
    public void osrsMiniGames_invalid_hiscores() {
        assertThrows(IllegalArgumentException.class, () -> buildMiniGames(new String[0]));
    }

    @Test
    @DisplayName("When minigames hiscore is invalid, throw illegal argument")
    public void osrsMiniGames_invalid_minigame() {
        assertThrows(IllegalArgumentException.class, () -> buildMiniGames(getInvalidMinigameHiscores().split("\\n")));
    }
}
