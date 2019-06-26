package com.impurity.twitchwebintegrator.builder;


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
public class TwitchUrlBuilderTests extends AbstractTest {

    @Test
    @DisplayName("When library url id is null, throw null pointer")
    public void twitchUrl_user_null_channel() {
        assertThrows(NullPointerException.class, () -> TwitchUrlBuilder.buildUserURL(null));
    }

    @Test
    @DisplayName("When library url key is null, throw null pointer")
    public void twitchUrl_stream_null_channel() {
        assertThrows(NullPointerException.class, () -> TwitchUrlBuilder.buildStreamURL(null));
    }

    @Test
    @DisplayName("When library key supplied, key query param is valid")
    public void twitchUrl_followers_null_id() {
        assertThrows(NullPointerException.class, () -> TwitchUrlBuilder.buildFollowersURL(null));
    }

    @Test
    @DisplayName("When followers, to_id query param is valid")
    public void twitchUrl_user_toId() {
        String channel = "1234";
        assertEquals(channel, TwitchUrlBuilder.buildUserURL(channel).build().getQueryParams().get("login").get(0));
    }

    @Test
    @DisplayName("When followers, to_id query param is valid")
    public void twitchUrl_stream_toId() {
        String channel = "1234";
        assertEquals(channel, TwitchUrlBuilder.buildStreamURL(channel).build().getQueryParams().get("user_login").get(0));
    }

    @Test
    @DisplayName("When followers, to_id query param is valid")
    public void twitchUrl_followers_toId() {
        String toid = "1234";
        assertEquals(toid, TwitchUrlBuilder.buildFollowersURL(toid).build().getQueryParams().get("to_id").get(0));
    }

}
