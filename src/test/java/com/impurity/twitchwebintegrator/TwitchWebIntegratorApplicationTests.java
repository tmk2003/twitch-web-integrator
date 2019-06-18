package com.impurity.twitchwebintegrator;

import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tmk2003
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchWebIntegratorApplicationTests extends AbstractTest {

	@Autowired
	private ApplicationContext _applicationContext;

	@Test
	@DisplayName("The application entry works")
	public void application_entry() {
		TwitchWebIntegratorApplication.main(new String[]{"Apples", "Bananas"});
		assertNotNull(_applicationContext);
	}

	@Test
	@DisplayName("The application context loads")
	public void context_loads() {
		assertNotNull(_applicationContext);
	}
}

