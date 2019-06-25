package com.impurity.twitchwebintegrator.domain;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiErrorTests extends AbstractTest {

    @Test
    @DisplayName("When building with null status, throw null pointer")
    public void status_param() {
        assertThrows(NullPointerException.class, () -> new ApiError(null, "", new Exception()));
    }

    @Test
    @DisplayName("When building with null message, throw null pointer")
    public void message_param() {
        assertThrows(NullPointerException.class, () -> new ApiError(OK, null, new Exception()));
    }

    @Test
    @DisplayName("When building with null throwable, throw null pointer")
    public void throwable_param() {
        assertThrows(NullPointerException.class, () -> new ApiError(OK, "", null));
    }
}
