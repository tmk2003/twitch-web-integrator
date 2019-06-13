package com.impurity.twitchwebintegrator.service.impl;

import com.impurity.twitchwebintegrator.model.SteamGame;
import com.impurity.twitchwebintegrator.test.utils.SteamFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tmk2003
 */
@Tag("Service")
@ExtendWith(SpringExtension.class)
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwitchServiceImplTests {

    @Test
    @DisplayName("When getting a user, and ")
    public void steam_library_return_200() throws Exception {
//        SteamGame[] steamGames = SteamFactory.getValidSteamGameArray(10);
//
//        when(_mockSteamService.getGameLibrary(_mockChannelName)).thenReturn(steamGames);
//        _mockMvc.perform(get("/steam/library/" + _mockChannelName))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        mapToJson(steamGames)
//                ));
    }
}
