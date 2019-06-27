package com.impurity.twitchwebintegrator.builder.osrs;


import com.impurity.twitchwebintegrator.domain.osrs.OsrsPlayer;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.builder.osrs.OsrsPlayerBuilder.buildPlayer;
import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static com.impurity.twitchwebintegrator.test.utils.OsrsFactory.getValidOsrsPlayerClientResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
