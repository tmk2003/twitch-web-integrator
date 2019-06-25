package com.impurity.twitchwebintegrator.controller;

import com.impurity.twitchwebintegrator.domain.SteamLibrary;
import com.impurity.twitchwebintegrator.domain.SteamLibraryGame;
import com.impurity.twitchwebintegrator.exception.steam.SteamClientLibraryHttpRequestException;
import com.impurity.twitchwebintegrator.exception.steam.SteamLibraryNotFoundException;
import com.impurity.twitchwebintegrator.response.SteamLibraryResponse;
import com.impurity.twitchwebintegrator.service.SteamService;
import com.impurity.twitchwebintegrator.test.utils.AbstractTest;
import com.impurity.twitchwebintegrator.test.utils.SteamFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.impurity.twitchwebintegrator.constant.Profiles.UNIT_TEST;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tmk2003
 */
@Tag("Controller")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles(UNIT_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SteamControllerTests extends AbstractTest {

    @MockBean
    private SteamService _mockSteamService;
    @Autowired
    private MockMvc _mockMvc;
    private final String MOCK_CHANNEL_NAME = "abc123";

    /******************* Get Steam Library *******************/
    @Test
    @DisplayName("When getting a steam library and is found, return 200 and library")
    public void steam_library_return_200() throws Exception {
        SteamLibrary steamLibrary = new SteamLibrary();
        SteamLibraryGame[] steamGames = SteamFactory.getValidSteamGameArray(10);
        SteamLibraryResponse steamLibraryResponse = new SteamLibraryResponse();

        steamLibrary.setGames(steamGames);
        steamLibraryResponse.setGames(steamGames);


        when(_mockSteamService.getGameLibrary(MOCK_CHANNEL_NAME)).thenReturn(steamLibrary);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapToJson(steamLibraryResponse)
                ));
    }

    @Test
    @DisplayName("When getting a steam library and is not found, return 404")
    public void no_steam_library_return_404() throws Exception {
        when(_mockSteamService.getGameLibrary(MOCK_CHANNEL_NAME)).thenThrow(SteamLibraryNotFoundException.class);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting a steam library and it cannot be created, return 500")
    public void steam_library_return_500() throws Exception {
        when(_mockSteamService.getGameLibrary(MOCK_CHANNEL_NAME)).thenThrow(SteamClientLibraryHttpRequestException.class);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    /******************* Get Steam Library Amount *******************/
    @Test
    @DisplayName("When getting a steam library amount and is found, return 200 and library amount")
    public void steam_library_amount_return_200() throws Exception {
        when(_mockSteamService.getGameLibraryAmount(MOCK_CHANNEL_NAME)).thenReturn(0L);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library/amount")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }

    @Test
    @DisplayName("When getting a steam library amount and is not found, return 404 amount")
    public void no_steam_library_amount_return_404() throws Exception {
        when(_mockSteamService.getGameLibraryAmount(MOCK_CHANNEL_NAME)).thenThrow(SteamLibraryNotFoundException.class);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library/amount")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("When getting a steam library amount and it cannot be created, return 500 amount")
    public void steam_library_amount_return_500() throws Exception {
        when(_mockSteamService.getGameLibraryAmount(MOCK_CHANNEL_NAME)).thenThrow(SteamClientLibraryHttpRequestException.class);
        _mockMvc.perform(get("/steam/" + MOCK_CHANNEL_NAME + "/library/amount")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
