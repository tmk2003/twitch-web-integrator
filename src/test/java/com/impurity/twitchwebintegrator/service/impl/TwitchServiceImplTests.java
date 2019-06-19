package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.client.TwitchClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author tmk2003
 */
@Tag("Service")
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchServiceImplTests {

    @Mock
    private TwitchClient _twitchClient;
    @InjectMocks
    private TwitchServiceImpl _twitchServiceImpl;


    @Test
    @DisplayName("When getting a user, and ")
    public void steam_library_return_200() throws Exception {
//        SteamGame[] steamGames = SteamFactory.getValidSteamGameArray(10);
//        when(_twitchClient.sendGetFollowers(any(TwitchUser.class))).thenReturn("");
    }
}
