package com.impurity.twitchwebintegrator.exception.twitch;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchClientUserRequestExceptionTests extends AbstractTest {

    @Test
    @DisplayName("The twitch client user request exception stores message properly")
    public void captures_message() {
        String testMessage = "apples";
        assertEquals(new TwitchClientUserRequestException(testMessage, new Exception()).getMessage(), testMessage);
    }

    @Test
    @DisplayName("The twitch client user request exception stores message properly")
    public void captures_throwable() {
        Exception testException = new Exception();
        assertEquals(new TwitchClientUserRequestException("apples", testException).getCause(), testException);
    }
}

