package com.impurity.twitchwebintegrator.exception;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
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
public class RestTemplateClientExceptionTests extends AbstractTest {

    @Test
    @DisplayName("The rest template client exception stores message properly")
    public void captures_message() {
        String testMessage = "apples";
        assertEquals(new RestTemplateClientException(testMessage, HttpStatus.SERVICE_UNAVAILABLE, new Exception()).getMessage(), testMessage);
    }

    @Test
    @DisplayName("The rest template client exception stores message properly")
    public void captures_throwable() {
        Exception testException = new Exception();
        assertEquals(new RestTemplateClientException("apples", HttpStatus.SERVICE_UNAVAILABLE, testException).getCause(), testException);
    }

    @Test
    @DisplayName("The rest template client exception stores message properly")
    public void captures_status() {
        HttpStatus testStatus = HttpStatus.ACCEPTED;
        assertEquals(new RestTemplateClientException("apples",testStatus, new Exception()).getStatus(), testStatus);
    }
}

