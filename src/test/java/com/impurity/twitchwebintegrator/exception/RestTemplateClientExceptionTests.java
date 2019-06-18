package com.impurity.twitchwebintegrator.exception;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertEquals(new RestTemplateClientException(testMessage).getMessage(), testMessage);
    }
}

