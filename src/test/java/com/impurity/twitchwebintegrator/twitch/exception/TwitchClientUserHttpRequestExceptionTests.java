package com.impurity.twitchwebintegrator.twitch.exception;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import com.impurity.twitchwebintegrator.twitch.exception.TwitchClientUserHttpRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class TwitchClientUserHttpRequestExceptionTests extends AbstractTest {

    @Test
    @DisplayName("The twitch user request exception stores message properly")
    public void captures_message() {
        String testMessage = "apples";
        assertEquals(new TwitchClientUserHttpRequestException(testMessage, HttpStatus.SERVICE_UNAVAILABLE, new Exception()).getMessage(), testMessage);
    }

    @Test
    @DisplayName("The twitch user request exception stores message properly")
    public void captures_throwable() {
        Exception testException = new Exception();
        assertEquals(new TwitchClientUserHttpRequestException("apples", HttpStatus.SERVICE_UNAVAILABLE, testException).getCause(), testException);
    }

    @Test
    @DisplayName("The twitch user request exception stores message properly")
    public void captures_status() {
        HttpStatus testStatus = HttpStatus.ACCEPTED;
        assertEquals(new TwitchClientUserHttpRequestException("apples",testStatus, new Exception()).getStatus(), testStatus);
    }
}

